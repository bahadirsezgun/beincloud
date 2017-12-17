package tr.com.beinplanner.schedule.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.schedule.service.ScheduleService;
import tr.com.beinplanner.user.dao.User;
@Entity
@Table(name="schedule_time_plan")
public class ScheduleTimePlan {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SCHT_ID")
	private long schtId;
	
	@Column(name="SCH_ID")
	private long schId;

	@Column(name="PLAN_START_DATE")
	private Date planStartDate;

	@Column(name="PLAN_END_DATE")
	private Date planEndDate;
	
	
	@Column(name="STATUTP")
	private int statuTp;
	
	@Column(name="SCHT_STAFF_ID")
	private long schtStaffId;
	
	@Column(name="TP_COMMENT")
	private String tpComment;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SCH_ID",foreignKey=@ForeignKey(foreignKeyDefinition="STP_TO_SP_FK"),insertable=false,updatable=false)
	private SchedulePlan schedulePlan;
	
	@OneToMany(mappedBy="schtId",fetch=FetchType.LAZY)
    private List<ScheduleUsersClassPlan>  scheduleUsersClassPlans;
	
	@OneToMany(mappedBy="schtId",fetch=FetchType.LAZY)
    private List<ScheduleUsersPersonalPlan>  scheduleUsersPersonalPlans;
	
	
	public List<ScheduleUsersClassPlan> getScheduleUsersClassPlans() {
		return scheduleUsersClassPlans;
	}

	public void setScheduleUsersClassPlans(List<ScheduleUsersClassPlan> scheduleUsersClassPlans) {
		this.scheduleUsersClassPlans = scheduleUsersClassPlans;
	}

	public List<ScheduleUsersPersonalPlan> getScheduleUsersPersonalPlans() {
		return scheduleUsersPersonalPlans;
	}

	public void setScheduleUsersPersonalPlans(List<ScheduleUsersPersonalPlan> scheduleUsersPersonalPlans) {
		this.scheduleUsersPersonalPlans = scheduleUsersPersonalPlans;
	}

	@Transient
	private String planStartDateStr;
	@Transient
	private String planEndDateStr;

	@Transient
	private int  schCount;
	
	
	@Transient
	private String planDayName;
	@Transient
	private String planDayTime;
	@Transient
	private String planEndDayTime;
	@Transient
	private int planCount;
	
	@Transient
	private String planStatusComment;
	@Transient
	private int planStatus;
	
	
	@Transient
	private User staff;
	
	@Transient
	private int progType;
	@Transient
	private String progName;
	@Transient
	private String progShortName;
	
	@Transient
	private int lastPlan;
	@Transient
	private int firstPlan;
	
	@Transient
	private String sequence ;
	
	@Transient
	private List<ScheduleFactory> users;
	
	
	@Transient
	private int updateFlag=0;
	
	@Transient
	private String participants;

	public long getSchtId() {
		return schtId;
	}

	public void setSchtId(long schtId) {
		this.schtId = schtId;
	}

	public long getSchId() {
		return schId;
	}

	public void setSchId(long schId) {
		this.schId = schId;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public int getStatuTp() {
		return statuTp;
	}

	public void setStatuTp(int statuTp) {
		this.statuTp = statuTp;
	}

	public long getSchtStaffId() {
		return schtStaffId;
	}

	public void setSchtStaffId(long schtStaffId) {
		this.schtStaffId = schtStaffId;
	}

	public String getTpComment() {
		return tpComment;
	}

	public void setTpComment(String tpComment) {
		this.tpComment = tpComment;
	}

	public String getPlanStartDateStr() {
		return planStartDateStr;
	}

	public void setPlanStartDateStr(String planStartDateStr) {
		this.planStartDateStr = planStartDateStr;
	}

	public String getPlanEndDateStr() {
		return planEndDateStr;
	}

	public void setPlanEndDateStr(String planEndDateStr) {
		this.planEndDateStr = planEndDateStr;
	}

	public int getSchCount() {
		return schCount;
	}

	public void setSchCount(int schCount) {
		this.schCount = schCount;
	}

	public String getPlanDayName() {
		return planDayName;
	}

	public void setPlanDayName(String planDayName) {
		this.planDayName = planDayName;
	}

	public String getPlanDayTime() {
		return planDayTime;
	}

	public void setPlanDayTime(String planDayTime) {
		this.planDayTime = planDayTime;
	}

	public String getPlanEndDayTime() {
		return planEndDayTime;
	}

	public void setPlanEndDayTime(String planEndDayTime) {
		this.planEndDayTime = planEndDayTime;
	}

	public int getPlanCount() {
		return planCount;
	}

	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}

	public String getPlanStatusComment() {
		return planStatusComment;
	}

	public void setPlanStatusComment(String planStatusComment) {
		this.planStatusComment = planStatusComment;
	}

	public int getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(int planStatus) {
		this.planStatus = planStatus;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public int getProgType() {
		return progType;
	}

	public void setProgType(int progType) {
		this.progType = progType;
	}

	
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProgShortName() {
		return progShortName;
	}

	public void setProgShortName(String progShortName) {
		this.progShortName = progShortName;
	}

	public int getLastPlan() {
		return lastPlan;
	}

	public void setLastPlan(int lastPlan) {
		this.lastPlan = lastPlan;
	}

	public int getFirstPlan() {
		return firstPlan;
	}

	public void setFirstPlan(int firstPlan) {
		this.firstPlan = firstPlan;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public List<ScheduleFactory> getUsers() {
		return users;
	}

	public void setUsers(List<ScheduleFactory> users) {
		this.users = users;
	}

	public int getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public SchedulePlan getSchedulePlan() {
		return schedulePlan;
	}

	public void setSchedulePlan(SchedulePlan schedulePlan) {
		this.schedulePlan = schedulePlan;
	}

	
	
	
	
	
	
	
	
}
