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
 * 出库信息
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_DELIVERYITEM", schema = "YIWEI")
public class Deliveryitem implements java.io.Serializable {

	private String diId;// 出库id
	private String cusId;// 客户id
	private String cusParentid; // 客户上级ID
	private String diNo;// 出库编号 1
	private String diType;// 出库类别2
	private String diReason;// 出库原因描述 3
	private String drugCode;// 项目编码(药品编码) 4
	private String drugName;// 项目名称(药品名称)5
	private Double drugNum;// 出库数量6
	private String diSpecification;// 规格7
	private String drugBatchno;// 生产批号 8
	private String drugMfrs;// 生产商 9
	private String drugMadein;// 产地 10
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date drugExpdate;// 有效期 11
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date drugMfgdate;// 生产日期 12
	private String drugEid;// 电子监管码 13
	private String diHcscode;// 本位码（医保编码）14
	private String diWhcode;// 库房编码15
	private String diWhname;// 库房名称16
	private String diLocation;// 货位/货架号17
	private String diOpcode;// 操作员编码18
	private String diOpname;// 操作员姓名19
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diOpdatetime;// 出库日期20
	private String diUnit;// 单位 21
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diPicktime;// 采集日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date diAddtime;// 创建日期

	@GenericGenerator(name = "Deliveryitem_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "Deliveryitem_id")
	@Column(name = "DI_ID", unique = true, nullable = false, length = 32)
	public String getDiId() {
		return this.diId;
	}

	public void setDiId(String diId) {
		this.diId = diId;
	}

	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_PARENTID", length = 32)
	public String getCusParentid() {
		return this.cusParentid;
	}

	public void setCusParentid(String cusParentid) {
		this.cusParentid = cusParentid;
	}

	@Column(name = "DI_NO", length = 32)
	public String getDiNo() {
		return this.diNo;
	}

	public void setDiNo(String diNo) {
		this.diNo = diNo;
	}

	@Column(name = "DI_REASON", length = 32)
	public String getDiReason() {
		return this.diReason;
	}

	public void setDiReason(String diReason) {
		this.diReason = diReason;
	}

	@Column(name = "DRUG_CODE", length = 32)
	public String getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	@Column(name = "DRUG_NUM", precision = 126, scale = 0)
	public Double getDrugNum() {
		return this.drugNum;
	}

	public void setDrugNum(Double drugNum) {
		this.drugNum = drugNum;
	}

	@Column(name = "DRUG_BATCHNO", length = 32)
	public String getDrugBatchno() {
		return this.drugBatchno;
	}

	public void setDrugBatchno(String drugBatchno) {
		this.drugBatchno = drugBatchno;
	}

	@Column(name = "DRUG_MFRS", length = 120)
	public String getDrugMfrs() {
		return this.drugMfrs;
	}

	public void setDrugMfrs(String drugMfrs) {
		this.drugMfrs = drugMfrs;
	}

	@Column(name = "DRUG_MADEIN", length = 120)
	public String getDrugMadein() {
		return this.drugMadein;
	}

	public void setDrugMadein(String drugMadein) {
		this.drugMadein = drugMadein;
	}

	@Column(name = "DRUG_MFGDATE", length = 7)
	public Date getDrugMfgdate() {
		return this.drugMfgdate;
	}

	public void setDrugMfgdate(Date drugMfgdate) {
		this.drugMfgdate = drugMfgdate;
	}

	@Column(name = "DRUG_EXPDATE", length = 7)
	public Date getDrugExpdate() {
		return this.drugExpdate;
	}

	public void setDrugExpdate(Date drugExpdate) {
		this.drugExpdate = drugExpdate;
	}

	@Column(name = "DRUG_EID", length = 32)
	public String getDrugEid() {
		return this.drugEid;
	}

	public void setDrugEid(String drugEid) {
		this.drugEid = drugEid;
	}

	@Column(name = "DI_TYPE", length = 32)
	public String getDiType() {
		return this.diType;
	}

	public void setDiType(String diType) {
		this.diType = diType;
	}

	@Column(name = "DRUG_NAME", length = 120)
	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	@Column(name = "DI_SPECIFICATION", length = 200)
	public String getDiSpecification() {
		return this.diSpecification;
	}

	public void setDiSpecification(String diSpecification) {
		this.diSpecification = diSpecification;
	}

	@Column(name = "DI_HCSCODE", length = 32)
	public String getDiHcscode() {
		return this.diHcscode;
	}

	public void setDiHcscode(String diHcscode) {
		this.diHcscode = diHcscode;
	}

	@Column(name = "DI_WHCODE", length = 32)
	public String getDiWhcode() {
		return this.diWhcode;
	}

	public void setDiWhcode(String diWhcode) {
		this.diWhcode = diWhcode;
	}

	@Column(name = "DI_WHNAME", length = 120)
	public String getDiWhname() {
		return this.diWhname;
	}

	public void setDiWhname(String diWhname) {
		this.diWhname = diWhname;
	}

	@Column(name = "DI_LOCATION", length = 32)
	public String getDiLocation() {
		return this.diLocation;
	}

	public void setDiLocation(String diLocation) {
		this.diLocation = diLocation;
	}

	@Column(name = "DI_OPCODE", length = 32)
	public String getDiOpcode() {
		return this.diOpcode;
	}

	public void setDiOpcode(String diOpcode) {
		this.diOpcode = diOpcode;
	}

	@Column(name = "DI_OPNAME", length = 32)
	public String getDiOpname() {
		return this.diOpname;
	}

	public void setDiOpname(String diOpname) {
		this.diOpname = diOpname;
	}

	@Column(name = "DI_OPDATETIME", length = 7)
	public Date getDiOpdatetime() {
		return this.diOpdatetime;
	}

	public void setDiOpdatetime(Date diOpdatetime) {
		this.diOpdatetime = diOpdatetime;
	}

	@Column(name = "DI_PICKTIME", length = 7)
	public Date getDiPicktime() {
		return this.diPicktime;
	}

	public void setDiPicktime(Date diPicktime) {
		this.diPicktime = diPicktime;
	}

	@Column(name = "DI_ADDTIME", length = 7)
	public Date getDiAddtime() {
		return this.diAddtime;
	}

	public void setDiAddtime(Date diAddtime) {
		this.diAddtime = diAddtime;
	}

	@Column(name = "DI_UNIT", length = 32)
	public String getDiUnit() {
		return diUnit;
	}

	public void setDiUnit(String diUnit) {
		this.diUnit = diUnit;
	}

}