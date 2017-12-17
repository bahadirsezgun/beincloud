package tr.com.beinplanner.packetsale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.login.session.LoginSession;
import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.packetsale.dao.PacketSaleFactory;
import tr.com.beinplanner.packetsale.dao.PacketSaleMembership;
import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.packetsale.repository.PacketSaleClassRepository;
import tr.com.beinplanner.packetsale.repository.PacketSaleMembershipRepository;
import tr.com.beinplanner.packetsale.repository.PacketSalePersonalRepository;
import tr.com.beinplanner.util.DateTimeUtil;

@Service
@Qualifier("packetSaleService")
public class PacketSaleService {

	@Autowired
	LoginSession loginSession;
	
	@Autowired
	PacketSaleClassRepository packetSaleClassRepository;

	@Autowired
	PacketSalePersonalRepository packetSalePersonalRepository;
	
	@Autowired
	PacketSaleMembershipRepository packetSaleMembershipRepository;
	
	public PacketSaleClass findPacketSaleClassById(long saleId) {
		PacketSaleClass packetSaleClass=packetSaleClassRepository.findOne(saleId);
		packetSaleClass.getUser().setPassword(null);
		return packetSaleClass;
	}
	
	public PacketSalePersonal findPacketSalePersonalById(long saleId) {
		PacketSalePersonal packetSalePersonal=packetSalePersonalRepository.findOne(saleId);
		packetSalePersonal.getUser().setPassword(null);
		return packetSalePersonal;
	}
	
	public PacketSaleMembership findPacketSaleMembershipById(long saleId) {
		PacketSaleMembership packetSaleMembership=packetSaleMembershipRepository.findOne(saleId);
		packetSaleMembership.getUser().setPassword(null);
		return packetSaleMembership;
	}
	
	public List<PacketSaleClass> findPacketSaleClassWithNoPayment(int firmId){
		List<PacketSaleClass> packetSaleClasses=packetSaleClassRepository.findPacketSaleClassWithNoPayment(firmId);
		packetSaleClasses.forEach(psf->{psf.getUser().setPassword(null);});
		
		return packetSaleClasses;
	}
	
	public List<PacketSaleMembership> findPacketSaleMembershipWithNoPayment(int firmId){
		List<PacketSaleMembership> packetSaleMemberships=packetSaleMembershipRepository.findPacketSaleMembershipWithNoPayment(firmId);
		packetSaleMemberships.forEach(psf->{psf.getUser().setPassword(null);});
		
		return packetSaleMemberships;
	}
	
	public List<PacketSalePersonal> findPacketSalePersonalWithNoPayment(int firmId){
		List<PacketSalePersonal> packetSalePersonals=packetSalePersonalRepository.findPacketSalePersonalWithNoPayment(firmId);
		packetSalePersonals.forEach(psf->{psf.getUser().setPassword(null);});
		return packetSalePersonals;
	}
	
	public List<PacketSaleFactory> findLast5PacketSales(int firmId){
		List<PacketSalePersonal> packetSalePersonals=packetSalePersonalRepository.findLast5PacketSales(firmId);
		List<PacketSaleMembership> packetSaleMemberships=packetSaleMembershipRepository.findLast5PacketSales(firmId);
		List<PacketSaleClass> packetSaleClasses=packetSaleClassRepository.findLast5PacketSales(firmId);
		
		List<PacketSaleFactory> packetSaleFactories=new ArrayList<PacketSaleFactory>();
		packetSaleFactories.addAll(packetSalePersonals);
		packetSaleFactories.addAll(packetSaleMemberships);
		packetSaleFactories.addAll(packetSaleClasses);
		
		packetSaleFactories.forEach(psf->{
			if(psf instanceof PacketSalePersonal) {
			 ((PacketSalePersonal)psf).getUser().setPassword(null);
			 ((PacketSalePersonal)psf).setSalesDateStr(DateTimeUtil.getDateStrByFormat(((PacketSalePersonal) psf).getSalesDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
			 ((PacketSalePersonal)psf).setChangeDateStr(DateTimeUtil.getDateStrByFormat(((PacketSalePersonal) psf).getChangeDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
				
			}else if(psf instanceof PacketSaleMembership) {
				((PacketSaleMembership)psf).getUser().setPassword(null);
				((PacketSaleMembership)psf).setSalesDateStr(DateTimeUtil.getDateStrByFormat(((PacketSaleMembership) psf).getSalesDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
				((PacketSaleMembership)psf).setChangeDateStr(DateTimeUtil.getDateStrByFormat(((PacketSaleMembership) psf).getChangeDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
			
			}else if(psf instanceof PacketSaleClass) {
				((PacketSaleClass)psf).getUser().setPassword(null);
				((PacketSaleClass)psf).setSalesDateStr(DateTimeUtil.getDateStrByFormat(((PacketSaleClass) psf).getSalesDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
				((PacketSaleClass)psf).setChangeDateStr(DateTimeUtil.getDateStrByFormat(((PacketSaleClass) psf).getChangeDate(),loginSession.getPtGlobal().getPtScrDateFormat() ));
			}
			
		});
		
		
		
		return packetSaleFactories;
	}
	
	
}
