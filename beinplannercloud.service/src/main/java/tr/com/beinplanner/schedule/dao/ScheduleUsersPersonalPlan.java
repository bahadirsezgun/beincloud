package tr.com.beinplanner.schedule.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name="schedule_users_personal_plan")
@JsonTypeName("supp")
public class ScheduleUsersPersonalPlan extends ScheduleFactory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUPP_ID")
	private long suppId;	
	
	
	public long getSuppId() {
		return suppId;
	}

	public void setSuppId(long suppId) {
		this.suppId = suppId;
	}

	
	
	
	
	
}
