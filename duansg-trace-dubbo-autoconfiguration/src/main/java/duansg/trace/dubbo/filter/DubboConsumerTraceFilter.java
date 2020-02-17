package duansg.trace.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.*;
import duansg.trace.core.constants.TraceConstants;
import duansg.trace.core.enums.InvokeSideTypeEnum;
import duansg.trace.core.model.TraceContext;
import duansg.trace.core.utils.RpcContextAttachmentUtil;
import duansg.trace.core.utils.TraceInitUtil;
import duansg.trace.core.utils.TraceUtil;
import duansg.trace.dubbo.base.AbstractTraceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * @author Duansg
 * @desc
 * @date 2019-12-31 20:35:08
 */
@Activate(group = { Constants.CONSUMER })
public class DubboConsumerTraceFilter extends AbstractTraceFilter {
    /**
     * @desc
     */
    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.RPC_CLIENT_DIGEST_LOG);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        String remoteHost = null;
        String cApplication = null;
        TraceContext traceContext = TraceUtil.getTraceContext();
        boolean isTraceEmpty = (traceContext == null);
        try {
            //如果为空则新生成一个
            traceContext = (isTraceEmpty && openInitTrace()) ? TraceInitUtil.generateTraceContext() : traceContext;
            //设置统一上下文
            TraceUtil.setTraceContext(traceContext);
            Map<String, String> attachments = RpcContextAttachmentUtil.assemAttachments(traceContext);
            //消费端应用名称
            cApplication = getInvokerUrlParam(invoker, Constants.APPLICATION_KEY);
            attachments.put(TraceConstants.CONSUMER_APPLICATION_KEY, cApplication);
            for (Map.Entry<String, String> entry : attachments.entrySet()){
                RpcContext.getContext().setAttachment(entry.getKey(), entry.getValue());
            }
            Result result =  invoker.invoke(invocation);
            //获取remoteHost
            remoteHost = RpcContext.getContext().getRemoteHost();
            logRpcDigest(invoker, invocation, NetUtils.getLocalHost(), remoteHost, start, -1, digestLogger, InvokeSideTypeEnum.CONSUMER, cApplication, null);
            return result;
        } catch (RpcException e) {
            int resultCode = (e != null) ? e.getCode() : 0;
            logRpcDigest(invoker, invocation, NetUtils.getLocalHost(), remoteHost, start, resultCode, digestLogger,InvokeSideTypeEnum.CONSUMER, cApplication, null);
            throw e;
        } finally {
            if (isTraceEmpty) {
                TraceUtil.clearTraceContext();
            }
        }
    }
}
