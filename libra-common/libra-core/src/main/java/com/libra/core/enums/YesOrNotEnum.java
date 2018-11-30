package com.libra.core.enums;

import lombok.Getter;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 是或者否的枚举
 */
@Getter
public enum YesOrNotEnum {
    Y(true, "是", 1),

    N(false, "否", 0);

    private Boolean flag;
    private String desc;
    private Integer code;

    YesOrNotEnum(Boolean flag, String desc, Integer code) {
        this.flag = flag;
        this.desc = desc;
        this.code = code;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (YesOrNotEnum s : YesOrNotEnum.values()) {
                if (s.getCode().equals(status)) {
                    return s.getDesc();
                }
            }
            return "";
        }
    }
}
