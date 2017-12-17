package tr.com.beinplanner.packetpayment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetpayment.dao.PacketPaymentFactory;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentMembership;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentPersonal;
@Repository
public interface PacketPaymentPersonalRepository extends CrudRepository<PacketPaymentPersonal, Long>{

	public PacketPaymentPersonal findBySaleId(long saleId);
	
	@Query(value="SELECT a.*,c.PACKET_PRICE " + 
			"	FROM packet_payment_personal a,user b,packet_sale_personal c " + 
			"	WHERE PAY_DATE>=:payStartDate " + 
			"	AND PAY_DATE<:payEndDate AND b.USER_ID=c.USER_ID AND c.SALE_ID=a.SALE_ID "
			+ " AND b.FIRM_ID=:firmId ",nativeQuery=true )
	public List<PacketPaymentPersonal> findPacketPaymentPersonalForDate(@Param("payStartDate") Date payStartDate,@Param("payEndDate") Date payEndDate,@Param("firmId") int firmId);

	
	@Query(value="SELECT a.*,b.PACKET_PRICE " + 
			"				FROM packet_sale_personal b,packet_payment_personal a " + 
			"				 WHERE a.SALE_ID=b.SALE_ID " + 
			"				 AND b.PACKET_PRICE>a.PAY_AMOUNT" + 
			"				 AND b.USER_ID IN (SELECT USER_ID FROM user WHERE FIRM_ID=:firmId)",nativeQuery=true )
	public List<PacketPaymentPersonal> findLeftPacketPaymentPersonal(@Param("firmId") int firmId);
	
	@Query(value="SELECT a.*" + 
			"				FROM packet_sale_personal b,packet_payment_personal a " + 
			"				 WHERE a.SALE_ID=b.SALE_ID " + 
			"				 AND b.USER_ID IN (SELECT USER_ID FROM user WHERE FIRM_ID=:firmId)"
			+ " ORDER BY PAY_DATE DESC "
			+ " LIMIT 5 ",nativeQuery=true )
	public List<PacketPaymentPersonal> findLast5packetPayments(@Param("firmId") int firmId);
	
	
	
	
}
