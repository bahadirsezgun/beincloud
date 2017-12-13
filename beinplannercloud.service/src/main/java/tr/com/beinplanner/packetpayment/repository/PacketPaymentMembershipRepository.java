package tr.com.beinplanner.packetpayment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetpayment.dao.PacketPaymentFactory;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentMembership;
@Repository
public interface PacketPaymentMembershipRepository extends CrudRepository<PacketPaymentMembership, Long>{

	public PacketPaymentFactory findBySaleId(long saleId);
	
	@Query(value="SELECT * " + 
			"	FROM packet_payment_membership " + 
			"	WHERE PAY_DATE>=:payStartDate " + 
			"	AND PAY_DATE<:payEndDate"
			+ " AND FIRM_ID=:firmId ",nativeQuery=true )
	public List<PacketPaymentMembership> findPacketPaymentMembershipForMonth(@Param("payStartDate") Date payStartDate,@Param("payEndDate") Date payEndDate,@Param("firmId") int firmId);
	
}
