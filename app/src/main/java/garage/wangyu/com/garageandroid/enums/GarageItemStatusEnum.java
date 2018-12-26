package garage.wangyu.com.garageandroid.enums;

/**
 * @Description 车位停车状态枚举类
 * @Author wangyu
 * @Date 2018/12/26 23:15
 */
public enum GarageItemStatusEnum {

    NO_CAR("无车", 0),

    HAS_CAR("有车", 1);

    private String name;

    private Integer value;

    GarageItemStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static GarageItemStatusEnum getByValue(Integer value) {
        for (GarageItemStatusEnum e : GarageItemStatusEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    public static boolean idValid(Integer value) {
        return getByValue(value) != null;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    //这里定义了mybatis调用的方法成员，对应ms$TpsFlg@CREATED.value中的value，这应该和序列化有关
    public Integer value() {
        return value;
    }
}
