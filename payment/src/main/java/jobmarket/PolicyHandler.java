package jobmarket;

import jobmarket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelRequested_CancelPayment(@Payload PaymentCancelRequested paymentCancelRequested){

        if(!paymentCancelRequested.validate()) return;

        System.out.println("\n\n##### listener CancelPayment : " + paymentCancelRequested.toJson() + "\n\n");

        // Sample Logic //
        // Payment payment = new Payment();
        // paymentRepository.save(payment);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverJobUpdated_PaymentStatusUpadated(@Payload JobUpdated jobUpdated){

        if(!jobUpdated.validate()) return;

        // **********************************
        // Job이 완료되어 Client가 승인한 경우
        // **********************************

        System.out.println("\n\n##### listener PaymentStatusUpadated : " + jobUpdated.toJson() + "\n\n");
        System.out.println("============= 청구 가능 =============");
        Payment payment = (Payment)paymentRepository.findByJobId(jobUpdated.getJobId()).get(0);
        payment.setPayStatus("payable");

        paymentRepository.save(payment);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}