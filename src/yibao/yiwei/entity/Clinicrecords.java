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
 * 诊断记录
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_CLINICRECORDS", schema = "YIWEI")
public class Clinicrecords implements java.io.Serializable {

	private String diagId;//主键
	private String cusId;//客户id
	private String cusPid;//客户上级id
	private String cusDareway;//医院编码
	private String diagNo;//门诊编号	1
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diagDatetime;//门诊日期	2
	private String diagDeptcode;//科室编码	3
	private String diagDeptname;//科室名称    4
	private String diagDoctor;//医师编码	5
	private String diagDoctorname;//医师姓名 6
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diagDoctortime;//诊断日期	7
	private String siPtsidcard;//身份证号	8
	private String siPtshealthcard;//医保卡号9
	private String siPtsname;// 患者姓名10
	private int siPtssex;//性别 11
	private String siPtsage;//年龄 12
	private String diagIcdcode;//诊断代码 13
	private String diagIcdname;//诊断名称14
	private String regType;//挂号类型	15
	private String diagType;//就诊方式	16
	private String secType;//险种类别	17
	private String diagPaytype;//医疗类别	18
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diagPicktime;//采集时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diagAddtime;//创建时间
	
	@GenericGenerator(name = "ClinicrecordsId", strategy = "guid")
	@Id
	@GeneratedValue(generator = "ClinicrecordsId")
	@Column(name = "DIAG_ID", unique = true, nullable = false, length = 32)
	public String getDiagId() {
		return this.diagId;
	}

	public void setDiagId(String diagId) {
		this.diagId = diagId;
	}

	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_PID", length = 32)
	public String getCusPid() {
		return this.cusPid;
	}

	public void setCusPid(String cusPid) {
		this.cusPid = cusPid;
	}

	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusDareway() {
		return this.cusDareway;
	}

	public void setCusDareway(String cusDareway) {
		this.cusDareway = cusDareway;
	}

	@Column(name = "DIAG_NO", length = 32)
	public String getDiagNo() {
		return this.diagNo;
	}

	public void setDiagNo(String diagNo) {
		this.diagNo = diagNo;
	}

	@Column(name = "DIAG_DATETIME", length = 7)
	public Date getDiagDatetime() {
		return this.diagDatetime;
	}

	public void setDiagDatetime(Date diagDatetime) {
		this.diagDatetime = diagDatetime;
	}

	@Column(name = "DIAG_DEPTCODE", length = 32)
	public String getDiagDeptcode() {
		return this.diagDeptcode;
	}

	public void setDiagDeptcode(String diagDeptcode) {
		this.diagDeptcode = diagDeptcode;
	}

	@Column(name = "DIAG_DEPTNAME", length = 32)
	public String getDiagDeptname() {
		return this.diagDeptname;
	}

	public void setDiagDeptname(String diagDeptname) {
		this.diagDeptname = diagDeptname;
	}

	@Column(name = "DIAG_DOCTOR", length = 32)
	public String getDiagDoctor() {
		return this.diagDoctor;
	}

	public void setDiagDoctor(String diagDoctor) {
		this.diagDoctor = diagDoctor;
	}

	@Column(name = "DIAG_DOCTORNAME", length = 32)
	public String getDiagDoctorname() {
		return this.diagDoctorname;
	}

	public void setDiagDoctorname(String diagDoctorname) {
		this.diagDoctorname = diagDoctorname;
	}

	@Column(name = "DIAG_DOCTORTIME", length = 7)
	public Date getDiagDoctortime() {
		return this.diagDoctortime;
	}

	public void setDiagDoctortime(Date diagDoctortime) {
		this.diagDoctortime = diagDoctortime;
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
	public int getSiPtssex() {
		return this.siPtssex;
	}

	public void setSiPtssex(int siPtssex) {
		this.siPtssex = siPtssex;
	}

	@Column(name = "SI_PTSAGE", length = 32)
	public String getSiPtsage() {
		return this.siPtsage;
	}

	public void setSiPtsage(String siPtsage) {
		this.siPtsage = siPtsage;
	}

	@Column(name = "DIAG_ICDCODE", length = 32)
	public String getDiagIcdcode() {
		return this.diagIcdcode;
	}

	public void setDiagIcdcode(String diagIcdcode) {
		this.diagIcdcode = diagIcdcode;
	}

	@Column(name = "DIAG_ICDNAME", length = 120)
	public String getDiagIcdname() {
		return this.diagIcdname;
	}

	public void setDiagIcdname(String diagIcdname) {
		this.diagIcdname = diagIcdname;
	}

	@Column(name = "REG_TYPE", length = 64)
	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	@Column(name = "DIAG_TYPE", length = 32)
	public String getDiagType() {
		return this.diagType;
	}

	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	@Column(name = "SEC_TYPE", length = 32)
	public String getSecType() {
		return this.secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	@Column(name = "DIAG_PAYTYPE", length = 32)
	public String getDiagPaytype() {
		return this.diagPaytype;
	}

	public void setDiagPaytype(String diagPaytype) {
		this.diagPaytype = diagPaytype;
	}

	@Column(name = "DIAG_PICKTIME", length = 7)
	public Date getDiagPicktime() {
		return this.diagPicktime;
	}

	public void setDiagPicktime(Date diagPicktime) {
		this.diagPicktime = diagPicktime;
	}

	@Column(name = "DIAG_ADDTIME", length = 7)
	public Date getDiagAddtime() {
		return this.diagAddtime;
	}

	public void setDiagAddtime(Date diagAddtime) {
		this.diagAddtime = diagAddtime;
	}

}