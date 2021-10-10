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
    @Autowired JobRepository jobRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubmitted_UpdateJobStatus(@Payload Submitted submitted){

        if(!submitted.validate()) return;

        System.out.println("\n\n @@@@@@@ Job listener - wheneverSubmitted_UpdateJobStatus to Json @@@@@@@");
        System.out.println(submitted.toJson());
        System.out.println("\n\n");

        // **********************************
        // freelancer 가 업무를 채택
        // **********************************

        Job job = (Job)jobRepository.findByJobId(submitted.getJobId()).get(0);
        job.setJobStatus("occupied");

        jobRepository.save(job);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubmitCanceled_UpdateJobStatus(@Payload SubmitCanceled submitCanceled){

        if(!submitCanceled.validate()) return;

        System.out.println("\n\n##### listener UpdateJobStatus : " + submitCanceled.toJson() + "\n\n");

        // Sample Logic //
        // Job job = new Job();
        // jobRepository.save(job);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverWorkingStatusUpdated_UpdateJobStatus(@Payload WorkingStatusUpdated workingStatusUpdated){

        if(!workingStatusUpdated.validate()) return;

        System.out.println("\n\n##### listener UpdateJobStatus : " + workingStatusUpdated.toJson() + "\n\n");

        // **********************************
        // Job이 완료되었을 경우
        // **********************************
        
        Job job = (Job)jobRepository.findByJobId(workingStatusUpdated.getJobId()).get(0);
        job.setJobStatus("completed");

        jobRepository.save(job);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}