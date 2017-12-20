package tr.com.beinplanner.schedule.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.dashboard.businessEntity.LastClasses;
import tr.com.beinplanner.schedule.dao.ScheduleMembershipPlan;
import tr.com.beinplanner.schedule.dao.SchedulePlan;
import tr.com.beinplanner.schedule.dao.ScheduleTimePlan;
import tr.com.beinplanner.schedule.dao.ScheduleUsersPersonalPlan;
import tr.com.beinplanner.schedule.repository.ScheduleMembershipPlanRepository;
import tr.com.beinplanner.schedule.repository.SchedulePlanRepository;
import tr.com.beinplanner.schedule.repository.ScheduleTimePlanRepository;
import tr.com.beinplanner.schedule.repository.ScheduleUsersClassPlanRepository;
import tr.com.beinplanner.schedule.repository.ScheduleUsersPersonalPlanRepository;
import tr.com.beinplanner.util.DateTimeUtil;

@Service
@Qualifier("scheduleService")
public class ScheduleService {

	@Autowired
	SchedulePlanRepository schedulePlanRepository;
	
	@Autowired
	ScheduleTimePlanRepository scheduleTimePlanRepository;
	
	@Autowired
	ScheduleMembershipPlanRepository scheduleMembershipPlanRepository;
	
	@Autowired
	ScheduleUsersClassPlanRepository scheduleUsersClassPlanRepository;
	
	@Autowired
	ScheduleUsersPersonalPlanRepository scheduleUsersPersonalPlanRepository;
	
	public SchedulePlan findSchedulePlanById(long schId) {
		return schedulePlanRepository.findOne(schId);
	}
	
	public List<ScheduleTimePlan> findScheduleTimePlansClassPlanByDatesForStaff(long schStaffId, Date startDate, Date endDate,int firmId){
		return scheduleTimePlanRepository.findScheduleTimePlansClassPlanByDatesForStaff(schStaffId, startDate, endDate);
	}
	
	public List<ScheduleTimePlan> findScheduleTimePlansPersonalPlanByDatesForStaff(long schStaffId, Date startDate, Date endDate,int firmId){
		return scheduleTimePlanRepository.findScheduleTimePlansPersonalPlanByDatesForStaff(schStaffId, startDate, endDate);
	}
	public LastClasses findLastOfClasses(int firmId){
		Date startDateNextWeek=DateTimeUtil.getNextWeekStartDate();
		Date endDateNextWeek=DateTimeUtil.getNextWeekEndDate();
	
		Date startDate=DateTimeUtil.getWeekStartDate();
		Date endDate=DateTimeUtil.getWeekEndDate();
	
		
		List<ScheduleMembershipPlan> scheduleMembershipPlansW=scheduleMembershipPlanRepository.findLastOfClasses(startDate, endDate, firmId);
		
		List<ScheduleTimePlan> scheduleTimePlansForClassW=scheduleTimePlanRepository.findLastOfClassesForClass(startDate, endDate, firmId);
		scheduleTimePlansForClassW.forEach(stfpw->{
			stfpw.setScheduleUsersClassPlans(scheduleUsersClassPlanRepository.findBySchtId(stfpw.getSchtId()));
		});
		
		List<ScheduleTimePlan> scheduleTimePlansForPersonalW=scheduleTimePlanRepository.findLastOfClassesForPersonal(startDate, endDate, firmId);
		scheduleTimePlansForPersonalW.forEach(stfpw->{
			stfpw.setScheduleUsersPersonalPlans(scheduleUsersPersonalPlanRepository.findBySchtId(stfpw.getSchtId()));
		});
		scheduleTimePlansForClassW.addAll(scheduleTimePlansForPersonalW);
		
		
		List<ScheduleMembershipPlan> scheduleMembershipPlansNW=scheduleMembershipPlanRepository.findLastOfClasses(startDateNextWeek, endDateNextWeek, firmId);
		
		
		
		List<ScheduleTimePlan> scheduleTimePlansForClassNW=scheduleTimePlanRepository.findLastOfClassesForClass(startDateNextWeek, endDateNextWeek, firmId);
		
		scheduleTimePlansForClassNW.forEach(stfpw->{
			stfpw.setScheduleUsersClassPlans(scheduleUsersClassPlanRepository.findBySchtId(stfpw.getSchtId()));
		});
		
		List<ScheduleTimePlan> scheduleTimePlansForPersonalNW=scheduleTimePlanRepository.findLastOfClassesForPersonal(startDateNextWeek, endDateNextWeek, firmId);
		scheduleTimePlansForPersonalNW.forEach(stfpnw->{
			stfpnw.setScheduleUsersPersonalPlans(scheduleUsersPersonalPlanRepository.findBySchtId(stfpnw.getSchtId()));
		});
		
		
		scheduleTimePlansForClassNW.addAll(scheduleTimePlansForPersonalNW);
		
		LastClasses lastClasses=new LastClasses();
		lastClasses.setStpMTW(scheduleMembershipPlansW);
		lastClasses.setStpMNW(scheduleMembershipPlansNW);
		lastClasses.setStpNW(scheduleTimePlansForClassNW);
		lastClasses.setStpTW(scheduleTimePlansForClassW);
		
		return lastClasses;
		
		
	}
	
	
	
	
}
