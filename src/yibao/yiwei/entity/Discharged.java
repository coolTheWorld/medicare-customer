package yibao.yiwei.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 出院记录
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_DISCHARGED", schema = "YIWEI")
public class Discharged implements java.io.Serializable {

	private String dcId;// 主键
	private String cusId;// 客户id
	private String cusDareway;// 医院编码
	private String caseNo;// 病案号 1
	private String hospNo;// 住院号 2
	private String siPtsidcard;// 身份证号3
	private String siPtshealthcard;// 医保卡号4
	private String siPtsname;// 患者姓名5
	private Integer siPtssex;// 性别6
	private String siPtsage;// 年龄7
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date dcIndate;// 入院日期8
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date dcOutdate;// 出院日期9
	private Integer dcHospdays;// 住院天数10
	private String dcOutype;// 出院方式11
	private Integer dcHosptimes;// 住院次数12
	private String dcDiagcode;// 出院诊断编码13
	private String dcDiagname;// 出院诊断名称14
	private String dcDoctorcode;// 医师编码15
	private String dcDoctorname;// 医师姓名16
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date dcPickime;// 采集日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date dcAddtime;// 创建日期

	@GenericGenerator(name = "Discharged_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "Discharged_id")
	@Column(name = "DC_ID", unique = true, nullable = false, length = 32)
	public String getDcId() {
		return this.dcId;
	}

	public void setDcId(String dcId) {
		this.dcId = dcId;
	}

	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusDareway() {
		return this.cusDareway;
	}

	public void setCusDareway(String cusDareway) {
		this.cusDareway = cusDareway;
	}

	@Column(name = "CASE_NO", length = 32)
	public String getCaseNo() {
		return this.caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	@Column(name = "HOSP_NO", length = 32)
	public String getHospNo() {
		return this.hospNo;
	}

	public void setHospNo(String hospNo) {
		this.hospNo = hospNo;
	}

	@Column(name = "SI_PTSIDCARD", length = 32)
	public String getSiPtsidcard() {
		return this.siPtsidcard;
	}

	public void setSiPtsidcard(String siPtsidcard) {
		this.siPtsidcard = siPtsidcard;
	}

	@Column(name = "SI_PTSHEALTHCARD", length = 32)
	public String getSiPtshealthcard() {
		return this.siPtshealthcard;
	}

	public void setSiPtshealthcard(String siPtshealthcard) {
		this.siPtshealthcard = siPtshealthcard;
	}

	@Column(name = "SI_PTSNAME", length = 32)
	public String getSiPtsname() {
		return this.siPtsname;
	}

	public void setSiPtsname(String siPtsname) {
		this.siPtsname = siPtsname;
	}

	@Column(name = "SI_PTSSEX", precision = 22, scale = 0)
	public Integer getSiPtssex() {
		return this.siPtssex;
	}

	public void setSiPtssex(Integer siPtssex) {
		this.siPtssex = siPtssex;
	}

	@Column(name = "SI_PTSAGE", length = 32)
	public String getSiPtsage() {
		return this.siPtsage;
	}

	public void setSiPtsage(String siPtsage) {
		this.siPtsage = siPtsage;
	}

	@Column(name = "DC_INDATE", length = 7)
	public Date getDcIndate() {
		return this.dcIndate;
	}

	public void setDcIndate(Date dcIndate) {
		this.dcIndate = dcIndate;
	}

	@Column(name = "DC_OUTDATE", length = 7)
	public Date getDcOutdate() {
		return this.dcOutdate;
	}

	public void setDcOutdate(Date dcOutdate) {
		this.dcOutdate = dcOutdate;
	}

	@Column(name = "DC_HOSPDAYS", precision = 22, scale = 0)
	public Integer getDcHospdays() {
		return this.dcHospdays;
	}

	public void setDcHospdays(Integer dcHospdays) {
		this.dcHospdays = dcHospdays;
	}

	@Column(name = "DC_OUTYPE", length = 32)
	public String getDcOutype() {
		return this.dcOutype;
	}

	public void setDcOutype(String dcOutype) {
		this.dcOutype = dcOutype;
	}

	@Column(name = "DC_HOSPTIMES", precision = 22, scale = 0)
	public Integer getDcHosptimes() {
		return this.dcHosptimes;
	}

	public void setDcHosptimes(Integer dcHosptimes) {
		this.dcHosptimes = dcHosptimes;
	}

	@Column(name = "DC_DIAGCODE", length = 32)
	public String getDcDiagcode() {
		return this.dcDiagcode;
	}

	public void setDcDiagcode(String dcDiagcode) {
		this.dcDiagcode = dcDiagcode;
	}

	@Column(name = "DC_DIAGNAME", length = 120)
	public String getDcDiagname() {
		return this.dcDiagname;
	}

	public void setDcDiagname(String dcDiagname) {
		this.dcDiagname = dcDiagname;
	}

	@Column(name = "DC_DOCTORCODE", length = 32)
	public String getDcDoctorcode() {
		return this.dcDoctorcode;
	}

	public void setDcDoctorcode(String dcDoctorcode) {
		this.dcDoctorcode = dcDoctorcode;
	}

	@Column(name = "DC_DOCTORNAME", length = 32)
	public String getDcDoctorname() {
		return this.dcDoctorname;
	}

	public void setDcDoctorname(String dcDoctorname) {
		this.dcDoctorname = dcDoctorname;
	}

	@Column(name = "DC_PICKIME", length = 7)
	public Date getDcPickime() {
		return this.dcPickime;
	}

	public void setDcPickime(Date dcPickime) {
		this.dcPickime = dcPickime;
	}

	@Column(name = "DC_ADDTIME", length = 7)
	public Date getDcAddtime() {
		return this.dcAddtime;
	}

	public void setDcAddtime(Date dcAddtime) {
		this.dcAddtime = dcAddtime;
	}

}