package yibao.yiwei.dto;

import java.math.BigDecimal;

public class TreatmentStatisticsDto {
	
	private String PLATNAME;//定点名称
	private BigDecimal TOTAL;//统计人次
	
	public String getPLATNAME() {
		return PLATNAME;
	}
	public void setPLATNAME(String pLATNAME) {
		PLATNAME = pLATNAME;
	}
	public BigDecimal getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(BigDecimal tOTAL) {
		TOTAL = tOTAL;
	}

	
}
