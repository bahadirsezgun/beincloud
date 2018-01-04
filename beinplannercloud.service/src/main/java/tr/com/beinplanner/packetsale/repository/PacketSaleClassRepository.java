package tr.com.beinplanner.packetsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.packetsale.dao.PacketSaleMembership;
@Repository
public interface PacketSaleClassRepository  extends CrudRepository<PacketSaleClass, Long>  {

	@Query(value="SELECT b.* " + 
			"				 FROM packet_sale_class b " + 
			"				 WHERE b.SALE_ID NOT IN (SELECT SALE_ID FROM packet_payment_class a ) " + 
			"				 AND b.USER_ID IN (SELECT USER_ID FROM user WHERE FIRM_ID=:firmId)",nativeQuery=true )
	public List<PacketSaleClass> findPacketSaleClassWithNoPayment(@Param("firmId") int firmId);
	
	
	@Query(value="SELECT b.* " + 
			"				 FROM packet_sale_class b " + 
			"				 WHERE b.USER_ID IN (SELECT USER_ID FROM user WHERE FIRM_ID=:firmId)"
			+ "              ORDER BY  SALES_DATE DESC "
			+ "              LIMIT 5 ",nativeQuery=true )
	public List<PacketSaleClass> findLast5PacketSales(@Param("firmId") int firmId);
	
	public List<PacketSaleClass> findByUserId(long userId);
	
	
}
