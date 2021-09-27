package chargingstation.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="payment", url="${api.url.pay}", fallback = PaymentServiceImpl.class)
//@FeignClient(name="payment", url="http://localhost:8082/payment", fallback = PaymentServiceImpl.class)
public interface PaymentService {
    @RequestMapping(method= RequestMethod.POST, path="/pay")
    public boolean pay(@RequestBody Payment payment);

}

