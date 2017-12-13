package tr.com.beinplanner.schedule.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonTypeName;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.user.dao.User;
@Entity
@JsonTypeName("sucp")
@Table(name="schedule_users_class_plan")
public class ScheduleUsersClassPlan extends ScheduleFactory{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUCP_ID")
	private long sucpId;
	
	@Column(name="SCHT_ID")
	private long schtId;

	@Column(name="USER_ID")
	private long userId;

	
	@Column(name="SALE_ID")
	private long saleId;

	@Transient
	private User user;
	
	@Transient
	private PacketSaleClass packetSaleClass;


	public long getSchtId() {
		return schtId;
	}



	public void setSchtId(long schtId) {
		this.schtId = schtId;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public long getSaleId() {
		return saleId;
	}



	public void setSaleId(long saleId) {
		this.saleId = saleId;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	

	public long getSucpId() {
		return sucpId;
	}



	public void setSucpId(long sucpId) {
		this.sucpId = sucpId;
	}



	public PacketSaleClass getPacketSaleClass() {
		return packetSaleClass;
	}



	public void setPacketSaleClass(PacketSaleClass packetSaleClass) {
		this.packetSaleClass = packetSaleClass;
	}
	

	


	
	
}
