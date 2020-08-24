package yibao.yiwei.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 大华平台血透 康复 治疗记录
 * @author Administrator
 *
 */

public class TreatmentDetailDto {
	
	private BigDecimal RN;    //行号
	private String TMID;    //记录ID
    private String PLATNAME;     //大华平台对应名称
    private String USRNAME;   //姓名
    private String SEX;    //性别
    private String CARDID;     //身份证号
    private String FCHANNELCODE;  //设备通道 进
    private String FLIBURL;  //人脸底库图 进
    private String FFACEMINURL;//人脸特写 进
    private String FIMAGEURL;//场景图 进
    private String FFEATURES; //人脸特征 进
	private String FSIMILARITY; //相似度 进
    private String LCHANNELCODE;//设备通道 出
    private String LLIBURL;    //脸底库图 出
    private String LFACEMINURL;   //人脸特写 出
    private String LIMAGEURL;     //场景图 出
    private String LFEATURES; //人脸特征 出
    private String LSIMILARITY;   //相似度 出
    
    private Timestamp TMTimestamp;   //治疗日期
	//@TimestampTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp TMSTART;   //首次识别日期
	//@TimestampTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
    private Timestamp TMEND;       //最后识别日期
    private BigDecimal TMINTERVAL; //时间间隔 分钟
    
    
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getTMID() {
		return TMID;
	}
	public void setTMID(String tMID) {
		TMID = tMID;
	}
	public String getPLATNAME() {
		return PLATNAME;
	}
	public void setPLATNAME(String pLATNAME) {
		PLATNAME = pLATNAME;
	}
	public String getUSRNAME() {
		return USRNAME;
	}
	public void setUSRNAME(String uSRNAME) {
		USRNAME = uSRNAME;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getCARDID() {
		return CARDID;
	}
	public void setCARDID(String cARDID) {
		CARDID = cARDID;
	}
	public String getFCHANNELCODE() {
		return FCHANNELCODE;
	}
	public void setFCHANNELCODE(String fCHANNELCODE) {
		FCHANNELCODE = fCHANNELCODE;
	}
	public String getFLIBURL() {
		return FLIBURL;
	}
	public void setFLIBURL(String fLIBURL) {
		FLIBURL = fLIBURL;
	}
	public String getFFACEMINURL() {
		return FFACEMINURL;
	}
	public void setFFACEMINURL(String fFACEMINURL) {
		FFACEMINURL = fFACEMINURL;
	}
	public String getFIMAGEURL() {
		return FIMAGEURL;
	}
	public void setFIMAGEURL(String fIMAGEURL) {
		FIMAGEURL = fIMAGEURL;
	}
	public String getFFEATURES() {
		return FFEATURES;
	}
	public void setFFEATURES(String fFEATURES) {
		FFEATURES = fFEATURES;
	}
	public String getFSIMILARITY() {
		return FSIMILARITY;
	}
	public void setFSIMILARITY(String fSIMILARITY) {
		FSIMILARITY = fSIMILARITY;
	}
	public String getLCHANNELCODE() {
		return LCHANNELCODE;
	}
	public void setLCHANNELCODE(String lCHANNELCODE) {
		LCHANNELCODE = lCHANNELCODE;
	}
	public String getLLIBURL() {
		return LLIBURL;
	}
	public void setLLIBURL(String lLIBURL) {
		LLIBURL = lLIBURL;
	}
	public String getLFACEMINURL() {
		return LFACEMINURL;
	}
	public void setLFACEMINURL(String lFACEMINURL) {
		LFACEMINURL = lFACEMINURL;
	}
	public String getLIMAGEURL() {
		return LIMAGEURL;
	}
	public void setLIMAGEURL(String lIMAGEURL) {
		LIMAGEURL = lIMAGEURL;
	}
	public String getLFEATURES() {
		return LFEATURES;
	}
	public void setLFEATURES(String lFEATURES) {
		LFEATURES = lFEATURES;
	}
	public String getLSIMILARITY() {
		return LSIMILARITY;
	}
	public void setLSIMILARITY(String lSIMILARITY) {
		LSIMILARITY = lSIMILARITY;
	}
	public Timestamp getTMTimestamp() {
		return TMTimestamp;
	}
	public void setTMTimestamp(Timestamp tMTimestamp) {
		TMTimestamp = tMTimestamp;
	}

	public Timestamp getTMSTART() {
		return TMSTART; 
	}
	public void setTMSTART(Timestamp tMSTART) {
		TMSTART = tMSTART;
	}
	public Timestamp getTMEND() {
		return TMEND;
	}
	public void setTMEND(Timestamp tMEND) {
		TMEND = tMEND;
	}
	public BigDecimal getTMINTERVAL() {
		return TMINTERVAL;
	}
	public void setTMINTERVAL(BigDecimal tMINTERVAL) {
		TMINTERVAL = tMINTERVAL;
	}
    

	
}
