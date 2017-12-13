package tr.com.beinplanner.packetpayment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.packetpayment.dao.PacketPaymentClass;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentFactory;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentMembership;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentPersonal;
import tr.com.beinplanner.packetpayment.repository.PacketPaymentClassRepository;
import tr.com.beinplanner.packetpayment.repository.PacketPaymentMembershipRepository;
import tr.com.beinplanner.packetpayment.repository.PacketPaymentPersonalRepository;

@Service
@Qualifier("packetPaymentService")
public class PacketPaymentService {

	@Autowired
	PacketPaymentClassRepository packetPaymentClassRepository;
	
	@Autowired
	PacketPaymentPersonalRepository packetPaymentPersonalRepository;
	
	@Autowired
	PacketPaymentMembershipRepository packetPaymentMembershipRepository;
	
	
	
	public PacketPaymentFactory findClassPacketPaymentBySaleId(long saleId) {
		return packetPaymentClassRepository.findBySaleId(saleId);
	}
	
	public PacketPaymentFactory findPersonalPacketPaymentBySaleId(long saleId) {
		return packetPaymentPersonalRepository.findBySaleId(saleId);
	}
	
	
	
	public double findTotalIncomePaymentInMonth(Date startDate,Date endDate,int firmId) {
		
		  List<PacketPaymentPersonal> packetPaymentPersonals=packetPaymentPersonalRepository.findPacketPaymentPersonalForMonth(startDate,endDate,firmId);
		  double totalPaymentForPersonal=packetPaymentPersonals.stream().mapToDouble(ppp->ppp.getPayAmount()).sum();
		  
		  List<PacketPaymentClass> packetPaymentClasses=packetPaymentClassRepository.findPacketPaymentClassForMonth(startDate,endDate,firmId);
		  double totalPaymentForClass=packetPaymentClasses.stream().mapToDouble(ppp->ppp.getPayAmount()).sum();
		  
		  List<PacketPaymentMembership> packetPaymentMemberships=packetPaymentMembershipRepository.findPacketPaymentMembershipForMonth(startDate,endDate,firmId);
		  double totalPaymentForMembership=packetPaymentMemberships.stream().mapToDouble(ppp->ppp.getPayAmount()).sum();
		  
		
		
		return totalPaymentForPersonal+totalPaymentForClass+totalPaymentForMembership;
	}
	
}
