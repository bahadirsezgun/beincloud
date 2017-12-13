package tr.com.beinplanner.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.user.dao.User;
import tr.com.beinplanner.user.repository.UserRepository;

@Service
@Qualifier("userService")
public class UserService  {

	
	@Autowired
	UserRepository userRepository;
	
	
	
	
	public User findUserById(long userId){
		return userRepository.findOne(userId);
	}
	
	
	public List<User> findAllByFirmId(long firmId){
		return userRepository.findAllByFirmId(firmId);
	}
	public List<User> findAllByFirmIdAndUserType(long firmId,long userType){
		return userRepository.findAllByFirmIdAndUserType(firmId, userType);
	}
	
}
