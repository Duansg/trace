package duansg.trace.core.base;

import duansg.trace.core.constants.TraceConstants;
import duansg.trace.core.model.DigestModel;
import duansg.trace.core.utils.TraceUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author Duansg
 * @desc 全局摘要日志打印基类.
 * @date 2020-01-08 20:12:22
 */
@Data
public abstract class AbstractTraceInterceptor {
    /**
     * @desc 应用名称
     */
    public String appName;

    /**
     * @desc 摘要日志打印
     * @param digestModel
     * @param digestLogger
     */
    protected void logDigest(DigestModel digestModel, Logger digestLogger, Boolean off) {
        //判断摘要日志全局总开关
        if (digestModel != null && TraceUtil.getPerprotey(TraceConstants.DIGEST_SWITCH)&&off){
            StringBuilder builder = new StringBuilder();
            String traceId = TraceUtil.getTraceId();
            traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
            String extendField = TraceUtil.getContextExtendParam(TraceConstants.LOAD_TEST_KEY);
            if (StringUtils.isNotBlank(extendField)){
                //如果携带了压测的信息,就把压测信息携带到日志中。
                builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(extendField).append(TraceConstants.RIGHT_DIGEST_CHAR);
            }
            builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(traceId).append(TraceConstants.RIGHT_DIGEST_CHAR);
            builder.append(digestModel.toString());
            digestLogger.info(builder.toString());
        }
    }
}
