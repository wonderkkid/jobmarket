package jobmarket;

public class JobPosted extends AbstractEvent {

    private Long id;
    private Long jobId;
    private String jobTitle;
    private Integer jobPayment;
    private String jobStatus;

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