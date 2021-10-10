package jobmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Paying_table")
public class Paying {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private String payStatus;

    @PostPersist
    public void onPostPersist(){
        PaymentRequested paymentRequested = new PaymentRequested();
        BeanUtils.copyProperties(this, paymentRequested);
        paymentRequested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        System.out.println("\n\n @@@@@@@ Paying onPostPersist @@@@@@@");
        System.out.println(paymentRequested.toJson());
        System.out.println("\n\n");

        jobmarket.external.Payment payment = new jobmarket.external.Payment();
        // mappings goes here

        payment.setJobId(paymentRequested.getJobId());
        payment.setPayStatus("payrequested");

        WorkingApplication.applicationContext.getBean(jobmarket.external.PaymentService.class).approvePayment(payment); 
    }


    @PrePersist
    public void onPrePersist(){
        //if ("completedOk".equals(jobStstus)
        PaymentRequested paymentRequested = new PaymentRequested();
        BeanUtils.copyProperties(this, paymentRequested);
        paymentRequested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        System.out.println("\n\n @@@@@@@ Paying onPostPersist @@@@@@@");
        System.out.println(paymentRequested.toJson());
        System.out.println("\n\n");

        jobmarket.external.Payment payment = new jobmarket.external.Payment();
        // mappings goes here

        payment.setJobId(paymentRequested.getJobId());
        payment.setPayStatus("payrequested");

        WorkingApplication.applicationContext.getBean(jobmarket.external.PaymentService.class).approvePayment(payment); 
    }

    @PreUpdate
    public void onPreUpdate(){
    }

    @PreRemove
    public void onPreRemove(){
        PaymentCancelRequested paymentCancelRequested = new PaymentCancelRequested();
        BeanUtils.copyProperties(this, paymentCancelRequested);
        paymentCancelRequested.publishAfterCommit();
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

    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}