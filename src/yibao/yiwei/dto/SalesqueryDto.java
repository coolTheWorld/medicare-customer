package yibao.yiwei.dto;

import java.math.BigDecimal;

public class SalesqueryDto {
	
	private String DRUGCODE; 
	private String DRUGNAME; 
	private String DRUGSPECIFICATION; 
	private BigDecimal TOTALQUANTITY;  //总销量
	private BigDecimal TOTALAMOUNT;    //总金额
	private String DRUGMFRS;  
	private String DRUGMADEIN;
	private BigDecimal REMARK;
	private String CUSID;
	
	public String getCUSID() {
		return CUSID;
	}

	public void setCUSID(String cUSID) {
		CUSID = cUSID;
	}

	public BigDecimal getREMARK() {
		return REMARK;
	}

	public void setREMARK(BigDecimal rEMARK) {
		REMARK = rEMARK;
	}

	public SalesqueryDto() {
		
	}

	public String getDRUGCODE() {
		return DRUGCODE;
	}

	public void setDRUGCODE(String drugcode) {
		DRUGCODE = drugcode;
	}

	public String getDRUGNAME() {
		return DRUGNAME;
	}

	public void setDRUGNAME(String drugname) {
		DRUGNAME = drugname;
	}

	public String getDRUGSPECIFICATION() {
		return DRUGSPECIFICATION;
	}

	public void setDRUGSPECIFICATION(String drugspecification) {
		DRUGSPECIFICATION = drugspecification;
	}

	public BigDecimal getTOTALQUANTITY() {
		return TOTALQUANTITY;
	}

	public void setTOTALQUANTITY(BigDecimal totalquantity) {
		TOTALQUANTITY = totalquantity;
	}

	public BigDecimal getTOTALAMOUNT() {
		return TOTALAMOUNT;
	}

	public void setTOTALAMOUNT(BigDecimal totalamount) {
		TOTALAMOUNT = totalamount;
	}

	public String getDRUGMFRS() {
		return DRUGMFRS;
	}

	public void setDRUGMFRS(String drugmfrs) {
		DRUGMFRS = drugmfrs;
	}

	public String getDRUGMADEIN() {
		return DRUGMADEIN;
	}

	public void setDRUGMADEIN(String drugmadein) {
		DRUGMADEIN = drugmadein;
	}
	
}