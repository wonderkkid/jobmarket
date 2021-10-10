package jobmarket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="jobs", path="jobs")
public interface JobRepository extends PagingAndSortingRepository<Job, Long>{

    List<Object> findByJobId(long jobId);

}
