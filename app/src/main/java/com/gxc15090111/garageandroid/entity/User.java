package com.gxc15090111.garageandroid.entity;

import com.gxc15090111.common.Model;

import java.math.BigDecimal;
import java.util.Date;

public class User extends Model {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别0-男;1-女
     */
    private Integer sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 类型0-固定;1-临时；2-管理员
     */
    private Integer type;

    /**
     * 停车价钱
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别0-男;1-女
     * @return sex 性别0-男;1-女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别0-男;1-女
     * @param sex 性别0-男;1-女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 类型0-固定;1-临时
     * @return type 类型0-固定;1-临时
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型0-固定;1-临时
     * @param type 类型0-固定;1-临时
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 停车价钱
     * @return price 停车价钱
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 停车价钱
     * @param price 停车价钱
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 创建时间
     * @return createtime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}