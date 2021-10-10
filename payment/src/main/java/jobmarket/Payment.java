package jobmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private String payStatus;

    @PostPersist
    public void onPostPersist(){

        if ("payrequested".equals(this.getPayStatus())) {
            System.out.println("\n\n =============결제 승인 처리중=============");
            PaymentApproved paymentApproved = new PaymentApproved();

            BeanUtils.copyProperties(this, paymentApproved);
            paymentApproved.setPayStatus("paid");
            paymentApproved.publishAfterCommit();

            System.out.println("\n" + paymentApproved.toJson() + "\n");

            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
                System.out.println("=============결제 승인 완료============= \n\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PreRemove
    public void onPreRemove(){

        PaymentCanceled paymentCanceled = new PaymentCanceled();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(paymentCanceled);            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        System.out.println("### paymentCanceled Info ###");
        System.out.println(json);
    }


    @PrePersist
    public void onPrePersist(){
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