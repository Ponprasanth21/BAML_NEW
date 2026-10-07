package com.bornfire.Services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bornfire.entity.Monitoringparameter;
import com.bornfire.entity.MontParameterRepository;
import com.bornfire.entity.RuleEngineRepository;
import com.bornfire.entity.TRAN_MASTER;
import com.bornfire.entity.TRAN_MASTER_REPOSITRY;
import com.bornfire.entity.Transaction;
import com.bornfire.entity.TransactionMasterRepository;




@Service
@ConfigurationProperties("output")
@Transactional
public class TransactionMasterServices {
	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
	
	@Autowired
	TransactionMasterRepository transactionmasterrepository;
	
	@Autowired
	RuleEngineRepository ruleEngineRepository;
	@Autowired
	MontParameterRepository montParameterRepository;
	
	@Autowired
	TRAN_MASTER_REPOSITRY TRANMaster;
	
	@Autowired
	SessionFactory sessionFactory;


	


	public Page<TRAN_MASTER> getTransactionDetails(PageRequest page,String ruleCode ,Date today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomList(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today,page);	 
	}
	/*public String getCount(String ruleCode ,String today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOF(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today);
	}*/
	public Page<TRAN_MASTER> getTransactionDetailsHVCWL(PageRequest page,String ruleCode ,Date today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListHVCWL(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today,page);
	}
	/*public String getCountHVCWL(String ruleCode ,String today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFHVCWL(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today);
	}*/
	public Page<TRAN_MASTER> getTransactionDetailsHVNCD(PageRequest page,String ruleCode ,Date today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListHVNCD(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today,page);
	}
	/*public String getCountHVNCD(String ruleCode ,String today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFHVNCD(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today);
	}*/
	public Page<TRAN_MASTER> getTransactionDetailsHVNCW(PageRequest page,String ruleCode ,Date today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListHVNCW(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today,page);
	}
	/*public String getCountHVNCW(String ruleCode ,String today) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFHVNCW(ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),today);
	}*/
	
	/*public List<TRAN_MASTER> getTransactionDetailsCVCDP(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCDP(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVCDP(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVCDP(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVCWL(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCWL(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVCWL(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVCWL(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVCND(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCND(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVCND(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVCND(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVNCW(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVNCW(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVNCW(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVNCW(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	
	
	public Page<TRAN_MASTER> getTransactionDetailsCVCDP1(PageRequest page,String ruleCode ,Date today,Date fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println( ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCDP1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value(),page);
	}
	/*public String getCountCVCDP1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVCDP1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVCWL1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCWL1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVCWL1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVCWL1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVCND1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVCND1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVCND1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVNCD1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public List<TRAN_MASTER> getTransactionDetailsCVNCW1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 return TRANMaster.findAllCustomListCVNCW1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	/*public String getCountCVNCW1(String ruleCode ,String today,String fromdate) {
		 List<Monitoringparameter> ruleList= montParameterRepository.getRuleCodeData(ruleCode);	 
		 System.out.println(ruleList.get(0).getLow_value());
		 System.out.println(ruleList.get(0).getHigh_value());
		 
		 return TRANMaster.getCountOFCVNCW1(fromdate,today,ruleList.get(0).getLow_value(),ruleList.get(0).getHigh_value());
	}*/
	@SuppressWarnings("unchecked")
	public List<Transaction> getTranrefNo(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where tran_ref_no  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getTrandate(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where tran_date  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getTranId(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where tran_id  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getPtranId(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where part_tran_id  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getPtrantype(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where part_tran_type  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getTranamt(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where tran_amt  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Transaction> getTranstatus(String CustId) {
		List<Transaction> list = (List<Transaction>) sessionFactory.getCurrentSession()
				.createQuery(" from Transaction where tran_status  ='" + CustId + "'").getResultList();
		return list;
	}
}