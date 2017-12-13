package tr.com.beinplanner.bonus.business.calculator.personal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.bonus.business.calculator.CalculateService;
import tr.com.beinplanner.bonus.businessDao.UserBonusDetailObj;
import tr.com.beinplanner.bonus.businessDao.UserBonusObj;
import tr.com.beinplanner.definition.dao.DefBonus;
import tr.com.beinplanner.definition.service.DefinitionService;
import tr.com.beinplanner.login.session.LoginSession;
import tr.com.beinplanner.packetpayment.dao.PacketPaymentPersonal;
import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.packetsale.service.PacketSaleService;
import tr.com.beinplanner.schedule.dao.ScheduleFactory;
import tr.com.beinplanner.schedule.dao.ScheduleTimePlan;
import tr.com.beinplanner.schedule.dao.ScheduleUsersPersonalPlan;
import tr.com.beinplanner.schedule.service.ScheduleFactoryService;
import tr.com.beinplanner.settings.dao.PtRules;
import tr.com.beinplanner.util.BonusTypes;
import tr.com.beinplanner.util.PayTypeUtil;
import tr.com.beinplanner.util.PaymentConfirmUtil;
import tr.com.beinplanner.util.RuleUtil;
import tr.com.beinplanner.util.StatuTypes;
@Service
@Scope("prototype")
public class CalculatePersonalBonusToStaticRate implements CalculateService {
	@Autowired
	ScheduleFactoryService scheduleFactoryService;
	
	@Autowired
	ScheduleUsersPersonalPlan scheduleUsersPersonalPlan;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	DefinitionService definitionService;
	
	@Autowired
	PacketSaleService packetSaleService;
	
	@Override
	public UserBonusObj calculateIt(List<ScheduleTimePlan> scheduleTimePlans,long staffId,int firmId) {

		
		
		UserBonusObj userBonusObj=new UserBonusObj();
		
		PtRules ptRulesBonusForConfirmedPayment=loginSession.getPtRules()
					.stream()
					.filter(ptr->ptr.getRuleId()==RuleUtil.rulePayBonusForConfirmedPayment)
					.findFirst().get();
		
		PtRules ptRulesCreditCardCommissionRate=loginSession.getPtRules()
		.stream()
		.filter(ptr->ptr.getRuleId()==RuleUtil.ruleCreditCardCommissionRate)
		.findFirst().get();
		
		PtRules ptRulesCreditCardCommission=loginSession.getPtRules()
		.stream()
		.filter(ptr->ptr.getRuleId()==RuleUtil.ruleCreditCardCommission)
		.findFirst().get();
		
		
		
		
		int bonusPaymentRule=ptRulesBonusForConfirmedPayment.getRuleValue();
		int creditCardCommissionRate=ptRulesCreditCardCommissionRate.getRuleValue();
		int creditCardCommission=ptRulesCreditCardCommission.getRuleValue();
		
		
		userBonusObj.setBonusPaymentRule(bonusPaymentRule);
		userBonusObj.setCreditCardCommissionRate(creditCardCommissionRate);
		userBonusObj.setCreditCardCommissionRule(creditCardCommission);

		List<DefBonus> defBonuses= definitionService.findByUserIdAndBonusTypeAndBonusIsType(staffId, BonusTypes.BONUS_TYPE_PERSONAL, BonusTypes.BONUS_IS_TYPE_STATIC_RATE);
		
		int i=1;
		
		double willPayAmount=0;
		
		List<UserBonusDetailObj> userBonusDetailObjs=new ArrayList<UserBonusDetailObj>();
		
		for (ScheduleTimePlan scheduleTimePlan : scheduleTimePlans) {
			if(scheduleTimePlan.getStatuTp()!=StatuTypes.TIMEPLAN_POSTPONE){
			
			UserBonusDetailObj userBonusDetailObj=new UserBonusDetailObj();
			
			double bonusRate=0;
			for (DefBonus defBonus : defBonuses) {
				if(defBonus.getBonusProgId()==scheduleTimePlan.getProgId()){
					bonusRate=defBonus.getBonusValue();
					break;
				}
			}
			
			
			List<ScheduleFactory> usersInTimePlan=scheduleFactoryService.findScheduleUsersPersonalPlanBySchtId(scheduleTimePlan.getSchtId());
			
			userBonusDetailObj.setScheduleFactories(usersInTimePlan);
			double totalTimePlanPayment=0;
			
			for (ScheduleFactory scheduleFactory : usersInTimePlan) {
				PacketSalePersonal packetSalePersonal=packetSaleService.findPacketSalePersonalById(scheduleFactory.getSaleId());
				PacketPaymentPersonal packetPaymentPersonal=packetSalePersonal.getPacketPaymentPersonal();
				
				double unitPrice=0;
				int saleCount=0;
				
				if(packetPaymentPersonal!=null){
					
					if(bonusPaymentRule==RuleUtil.RULE_OK){
						if(packetPaymentPersonal.getPayConfirm()==PaymentConfirmUtil.PAYMENT_CONFIRM){
							unitPrice=packetPaymentPersonal.getPayAmount()/packetSalePersonal.getProgCount();
						}
					}else{
						unitPrice=packetPaymentPersonal.getPayAmount()/packetSalePersonal.getProgCount();
					}
					
					if(userBonusObj.getCreditCardCommissionRule()==RuleUtil.RULE_OK){
						
						if(packetPaymentPersonal.getPayType()==PayTypeUtil.PAY_TYPE_CREDIT_CARD){
							double commissionRate=((100d- Double.parseDouble(""+userBonusObj.getCreditCardCommissionRate()))/100);
							
							unitPrice=unitPrice*commissionRate;
						}
					}
					
					totalTimePlanPayment+=unitPrice;
					saleCount=packetSalePersonal.getProgCount();
				}
				scheduleFactory.setUnitPrice(unitPrice);
				scheduleFactory.setSaleCount(saleCount);
			}
			
			
			
			
			userBonusDetailObj.setSchCount(scheduleTimePlan.getSchCount());
			userBonusDetailObj.setPlanStartDateStr(scheduleTimePlan.getPlanStartDateStr());
			userBonusDetailObj.setClassCount(i);
			userBonusDetailObj.setProgName(scheduleTimePlan.getProgName());
			userBonusDetailObj.setBonusValue(bonusRate);
			userBonusDetailObj.setPacketUnitPrice(totalTimePlanPayment);
			userBonusDetailObj.setStaffPaymentAmount(totalTimePlanPayment*(bonusRate/100));
			userBonusDetailObj.setSchtId(scheduleTimePlan.getSchtId());
			
			willPayAmount+=userBonusDetailObj.getStaffPaymentAmount();
			
			userBonusDetailObjs.add(userBonusDetailObj);
		   i++;	
		 }
		}
		
		
		userBonusObj.setWillPayAmount(willPayAmount);
		userBonusObj.setSchStaffId(staffId);
		userBonusObj.setUserBonusDetailObjs(userBonusDetailObjs);
		return userBonusObj;
	}

}
