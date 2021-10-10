package jobmarket;

import jobmarket.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class JobInfoViewHandler {


    @Autowired
    private JobInfoRepository jobInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenJobPosted_then_CREATE_1 (@Payload JobPosted jobPosted) {
        try {

            if (!jobPosted.validate()) return;

            // view 객체 생성
            JobInfo jobInfo = new JobInfo();
            // view 객체에 이벤트의 Value 를 set 함
            jobInfo.setId(jobPosted.getId());
            jobInfo.setJobId(jobPosted.getJobId());
            jobInfo.setJobTitle(jobPosted.getJobTitle());
            jobInfo.setJobPayment(jobPosted.getJobPayment());
            jobInfo.setJobStatus("registered");
            // view 레파지 토리에 save
            jobInfoRepository.save(jobInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubmitted_then_UPDATE_1(@Payload Submitted submitted) {
        try {
            if (!submitted.validate()) return;
                // view 객체 조회

                    List<JobInfo> jobInfoList = jobInfoRepository.findByJobId(submitted.getJobId());
                    for(JobInfo jobInfo : jobInfoList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    jobInfo.setFreelancerName(submitted.getFreelancerName());
                    jobInfo.setJobStatus("submitted");
                // view 레파지 토리에 save
                jobInfoRepository.save(jobInfo);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubmitCanceled_then_UPDATE_2(@Payload SubmitCanceled submitCanceled) {
        try {
            if (!submitCanceled.validate()) return;
                // view 객체 조회

                    List<JobInfo> jobInfoList = jobInfoRepository.findByJobId(submitCanceled.getJobId());
                    for(JobInfo jobInfo : jobInfoList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    jobInfo.setFreelancerName("");
                    jobInfo.setJobStatus("registered");
                // view 레파지 토리에 save
                jobInfoRepository.save(jobInfo);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_3(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회
            Optional<JobInfo> jobInfoOptional = jobInfoRepository.findById(paymentApproved.getId());

            if( jobInfoOptional.isPresent()) {
                 JobInfo jobInfo = jobInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 jobInfo.setJobStatus("payed");
                // view 레파지 토리에 save
                 jobInfoRepository.save(jobInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenJobDeleted_then_DELETE_1(@Payload JobDeleted jobDeleted) {
        try {
            if (!jobDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            jobInfoRepository.deleteByJobId(jobDeleted.getJobId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

