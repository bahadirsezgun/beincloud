package com.beinplanner.contollers.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.beinplanner.bonus.service.UserBonusPaymentService;
import tr.com.beinplanner.income.dao.PastIncomeMonthTbl;
import tr.com.beinplanner.income.dao.PtExpenses;
import tr.com.beinplanner.income.service.PtExpensesService;
import tr.com.beinplanner.login.session.LoginSession;
import tr.com.beinplanner.packetpayment.service.PacketPaymentService;
import tr.com.beinplanner.result.HmiResultObj;
import tr.com.beinplanner.schedule.service.ScheduleService;
import tr.com.beinplanner.user.repository.UserRepository;
import tr.com.beinplanner.util.DateTimeUtil;
import tr.com.beinplanner.util.OhbeUtil;

@RestController
@RequestMapping("/bein/dashboard")
public class DashboardController {

	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	PtExpensesService ptExpensesService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserBonusPaymentService userBonusPaymentService;
	
	@Autowired
	PacketPaymentService packetPaymentService;
	
	@PostMapping(value="/findPastForYear/{year}")
	public  @ResponseBody HmiResultObj findPastForYear(@PathVariable("year") int year,HttpServletRequest request ) {
		HmiResultObj hmiResultObj=new HmiResultObj();
		
		List<PastIncomeMonthTbl> pastIncomeMonthTbls=new ArrayList<PastIncomeMonthTbl>();
		for (int i=1;i<13;i++) {
			
			PastIncomeMonthTbl pastIncomeMonthTbl=new PastIncomeMonthTbl();
			pastIncomeMonthTbl.setPimMonth(i);
			pastIncomeMonthTbl.setPimMonthName(DateTimeUtil.getMonthNamesBySequence(i));
			pastIncomeMonthTbl.setPimYear(year);
			
			List<PtExpenses> ptExpenses=ptExpensesService.findPtExpensesForMonth(year, i, loginSession.getUser().getFirmId());
			
			double expenseGeneral=0;
			double incomeGeneral=0;
			
			for (PtExpenses ptExpenses2 : ptExpenses) {
				if(ptExpenses2.getPeInOut()==0)
					expenseGeneral+=ptExpenses2.getPeAmount();
				else
					incomeGeneral+=ptExpenses2.getPeAmount();
			}
			
			pastIncomeMonthTbl.setExpenseGeneral(expenseGeneral);
			
			double totalBonusPaymentPersonal=userBonusPaymentService.findTotalOfMonthBonusPaymentPersonal(loginSession.getUser().getFirmId(), i,year);
			double totalBonusPaymentClass=userBonusPaymentService.findTotalOfMonthBonusPaymentClass(loginSession.getUser().getFirmId(), i,year);
			
			
			pastIncomeMonthTbl.setExpenseToBonus(totalBonusPaymentPersonal+totalBonusPaymentClass);
			
			pastIncomeMonthTbl.setTotalExpense(pastIncomeMonthTbl.getExpenseGeneral()+pastIncomeMonthTbl.getExpenseToBonus());
			
			
			String monthStr=""+i;
			if(i<10)
				 monthStr="0"+i;
			
			String payDateStr="01/"+monthStr+"/"+year+" 00:00";
			
			Date payStartDate=OhbeUtil.getThatDayFormatNotNull(payDateStr, "dd/MM/yyyy HH:mm");
			Date payEndDate=OhbeUtil.getDateForNextMonth(payStartDate, 1);
			
			
			
			
			pastIncomeMonthTbl.setIncomeGeneral(incomeGeneral);
			pastIncomeMonthTbl.setIncomeFromMembers(packetPaymentService.findTotalIncomePaymentInMonth(payStartDate,payEndDate,loginSession.getUser().getFirmId()));
			
			pastIncomeMonthTbl.setTotalIncome(pastIncomeMonthTbl.getIncomeGeneral()+pastIncomeMonthTbl.getIncomeFromMembers());
			
			pastIncomeMonthTbl.setTotalEarn(pastIncomeMonthTbl.getTotalIncome()-pastIncomeMonthTbl.getTotalExpense());
			pastIncomeMonthTbls.add(pastIncomeMonthTbl);
			
		}
	
		hmiResultObj.setResultObj(pastIncomeMonthTbls);
		return hmiResultObj;
	}
	
}
