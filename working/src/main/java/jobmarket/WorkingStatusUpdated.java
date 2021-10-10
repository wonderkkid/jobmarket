package jobmarket;

public class WorkingStatusUpdated extends AbstractEvent {

    private Long id;
    private Long jobId;
    private String freelanceerName;
    private String jobStatus;

    public WorkingStatusUpdated(){
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Long getjobId() {
        return jobId;
    }
    public void setjobId(Long jobId) {
        this.jobId = jobId;
    }


    public String getFreelanceerName() {
        return freelanceerName;
    }
    public void setFreelanceerName(String freelanceerName) {
        this.freelanceerName = freelanceerName;
    }


    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}