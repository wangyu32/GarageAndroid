package garage.wangyu.com.garageandroid.entity;

import com.wangyu.common.Model;

public class PriceUnit extends Model {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 计费单位，多少秒为一个计费单位
     */
    private Long unit;

    /**
     * 计费单位名称
     */
    private String uname;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 计费单位，多少秒为一个计费单位
     * @return unit 计费单位，多少秒为一个计费单位
     */
    public Long getUnit() {
        return unit;
    }

    /**
     * 计费单位，多少秒为一个计费单位
     * @param unit 计费单位，多少秒为一个计费单位
     */
    public void setUnit(Long unit) {
        this.unit = unit;
    }

    /**
     * 计费单位名称
     * @return uname 计费单位名称
     */
    public String getUname() {
        return uname;
    }

    /**
     * 计费单位名称
     * @param uname 计费单位名称
     */
    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }
}