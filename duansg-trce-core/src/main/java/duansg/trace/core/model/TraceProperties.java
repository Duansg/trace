package duansg.trace.core.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Duansg
 * @desc 全局配置类需要外部指定属性
 * @date 2019-12-28 14:32:12
 */
@Data
@ConfigurationProperties(prefix="spring.boot.trace")
public class TraceProperties {
    /**
     * @desc 服务名称
     */
    private String appName;
    /**
     * @desc
     * 例如定义切入点表达式 * com.duansg.service.impl..*.*(..)
     * 1、第一个*号：表示返回类型， *号表示所有的类型。
     * 2、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.duansg.service.impl包、子孙包下所有类的方法。
     * 3、第二个*号：表示类名，*号表示所有的类。
     * 4、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    private String traceDaoExecution;
    /**
     *
     */
    private String tracePvExecution;
    /**
     * @desc 总开关
     * @desc key:spring.boot.trace.traceSwitch
     */
    private boolean traceSwitch = true;
    /**
     * @desc dao开关
     * @desc key:spring.boot.trace.traceSwitch.Dao
     */
    private boolean traceSwitchDao = false;
    /**
     * @desc pv开关
     * @desc key:spring.boot.trace.traceSwitch.Pv
     */
    private boolean traceSwitchPv = false;
    /**
     * @desc feign开关
     * @desc key:spring.boot.trace.traceSwitch.Feign
     */
    private boolean traceSwitchFeign = false;
    /**
     * @desc dubbo开关
     * @desc key:spring.boot.trace.traceSwitch.Dubbo
     */
    private boolean traceSwitchDubbo = false;
    /**
     *
     */
    private String customIncerptor;


}
