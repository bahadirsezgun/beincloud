package tr.com.beinplanner.packetsale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
@Repository
public interface PacketSaleClassRepository extends CrudRepository<PacketSaleClass, Long> {

}
