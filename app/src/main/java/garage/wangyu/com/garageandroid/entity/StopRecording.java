package garage.wangyu.com.garageandroid.entity;

import com.wangyu.common.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车记录
 */
public class StopRecording extends Model {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 车库ID
     */
    private Long garageid;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车主电话
     */
    private String phone;

    /**
     * 进入车库时间
     */
    private Date intime;

    /**
     * 离开车库时间
     */
    private Date outtime;

    /**
     * 总共停车时间，单位秒
     */
    private Long totaltime;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 停车费
     */
    private BigDecimal amount;

    /**
     * 状态0-入库;1-出库
     */
    private Integer status;

    /**
     * 车位ID
     */
    private Long itemId;

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
     * 用户ID
     * @return userid 用户ID
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 用户ID
     * @param userid 用户ID
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 车牌号
     * @return car_number 车牌号
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * 车牌号
     * @param carNumber 车牌号
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    /**
     * 车主电话
     * @return phone 车主电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 车主电话
     * @param phone 车主电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 进入车库时间
     * @return intime 进入车库时间
     */
    public Date getIntime() {
        return intime;
    }

    /**
     * 进入车库时间
     * @param intime 进入车库时间
     */
    public void setIntime(Date intime) {
        this.intime = intime;
    }

    /**
     * 离开车库时间
     * @return outtime 离开车库时间
     */
    public Date getOuttime() {
        return outtime;
    }

    /**
     * 离开车库时间
     * @param outtime 离开车库时间
     */
    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    /**
     * 总共停车时间，单位秒
     * @return totaltime 总共停车时间，单位秒
     */
    public Long getTotaltime() {
        return totaltime;
    }

    /**
     * 总共停车时间，单位秒
     * @param totaltime 总共停车时间，单位秒
     */
    public void setTotaltime(Long totaltime) {
        this.totaltime = totaltime;
    }

    /**
     * 单价
     * @return price 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 单价
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 停车费
     * @return amount 停车费
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 停车费
     * @param amount 停车费
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 状态0-入库;1-出库
     * @return status 状态0-入库;1-出库
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态0-入库;1-出库
     * @param status 状态0-入库;1-出库
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 车位ID
     * @return item_id 车位ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 车位ID
     * @param itemId 车位ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}