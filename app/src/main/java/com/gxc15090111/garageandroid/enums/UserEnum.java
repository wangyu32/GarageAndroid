package com.gxc15090111.garageandroid.enums;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/5 20:31
 */
public enum UserEnum {

    COMMON("普通用户", 0),

    MEMBERSHIP("会员用户", 1),

    ADMIN("管理员", 2);

    private String name;

    private Integer value;

    UserEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static UserEnum getByCode(Integer value) {
        for (UserEnum e : UserEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    public static boolean idValid(Integer code) {
        return getByCode(code) != null;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
