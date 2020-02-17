package duansg.trace.core.constants;

/**
 * @author Duansg
 * @desc
 * @date 2019-12-31 19:35:08
 */
public class TraceConstants {

    /** traceId的key */
    public static final String TRACE_ID_KEY = "traceId";

    /** 摘要日志中的空值占位符  */
    public static final String EMPTY_DIGEST_VALUE = "-";

    /** 摘要日志中的左括号  */
    public static final String LEFT_DIGEST_CHAR = "(";

    /** 摘要日志中的右括号  */
    public static final String RIGHT_DIGEST_CHAR = ")";

    /** 摘要日志中的指向箭头  */
    public static final String POINT_DIGEST_CHAR = " -> ";

    /** MVC摘要日志 */
    public static final String PV_DIGEST_LOG = "PV_DIGEST";

    /** mvc框架名称 */
    public static final String MVC_FRAM_NAME = "springmvc";

    /** feign框架名称 */
    public static final String FEIGN_FRAM_NAME = "feign";

    /** 错误摘要日志  */
    public static final String ERROR_DIGEST_LOG = "ERROR_DIGEST";

    /** appName的key */
    public static final String APP_NAME_KEY = "appName";

    /** dubbo.appName的key */
    public static final String DUBBO_APP_NAME_KEY = "dubbo.appName";

    /** 压测的key */
    public static final String LOAD_TEST_KEY = "trace.loadTest";

    /** HTTP头部压测的key */
    public static final String HTTP_LOAD_TEST_KEY = "trace-loadTest";

    /** RpcAttachment前缀 */
    public static final String RPC_ATTACHMENT_PREFIX = "dubboTrace.";

    /** RPC客户端摘要日志 */
    public static final String RPC_CLIENT_DIGEST_LOG = "RPC_CLIENT_DIGEST";

    /** RPC服务端摘要日志 */
    public static final String RPC_SERVER_DIGEST_LOG = "RPC_SERVER_DIGEST";

    /** 消费端应用名称 */
    public static final String CONSUMER_APPLICATION_KEY = "consumer.application";

    /** 提供者端应用名称 */
    public static final String PROVIDER_APPLICATION_KEY = "provider.application";

    /** DAO层摘要日志 */
    public static final String DAO_DIGEST_LOG = "DAO_DIGEST";

    /** FEIGN层摘要日志 */
    public static final String FEIGN_DIGEST_LOG = "FEIGN_DIGEST";

    /** 摘要日志开关*/
    public static final String DIGEST_SWITCH = "spring.boot.trace.switch";

    /** 摘要日志开关(DAO) */
    public static final String DIGEST_LOG_SWITCH_DAO = "spring.boot.trace.traceSwitch.Dao";

    /** 摘要日志开关(PV) */
    public static final String DIGEST_LOG_SWITCH_PV = "spring.boot.trace.traceSwitch.Pv";

    /** 摘要日志开关(FEIGN) */
    public static final String DIGEST_LOG_SWITCH_FEIGN = "spring.boot.trace.traceSwitch.Feign";

    /** 摘要日志开关(DUBBO) */
    public static final String DIGEST_LOG_SWITCH_DUBBO = "spring.boot.trace.traceSwitch.Dubbo";

    /** 压测的开关 */
    public static final String LOAD_TEST_SWITCH = "loadTest.switch";

}
