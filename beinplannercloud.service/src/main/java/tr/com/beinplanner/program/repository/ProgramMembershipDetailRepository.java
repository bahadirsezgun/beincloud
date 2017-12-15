package tr.com.beinplanner.program.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.program.dao.ProgramMembershipDetail;

@Repository
public interface ProgramMembershipDetailRepository extends CrudRepository<ProgramMembershipDetail, Long>{

}
