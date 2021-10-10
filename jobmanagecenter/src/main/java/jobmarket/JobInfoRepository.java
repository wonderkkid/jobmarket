package jobmarket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobInfoRepository extends CrudRepository<JobInfo, Long> {

    List<JobInfo> findByJobId(Long jobId);

    void deleteByJobId(Long jobId);
}