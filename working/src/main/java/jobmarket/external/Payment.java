package jobmarket.external;

public class Payment {

    private Long id;
    private Long jobId;
    private String payStatus;

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
