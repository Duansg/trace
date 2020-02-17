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

/**
 * @author Duansg
 * @desc
 * @date 2019-12-31 20:38:02
 */
@Activate(group = { Constants.PROVIDER })
public class DubboProviderTraceFilter extends AbstractTraceFilter {

    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.RPC_SERVER_DIGEST_LOG);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        //获取remoteHost
        String remoteHost = RpcContext.getContext().getRemoteHost();
        String clientName = null;
        String serverName = null;
        try {
            serverName = getInvokerUrlParam(invoker, Constants.APPLICATION_KEY);
            clientName = getRpcContextAttachmentParam(TraceConstants.CONSUMER_APPLICATION_KEY);
            TraceContext traceContext = RpcContextAttachmentUtil.parseAttachments(RpcContext.getContext().getAttachments());
            //如果为空则新生成一个
            traceContext = (traceContext == null && openInitTrace()) ? TraceInitUtil.generateTraceContext() : traceContext;
            //设置统一上下文
            TraceUtil.setTraceContext(traceContext);
            Result result =  invoker.invoke(invocation);
            logRpcDigest(invoker, invocation, remoteHost, NetUtils.getLocalHost(), start, -1, digestLogger,
                    InvokeSideTypeEnum.PROVIDER, clientName, serverName);
            return result;
        } catch (RpcException e) {
            int resultCode = (e != null) ? e.getCode() : 0;
            logRpcDigest(invoker, invocation, remoteHost, NetUtils.getLocalHost(), start, resultCode, digestLogger,
                    InvokeSideTypeEnum.PROVIDER, clientName, serverName);
            throw e;
        } finally {
            TraceUtil.clearTraceContext();
        }
    }
}
