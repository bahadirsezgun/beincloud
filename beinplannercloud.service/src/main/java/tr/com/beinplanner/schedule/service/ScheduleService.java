package tr.com.beinplanner.schedule.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.schedule.dao.SchedulePlan;
import tr.com.beinplanner.schedule.dao.ScheduleTimePlan;
import tr.com.beinplanner.schedule.repository.SchedulePlanRepository;
import tr.com.beinplanner.schedule.repository.ScheduleTimePlanRepository;

@Service
@Qualifier("scheduleService")
public class ScheduleService {

	@Autowired
	SchedulePlanRepository schedulePlanRepository;
	
	@Autowired
	ScheduleTimePlanRepository scheduleTimePlanRepository;
	
	public SchedulePlan findSchedulePlanById(long schId) {
		return schedulePlanRepository.findOne(schId);
	}
	
	public List<ScheduleTimePlan> findScheduleTimePlansClassPlanByDatesForStaff(long schStaffId, Date startDate, Date endDate,int firmId){
		return scheduleTimePlanRepository.findScheduleTimePlansClassPlanByDatesForStaff(schStaffId, startDate, endDate);
	}
	
	public List<ScheduleTimePlan> findScheduleTimePlansPersonalPlanByDatesForStaff(long schStaffId, Date startDate, Date endDate,int firmId){
		return scheduleTimePlanRepository.findScheduleTimePlansPersonalPlanByDatesForStaff(schStaffId, startDate, endDate);
	}
	
}
