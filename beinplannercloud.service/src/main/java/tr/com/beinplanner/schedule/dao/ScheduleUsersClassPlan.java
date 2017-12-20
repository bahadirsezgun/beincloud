package tr.com.beinplanner.schedule.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.annotation.JsonTypeName;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.user.dao.User;
@Entity
@Table(name="schedule_users_class_plan")
@Qualifier("scheduleUsersClassPlan")
@JsonTypeName("sucp")
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

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID",foreignKey=@ForeignKey(foreignKeyDefinition="SUCP_TO_USER_FK"),insertable=false,updatable=false)
	private User user;
	
	@Transient
	private PacketSaleClass packetSaleClass;


	
	@Transient
	private double unitPrice;
	
	@Transient
	private int saleCount;
	
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



	public double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public int getSaleCount() {
		return saleCount;
	}



	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	

	


	
	
}
