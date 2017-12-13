package tr.com.beinplanner.packetsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.packetsale.dao.PacketSaleMembership;
import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.packetsale.repository.PacketSaleClassRepository;
import tr.com.beinplanner.packetsale.repository.PacketSaleMembershipRepository;
import tr.com.beinplanner.packetsale.repository.PacketSalePersonalRepository;

@Service
@Qualifier("packetSaleService")
public class PacketSaleService {

	@Autowired
	PacketSaleClassRepository packetSaleClassRepository;

	@Autowired
	PacketSalePersonalRepository packetSalePersonalRepository;
	
	@Autowired
	PacketSaleMembershipRepository packetSaleMembershipRepository;
	
	public PacketSaleClass findPacketSaleClassById(long saleId) {
		return packetSaleClassRepository.findOne(saleId);
	}
	
	public PacketSalePersonal findPacketSalePersonalById(long saleId) {
		return packetSalePersonalRepository.findOne(saleId);
	}
	
	public PacketSaleMembership findPacketSaleMembershipById(long saleId) {
		return packetSaleMembershipRepository.findOne(saleId);
	}
	
	
	
}
