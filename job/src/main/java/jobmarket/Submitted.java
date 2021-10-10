package jobmarket;

public class Submitted extends AbstractEvent {

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

    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}