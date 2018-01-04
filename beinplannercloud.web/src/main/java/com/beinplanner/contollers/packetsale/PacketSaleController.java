package com.beinplanner.contollers.packetsale;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.beinplanner.packetsale.dao.PacketSaleFactory;
import tr.com.beinplanner.packetsale.service.PacketSaleService;

@RestController
@RequestMapping("/bein/packetsale")
public class PacketSaleController {

	
	@Autowired
	PacketSaleService packetSaleService;
	
	
	
	@RequestMapping(value="/findUserBoughtPackets/{userId}", method = RequestMethod.POST) 
	public @ResponseBody List<PacketSaleFactory> findUserBoughtPackets(@PathVariable("userId") long userId,@PathVariable("progType") int progType, HttpServletRequest request ){
		return packetSaleService.findUserBoughtPackets(userId);
	}
	
	
}
