package chargingstation.external;

// import java.util.Date;

public class Payment {

    private Long id;
    private Long orderId;
    private String orderPackType;
    private Integer orderPackQty;
    private String orderPrice;
    private String orderStatus;
    private String carName;
    private String carNumber;
    private String payTime;
    private String phoneNumber;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderPackType() {
        return orderPackType;
    }
    public void setOrderPackType(String orderPackType) {
        this.orderPackType = orderPackType;
    }
    public Integer getOrderPackQty() {
        return orderPackQty;
    }
    public void setOrderPackQty(Integer orderPackQty) {
        this.orderPackQty = orderPackQty;
    }
    public String getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(String price) {
        this.orderPrice = price;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getPayTime() {
        return payTime;
    }
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
