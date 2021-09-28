package chargingstation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chargingstation.external.Payment;
import chargingstation.external.PaymentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


 @RestController
 @RequestMapping("/order")
 public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    // Pack 주문 리스트 가져오기
    @GetMapping("/list")
    public ResponseEntity<List<Order>> getOrderList() {
        List<Order> orders = orderRepository.findAll();
        System.out.println("$$$$$ getOrderList  $$$$$");
        return ResponseEntity.ok(orders);
    }

    // 주문 하기 - Request / Response Transaction
    @PostMapping("/order")
    public ResponseEntity<Order> orderPlace(@RequestBody Order order) {

        Payment payment = new Payment();

        payment.setOrderId(order.getId());
        payment.setOrderStatus("ORDER");
        payment.setOrderPackType(order.getPackType());
        payment.setOrderPackQty(order.getPackQty());
        payment.setOrderPrice(order.getPrice());
        payment.setCarName(order.getCarName());
        payment.setCarNumber(order.getCarNumber());
        payment.setPhoneNumber(order.getPhoneNumber());

        // Request / Response Transaction
        boolean bRet = OrderApplication.applicationContext.getBean(PaymentService.class).pay(payment);
   
        if (bRet) {
            System.out.println("$$$$$ 주문이 완료되었습니다 $$$$$$");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = format.format(Calendar.getInstance().getTime());
            order.setOrderTime(dateStr);
            order.setOrderStatus("주문 완료");
            orderRepository.save(order);

        } else {
            System.out.println("$$$$$ 주문이 실패하였습니다 $$$$$$");
            order.setOrderStatus("주문 실패");
        }
        return ResponseEntity.ok(order);
    }    

    // Pack 주문 취소 하기
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        java.util.Optional<Order> optionalOrder= orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            if (order != null){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = format.format(Calendar.getInstance().getTime());
                order.setOrderTime(dateStr);
                order.setOrderStatus("CANCEL");
                orderRepository.delete(order);

                System.out.println("$$$$$ cancelOrder  $$$$$");
                return ResponseEntity.ok("Cancel Order ID :[" +String.valueOf(order.getId())+ "]");
            }else{
                System.out.println("$$$$$ Failed cancelOrder  $$$$$");
                return ResponseEntity.ok("Can't find Cancel Order Id : ["+ id.toString() + "]");
            }
        }else{
            return ResponseEntity.ok("Can't find Cancel Order Id : ["+ id.toString() + "]");
        }
    }

    // Pack 주문 모두 삭제 
	@DeleteMapping("/deleteall")
	public ResponseEntity<String> deleteAll() {
		System.out.println("$$$$$ deleteAll  $$$$$");
        orderRepository.deleteAll();
		return ResponseEntity.ok("ALL DELETE ORDERS");
	}
 }