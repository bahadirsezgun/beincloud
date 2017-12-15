package tr.com.beinplanner.schedule.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.beinplanner.schedule.dao.ScheduleTimePlan;

@Repository
public interface ScheduleTimePlanRepository  extends CrudRepository<ScheduleTimePlan, Long>{

	
	
	@Query(value="SELECT a.SCHT_ID,d.PROG_ID,d.PROG_NAME,c.SCH_COUNT,a.STATUTP ,a.PLAN_START_DATE,a.PLAN_END_DATE,a.SCH_ID,a.SCHT_STAFF_ID,a.TP_COMMENT" + 
			"				  FROM 	schedule_time_plan a,  " + 
			"							schedule_users_class_plan b, " + 
			"				         schedule_plan c, " + 
			"				         program_class d  " + 
			"					 WHERE a.SCHT_STAFF_ID=:schStaffId " + 
			"					   AND a.SCHT_ID=b.SCHT_ID " + 
			"					   AND a.PLAN_START_DATE>=:startDate " + 
			"					   AND a.PLAN_START_DATE<:endDate " + 
			"					   AND c.SCH_ID=a.SCH_ID  " + 
			"					   AND c.PROG_ID=d.PROG_ID  " + 
			"					 GROUP BY a.SCHT_ID,d.PROG_ID,d.PROG_NAME,c.SCH_COUNT,a.STATUTP,a.PLAN_START_DATE,a.PLAN_END_DATE,a.SCHT_STAFF_ID,a.TP_COMMENT  " + 
			"					 ORDER BY a.PLAN_START_DATE ",nativeQuery=true)
	public List<ScheduleTimePlan> findScheduleTimePlansClassPlanByDatesForStaff(@Param("schStaffId") long schStaffId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	

	@Query(value="SELECT a.SCHT_ID,d.PROG_ID,d.PROG_NAME,c.SCH_COUNT,a.STATUTP,a.PLAN_START_DATE,a.PLAN_END_DATE,a.SCH_ID,a.SCHT_STAFF_ID,a.TP_COMMENT" + 
			"				  FROM 	schedule_time_plan a,  " + 
			"							schedule_users_personal_plan b, " + 
			"				         schedule_plan c, " + 
			"				         program_personal d  " + 
			"					 WHERE a.SCHT_STAFF_ID=:schStaffId " + 
			"					   AND a.SCHT_ID=b.SCHT_ID " + 
			"					   AND a.PLAN_START_DATE>=:startDate " + 
			"					   AND a.PLAN_START_DATE<:endDate " + 
			"					   AND c.SCH_ID=a.SCH_ID  " + 
			"					   AND c.PROG_ID=d.PROG_ID  " + 
			"					 GROUP BY a.SCHT_ID,d.PROG_ID,d.PROG_NAME,c.SCH_COUNT,a.STATUTP,a.PLAN_START_DATE,a.PLAN_END_DATE,a.SCHT_STAFF_ID,a.TP_COMMENT  " + 
			"					 ORDER BY a.PLAN_START_DATE ",nativeQuery=true)
	public List<ScheduleTimePlan> findScheduleTimePlansPersonalPlanByDatesForStaff(@Param("schStaffId") long schStaffId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	
}
