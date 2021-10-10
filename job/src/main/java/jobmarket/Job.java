package jobmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Job_table")
public class Job {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private String jobTitle;
    private Integer jobPayment;
    private String jobStatus;

    @PrePersist
    public void onPrePersist(){
        System.out.println("\n\n @@@@@@@ Job - onPrePersist POST to Json @@@@@@@");

        JobPosted jobPosted = new JobPosted();        
        
        // **********************************
        // Job 등록
        // **********************************        
        this.setJobStatus("posted");  
        BeanUtils.copyProperties(this, jobPosted);                
        jobPosted.publishAfterCommit();

    }

    @PostPersist
    public void onPostPersist(){
        JobPosted jobPosted = new JobPosted();                        
        BeanUtils.copyProperties(this, jobPosted);        
        jobPosted.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate(){
        System.out.println("\n\n @@@@@@@ Job - onPreUpdate to Json @@@@@@@");
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("\n\n @@@@@@@ Job - onPostUpdate to Json @@@@@@@");
        JobUpdated jobUpdated = new JobUpdated();
        BeanUtils.copyProperties(this, jobUpdated);
        jobUpdated.publishAfterCommit();

    }

    @PreRemove
    public void onPreRemove(){
        JobDeleted jobDeleted = new JobDeleted();
        BeanUtils.copyProperties(this, jobDeleted);

        System.out.println("@@@@@@@ Job - onPreRemove to Json @@@@@@@");
        System.out.println(jobDeleted.toJson());
        System.out.println("\n\n");

        jobDeleted.publishAfterCommit();
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

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getJobPayment() {
        return jobPayment;
    }
    public void setJobPayment(Integer jobPayment) {
        this.jobPayment = jobPayment;
    }

    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}