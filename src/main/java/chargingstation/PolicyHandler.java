package chargingstation;

import chargingstation.config.kafka.KafkaProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired 
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMountCompleted_PersistOrder(@Payload MountCompleted mountCompleted){
        // MountPart 로 부터 MountComplete 정보를 받는다.
        if(!mountCompleted.validate()) return;

        System.out.println("\n\n$$$$$ listener UpdateOrder : " + mountCompleted.toJson() + "\n\n");

        java.util.Optional<Order> optionalOrder= orderRepository.findById(mountCompleted.getOrderId());
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            if (order != null){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = format.format(Calendar.getInstance().getTime());

                order.setOrderTime(dateStr);
                order.setOrderStatus("MOUNT_COMPLETED");
                orderRepository.save(order);
                System.out.println("$$$$$ KAKAO Message : PACK MOUNT가 완료 되었습니다. PhoneNumber : [" + order.getPhoneNumber()+ "] $$$$$");
            }
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMountCanceled_RemoveOrder(@Payload MountCanceled mountCanceled){
        // MountPart 로 부터 Mount Cancel 정보를 받는다.
        if(!mountCanceled.validate()) return;

        System.out.println("$$$$$ listener UpdateOrder : " + mountCanceled.toJson() + "$$$$$");

        System.out.println("$$$$$ KAKAO Message : PACK MOUNT가 취소 되었습니다. PhoneNumber : [" + mountCanceled.getPhoneNumber()+ "] $$$$$");
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}