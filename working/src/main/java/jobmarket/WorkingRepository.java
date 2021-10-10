package jobmarket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="workings", path="workings")
public interface WorkingRepository extends PagingAndSortingRepository<Working, Long>{

    List<Object> findByJobId(Long jobId);

}
