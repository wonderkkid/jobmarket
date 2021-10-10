package jobmarket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="payings", path="payings")
public interface PayingRepository extends PagingAndSortingRepository<Paying, Long>{

    List<Object> findByJobId(Long jobId);

}
