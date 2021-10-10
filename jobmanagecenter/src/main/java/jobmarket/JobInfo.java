package jobmarket;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="JobInfo_table")
public class JobInfo {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long jobId;
        private String jobTitle;
        private Integer jobPayment;
        private String jobStatus;
        private String freelancerName;


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
        public String getFreelancerName() {
            return freelancerName;
        }

        public void setFreelancerName(String freelancerName) {
            this.freelancerName = freelancerName;
        }

}