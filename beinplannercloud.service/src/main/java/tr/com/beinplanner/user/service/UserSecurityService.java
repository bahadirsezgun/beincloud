package tr.com.beinplanner.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.login.session.LoginSession;
import tr.com.beinplanner.settings.service.SettingsService;
import tr.com.beinplanner.user.dao.CustomUserDetails;
import tr.com.beinplanner.user.dao.User;
import tr.com.beinplanner.user.repository.UserRepository;

@Service
@Qualifier("userSecurityService")
public class UserSecurityService  implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	SettingsService settingsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUsers = userRepository.findByUserEmail(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        
        
        User user=optionalUsers.get();
        loginSession.setUser(user);
        loginSession.setPtGlobal(settingsService.findPtGlobalByFirmId(user.getFirmId()));
        loginSession.setPtRules(settingsService.findPtRulesByFirmId(user.getFirmId()));
        
        return optionalUsers
                .map(CustomUserDetails::new).get();
	}
}
