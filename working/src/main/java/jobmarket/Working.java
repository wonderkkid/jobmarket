package jobmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;
import jobmarket.external.Payment;
import jobmarket.external.PaymentService;


@Entity
@Table(name="Working_table")
public class Working {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private String freelancerName;
    private String jobStatus;

    @PostPersist
    public void onPostPersist(){
        Submitted submitted = new Submitted();

        this.setJobStatus("occupied");
        BeanUtils.copyProperties(this, submitted);
        System.out.println("\n\n @@@@@@@ Working - onPostPersist to Json @@@@@@@");
        System.out.println(submitted.toJson());
        System.out.println("\n\n");

        submitted.publishAfterCommit();
    }


    @PreUpdate
    public void onPreUpdate(){

        System.out.println("\n\n @@@@@@@ Working - onPreUpdate to Json @@@@@@@");
        System.out.println(this.getJobStatus());
        System.out.println("\n\n");

        if("completed".equals(this.getJobStatus())){
            System.out.println("\n\n @@@@@@@ Working - onPreUpdate to Json @@@@@@@");
            System.out.println("@@@ JobId : " + getJobId());            
            System.out.println("@@@ occupied by freelancerName : " + getFreelancerName());            

            //payment.setPayStatus("paid");

            //WorkingApplication.applicationContext.getBean(PaymentService.class).approvePayment(payment);

            WorkingStatusUpdated workingStatusUpdated = new WorkingStatusUpdated();
            BeanUtils.copyProperties(this, workingStatusUpdated);
            workingStatusUpdated.publishAfterCommit();


            //System.out.println("@@@ PaymentCompleted JobId= " + getJobId());
            System.out.println("\n\n");
        }
    }

        /*
        SubmitCanceled submitCanceled = new SubmitCanceled();
        BeanUtils.copyProperties(this, submitCanceled);
        submitCanceled.publishAfterCommit();

        PaymentRequested paymentRequested = new PaymentRequested();
        BeanUtils.copyProperties(this, paymentRequested);
        paymentRequested.publishAfterCommit();
        
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        jobmarket.external.Payment payment = new jobmarket.external.Payment();
        // mappings goes here
        //Application.applicationContext.getBean(jobmarket.external.PaymentService.class).approvePayment(payment);
        WorkingApplication.applicationContext.getBean(jobmarket.external.PaymentService.class).approvePayment(payment);

        
        PaymentCancelRequested paymentCancelRequested = new PaymentCancelRequested();
        BeanUtils.copyProperties(this, paymentCancelRequested);
        paymentCancelRequested.publishAfterCommit();
        */

    
    @PrePersist
    public void onPrePersist(){

        Submitted submitted = new Submitted();

        this.setJobStatus("occupied");
        BeanUtils.copyProperties(this, submitted);
        System.out.println("\n\n @@@@@@@ Working - onPostPersist to Json @@@@@@@");
        System.out.println(submitted.toJson());
        System.out.println("\n\n");

        submitted.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getFreelancerName() {
        return freelancerName;
    }
    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

}