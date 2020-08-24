package yibao.yiwei.dto;

import java.math.BigDecimal;

public class TreatmentDto {
	
	private String NAME;//定点名称
	private BigDecimal NUM;//治疗人次
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public BigDecimal getNUM() {
		return NUM;
	}
	public void setNUM(BigDecimal nUM) {
		NUM = nUM;
	}
	
	
	
}
