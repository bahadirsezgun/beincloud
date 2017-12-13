package tr.com.beinplanner.schedule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.schedule.dao.ScheduleMembershipPlan;

@Repository
public interface ScheduleMembershipPlanRepository extends CrudRepository<ScheduleMembershipPlan, Long> {

}
