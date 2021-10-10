package jobmarket;

public class SubmitCanceled extends AbstractEvent {

    private Long id;
    private Long jobId;
    private String freelancerName;
    private String jobStatus;

    public SubmitCanceled(){
        super();
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
    public void setFreelanceerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }
    
    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}