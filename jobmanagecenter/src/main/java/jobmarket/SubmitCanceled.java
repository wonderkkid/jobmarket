package jobmarket;

public class SubmitCanceled extends AbstractEvent {

    private Long id;
    private Long jobId;
    private String freelancerName;
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

    public String getFreelancerName() {
        return freelancerName;
    }
    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getWorkStatus() {
        return jobStatus;
    }
    public void setWorkStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}