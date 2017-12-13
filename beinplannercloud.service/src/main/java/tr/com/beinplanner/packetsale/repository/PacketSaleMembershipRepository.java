package tr.com.beinplanner.packetsale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.packetsale.dao.PacketSaleMembership;
@Repository
public interface PacketSaleMembershipRepository extends CrudRepository<PacketSaleMembership, Long> {

}
