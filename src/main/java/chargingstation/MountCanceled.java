package chargingstation;

public class MountCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String orderPackType;
    private Integer orderPackQty;
    private String orderStatus;
    private String carName;
    private String carNumber;
    private String phoneNumber;
    private Long stockId;

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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
}