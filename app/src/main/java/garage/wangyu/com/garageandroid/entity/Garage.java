package garage.wangyu.com.garageandroid.entity;

import com.wangyu.common.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车库
 */
public class Garage extends Model {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 车库名
     */
    private String name;

    /**
     * 总停车位数
     */
    private Integer total;

    /**
     * 已使用停车位数
     */
    private Integer inuse;

    /**
     * 空闲停车位数
     */
    private Integer unuse;

    /**
     * 每小时单价
     */
    private BigDecimal price;

    /**
     * 服务IP
     */
    private String serverIp;

    /**
     * 服务端口
     */
    private Integer serverPort;

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
     * 车库名
     * @return name 车库名
     */
    public String getName() {
        return name;
    }

    /**
     * 车库名
     * @param name 车库名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 总停车位数
     * @return total 总停车位数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 总停车位数
     * @param total 总停车位数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 已使用停车位数
     * @return inuse 已使用停车位数
     */
    public Integer getInuse() {
        return inuse;
    }

    /**
     * 已使用停车位数
     * @param inuse 已使用停车位数
     */
    public void setInuse(Integer inuse) {
        this.inuse = inuse;
    }

    /**
     * 空闲停车位数
     * @return unuse 空闲停车位数
     */
    public Integer getUnuse() {
        return unuse;
    }

    /**
     * 空闲停车位数
     * @param unuse 空闲停车位数
     */
    public void setUnuse(Integer unuse) {
        this.unuse = unuse;
    }

    /**
     * 每小时单价
     * @return price 每小时单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 每小时单价
     * @param price 每小时单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 服务ip
     * @return server_ip 服务ip
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * 服务ip
     * @param serverIp 服务ip
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    /**
     * 服务端口
     * @return server_port 服务端口
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * 服务端口
     * @param serverPort 服务端口
     */
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
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