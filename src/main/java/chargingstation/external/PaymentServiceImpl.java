package chargingstation.external;

import org.springframework.stereotype.Service;

//import org.springframework.stereotype.Component;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * Payment fallback
     */
    public boolean pay(Payment payment) {
        System.out.println("$$$$$ 결재 지연중 입니다. $$$$$");
        System.out.println("$$$$$ 결재 지연중 입니다. $$$$$");
        System.out.println("$$$$$ 결재 지연중 입니다. $$$$$");
        return false;
    }
}

