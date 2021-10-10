package jobmarket;

public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long jobId;
    private String payStatus;

    public PaymentApproved(){
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
    public void setjobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}