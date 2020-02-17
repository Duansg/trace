package duansg.trace.core.enums;

import lombok.Getter;

/**
 * @author Duansg
 * @desc
 * @date 2019-12-31 20:18:12
 */
@Getter
public enum  InvokeSideTypeEnum {
    /** 消费者 */
    CONSUMER("client", "消费者端"),

    /** 提供者 */
    PROVIDER("server", "消费者端");

    /** 名称 */
    private String name;

    /** 描述 */
    private String desc;

    /**
     * @param name
     * @param desc
     */
    InvokeSideTypeEnum(String name, String desc){
        this.name = name;
        this.desc = desc;
    }
}
