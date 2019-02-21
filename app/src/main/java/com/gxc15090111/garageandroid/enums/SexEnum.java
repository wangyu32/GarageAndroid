package com.gxc15090111.garageandroid.enums;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/5 1:30
 */
public enum SexEnum {

    MALE("男", 0),

    FEMALE("女", 1);

    private String name;

    private Integer value;

    SexEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static SexEnum getByCode(Integer value) {
        for (SexEnum e : SexEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    public static SexEnum getByName(String name) {
        for (SexEnum e : SexEnum.values()) {
            if (e.name.equals(name)) {
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
