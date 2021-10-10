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
    @Autowired WorkingRepository workingRepository;
    @Autowired PayingRepository payingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_UpdateWorkingStatus(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener UpdateWorkingStatus : " + paymentApproved.toJson() + "\n\n");

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_UpdateWorkingStatus(@Payload PaymentCanceled paymentCanceled){

        if(!paymentCanceled.validate()) return;

        System.out.println("\n\n##### listener UpdateWorkingStatus : " + paymentCanceled.toJson() + "\n\n");

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverJobUpdated_WorkingStatusUpdated(@Payload JobUpdated jobUpdated){

        if(!jobUpdated.validate()) return;

        System.out.println("\n\n##### listener WorkingStatusUpdated : " + jobUpdated.toJson() + "\n\n");

        Working workings = (Working)workingRepository.findByJobId(jobUpdated.getJobId()).get(0);
        workings.setJobStatus(jobUpdated.getJobStatus());

        workingRepository.save(workings);       

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}