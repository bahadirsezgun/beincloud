package tr.com.beinplanner.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.dashboard.businessEntity.ActiveMember;
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
	
	
	public ActiveMember findActiveMemberCount(int firmId) {
		List<User> userInMemberships=userRepository.findActiveMemberInMembershipPlanning(firmId);
		List<User> userInClasss=userRepository.findActiveMemberInClassPlanning(firmId);
		List<User> userInPersonals=userRepository.findActiveMemberInPersonalPlanning(firmId);
		
		
		ActiveMember activeMember=new ActiveMember();
		activeMember.setActiveMemberCCount(userInClasss.size());
		activeMember.setActiveMemberPCount(userInPersonals.size());
		activeMember.setActiveMemberMCount(userInMemberships.size());
		
		activeMember.setActiveMemberCount(activeMember.getActiveMemberCCount()+activeMember.getActiveMemberMCount()+activeMember.getActiveMemberPCount());;
		
		return activeMember;
		
	}
	
}
