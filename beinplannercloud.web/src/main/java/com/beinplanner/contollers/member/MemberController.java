package com.beinplanner.contollers.member;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.beinplanner.result.HmiResultObj;
import tr.com.beinplanner.user.dao.User;
import tr.com.beinplanner.user.iuser.ProcessAdmin;
import tr.com.beinplanner.user.iuser.ProcessInterface;
import tr.com.beinplanner.user.service.UserService;

@RestController
@RequestMapping("/bein/member")
public class MemberController {

	@Autowired
	UserService userService;
	
	
	
	@PostMapping(value="/findById/{userId}")
	public  @ResponseBody HmiResultObj doLogin(@PathVariable long userId,HttpServletRequest request ) {
		User user=userService.findUserById(userId);
		HmiResultObj hmiResultObj=new HmiResultObj();
		hmiResultObj.setResultObj(user);
		return hmiResultObj;
	}
	
	@PostMapping(value="/findAll/{firmId}")
	public  @ResponseBody HmiResultObj findAll(@PathVariable long firmId,HttpServletRequest request ) {
		List<User> user=userService.findAllByFirmId(firmId);
		HmiResultObj hmiResultObj=new HmiResultObj();
		hmiResultObj.setResultObj(user);
		return hmiResultObj;
	}
	
	
}
