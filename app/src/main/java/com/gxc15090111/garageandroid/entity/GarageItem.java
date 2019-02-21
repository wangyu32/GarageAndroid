package com.gxc15090111.garageandroid.entity;

import com.gxc15090111.common.Model;

public class GarageItem extends Model {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 车库ID
     */
    private Long garageid;

    /**
     * 车位编号
     */
    private String code;

    /**
     * 车位层级1-1层;2-2层
     */
    private Integer level;

    /**
     * 车位状态0-无车;1-有车
     */
    private Integer status;

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
     * 车库ID
     * @return garageid 车库ID
     */
    public Long getGarageid() {
        return garageid;
    }

    /**
     * 车库ID
     * @param garageid 车库ID
     */
    public void setGarageid(Long garageid) {
        this.garageid = garageid;
    }

    /**
     * 车位编号
     * @return code 车位编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 车位编号
     * @param code 车位编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 车位层级1-1层;2-2层
     * @return level 车位层级1-1层;2-2层
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 车位层级1-1层;2-2层
     * @param level 车位层级1-1层;2-2层
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 车位状态0-无车;1-有车
     * @return status 车位状态0-无车;1-有车
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 车位状态0-无车;1-有车
     * @param status 车位状态0-无车;1-有车
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}