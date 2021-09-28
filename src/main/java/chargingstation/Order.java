package chargingstation;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String packType;
    private Integer packQty;
    private String orderTime;
    private String price;
    private String orderStatus;
    private String carName;
    private String carNumber;
    private String phoneNumber;

	// public static enum ORDER_STATUS {
	// 	ORDER_PLACED, ORDER_CANCELED, PAY_FINISHED, PAY_CANCELED, MOUNT_REQUESTED, MOUNT_CANCEL_REQUESTED, MOUNT_COMPLETE, MOUNT_CANCELED,
    //     ORDER_FAILED
	// }     

    // 주문 실행 - Request / Response Transaction
    @PostPersist
    public void onPostPersist(){

        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced); 
        orderPlaced.saveJsonToPvc(orderPlaced.getOrderStatus(), orderPlaced.toJson());
    }

    // 주문 취소 - 취소 시 정보를 삭제한다. 
    @PostRemove
    public void onPostRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.setOrderStatus("ORDER_CANCELED");
        orderCanceled.setId(this.id);
        orderCanceled.publishAfterCommit();

        orderCanceled.saveJsonToPvc(orderCanceled.getOrderStatus(), orderCanceled.toJson());
        System.out.println("$$$$$$$$$$ Order onPostRemove  $$$$$$$$$$");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }
    public Integer getPackQty() {
        return packQty;
    }

    public void setPackQty(Integer packQty) {
        this.packQty = packQty;
    }
    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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




}