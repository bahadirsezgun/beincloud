package tr.com.beinplanner.schedule.dao;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.user.dao.User;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({@JsonSubTypes.Type(value = ScheduleUsersClassPlan.class, name = "sucp"),
			   @JsonSubTypes.Type(value = ScheduleMembershipPlan.class, name = "smp"),
			   @JsonSubTypes.Type(value = ScheduleUsersPersonalPlan.class, name = "supp")})
public abstract class ScheduleFactory {

	@Column(name="SCHT_ID")
	private long schtId;

	@Column(name="USER_ID")
	private long userId;

	
	@Column(name="SALE_ID")
	private long saleId;

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

	@Transient
	private PacketSalePersonal packetSalePersonal;

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

	
	
	
	
	
}
