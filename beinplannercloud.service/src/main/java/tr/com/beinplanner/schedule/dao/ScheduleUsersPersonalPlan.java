package tr.com.beinplanner.schedule.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonTypeName;

import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.user.dao.User;

@Entity
@Table(name="schedule_users_personal_plan")
@JsonTypeName("supp")
public class ScheduleUsersPersonalPlan extends ScheduleFactory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUPP_ID")
	private long suppId;	
	
	@Column(name="SCHT_ID")
	private long schtId;

	@Column(name="USER_ID")
	private long userId;

	
	@Column(name="SALE_ID")
	private long saleId;

	@Transient
	private PacketSalePersonal packetSalePersonal;

	@Transient
	private User user;
	
	@Transient
	private double unitPrice;
	
	@Transient
	private int saleCount;
	
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

	public PacketSalePersonal getPacketSalePersonal() {
		return packetSalePersonal;
	}

	public void setPacketSalePersonal(PacketSalePersonal packetSalePersonal) {
		this.packetSalePersonal = packetSalePersonal;
	}
	
	public long getSuppId() {
		return suppId;
	}

	public void setSuppId(long suppId) {
		this.suppId = suppId;
	}

	
	
	
	
	
}
