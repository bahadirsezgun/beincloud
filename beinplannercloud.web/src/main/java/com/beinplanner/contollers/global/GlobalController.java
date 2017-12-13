package com.beinplanner.contollers.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.beinplanner.login.session.LoginSession;
import tr.com.beinplanner.result.HmiResultObj;
import tr.com.beinplanner.settings.dao.PtGlobal;
import tr.com.beinplanner.settings.service.SettingsService;
import tr.com.beinplanner.user.dao.User;

@RestController
@RequestMapping("/bein/global")
public class GlobalController {

	@Autowired
	LoginSession loginSession;
	
	@Autowired
	SettingsService settingsService;
	
	
	@PostMapping(value="/getGlobals")
	public  @ResponseBody HmiResultObj getGlobals() {
	  PtGlobal ptGlobal=	 settingsService.findPtGlobalByFirmId(loginSession.getUser().getFirmId());
	  HmiResultObj hmiResultObj=new HmiResultObj();
	  hmiResultObj.setResultObj(ptGlobal);
	  return hmiResultObj;
	}
	
}
