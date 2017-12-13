package tr.com.beinplanner.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.user.dao.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAllByFirmId(long firmId);

	List<User> findAllByFirmIdAndUserType(long firmId,long userType);
	
	Optional<User> findByUserEmail(String userEmail);
	
}
