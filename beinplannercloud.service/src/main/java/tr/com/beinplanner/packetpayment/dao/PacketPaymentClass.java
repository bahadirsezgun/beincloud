package tr.com.beinplanner.packetpayment.dao;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonTypeName;

import tr.com.beinplanner.packetsale.dao.PacketSaleClass;
import tr.com.beinplanner.packetsale.dao.PacketSaleFactory;
import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.user.dao.User;
@Entity
@Table(name="packet_payment_class")
@JsonTypeName("ppc")
public class PacketPaymentClass extends PacketPaymentFactory {

	@Transient
	private String type="ppc";
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAY_ID")
	private long payId;
	
	@Column(name="SALE_ID")
	private long saleId;
	
	@Column(name="PAY_AMOUNT")
	private double payAmount;
	
	
	
	@Column(name="PAY_DATE")
	private Date payDate;
	
	@Transient
	private String payDateStr;
	
	@Column(name="PAY_CONFIRM")
	private int payConfirm;
	
	@Column(name="CHANGE_DATE")
	private Date changeDate=new Date();
	
	@Transient
	private String changeDateStr;
	
	@Column(name="PAY_TYPE")
	private int payType;
	
	

	@Transient
	private String payComment;
	
	
	@Transient
	private String salesDateStr;
	
	
	@OneToMany(mappedBy="payId",fetch=FetchType.EAGER)
	private List<PacketPaymentClassDetail> packetPaymentClassDetails;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SALE_ID",foreignKey=@ForeignKey(foreignKeyDefinition="PPC_TO_PSC_FK"),insertable=false,updatable=false)
	private PacketSaleClass packetSaleClass;

	/***************************************************************/
	/**********************USER ***********************************/
	/**************************************************************/
	@Transient
	private User user;
	

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public long getSaleId() {
		return saleId;
	}

	public void setSaleId(long saleId) {
		this.saleId = saleId;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayDateStr() {
		return payDateStr;
	}

	public void setPayDateStr(String payDateStr) {
		this.payDateStr = payDateStr;
	}

	public int getPayConfirm() {
		return payConfirm;
	}

	public void setPayConfirm(int payConfirm) {
		this.payConfirm = payConfirm;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeDateStr() {
		return changeDateStr;
	}

	public void setChangeDateStr(String changeDateStr) {
		this.changeDateStr = changeDateStr;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getPayComment() {
		return payComment;
	}

	public void setPayComment(String payComment) {
		this.payComment = payComment;
	}



	public String getSalesDateStr() {
		return salesDateStr;
	}

	public void setSalesDateStr(String salesDateStr) {
		this.salesDateStr = salesDateStr;
	}

	

	public List<PacketPaymentClassDetail> getPacketPaymentClassDetails() {
		return packetPaymentClassDetails;
	}

	public void setPacketPaymentClassDetails(List<PacketPaymentClassDetail> packetPaymentClassDetails) {
		this.packetPaymentClassDetails = packetPaymentClassDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	public PacketSaleClass getPacketSaleClass() {
		return packetSaleClass;
	}

	public void setPacketSaleClass(PacketSaleClass packetSaleClass) {
		this.packetSaleClass = packetSaleClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	


	
}
