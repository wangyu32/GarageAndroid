package garage.wangyu.com.garageandroid.enums;

/**
 * @Description 车辆状态枚举类
 * @Author wangyu
 * @Date 2018/12/6 17:46
 */
public enum CarStatusEnum {

    COME_IN("入库", 0),

    COME_OUT("出库", 1);

    private String name;

    private Integer value;

    CarStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static CarStatusEnum getByCode(Integer value) {
        for (CarStatusEnum e : CarStatusEnum.values()) {
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
