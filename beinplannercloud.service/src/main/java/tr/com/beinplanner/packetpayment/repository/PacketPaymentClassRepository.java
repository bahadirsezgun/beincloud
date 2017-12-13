package tr.com.beinplanner.packetpayment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetpayment.dao.PacketPaymentClass;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentFactory;

@Repository
public interface PacketPaymentClassRepository extends CrudRepository<PacketPaymentClass, Long>{

	public PacketPaymentFactory findBySaleId(long saleId);

	
	@Query(value="SELECT * " + 
			"	FROM packet_payment_class " + 
			"	WHERE PAY_DATE>=:payStartDate " + 
			"	AND PAY_DATE<:payEndDate"
			+ " AND FIRM_ID=:firmId ",nativeQuery=true )
	public List<PacketPaymentClass> findPacketPaymentClassForMonth(@Param("payStartDate") Date payStartDate,@Param("payEndDate") Date payEndDate,@Param("firmId") int firmId);
	
}
