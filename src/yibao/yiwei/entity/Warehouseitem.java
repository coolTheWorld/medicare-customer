package yibao.yiwei.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import yibao.yiwei.utils.JacksonDateSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 入库信息
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_WAREHOUSEITEM", schema = "YIWEI")
public class Warehouseitem implements java.io.Serializable {

	private String wiId;// 入库ID
	private String cusId;// 客户ID
	private String cusParentid;// 客户上级ID
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date drugCollectdate;// 药品采集日期
	private String wiCode;// 入库编号1
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date wiDatetime;// 入库日期2
	private String wiType;// 入库类别3
	private String drugCode;// 项目编码(药品编码) 4
	private String drugName;// 项目名称(药品名称)5
	private Double drugNum;// 入库数量 6
	private Double drugPurchaseprice;// 采购价 7
	private String drugBatchno;// 生产批号 8
	private String drugMfrs;// 生产商 9
	private String drugMadein;// 产地 10
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date drugExpdate;// 有效期 11
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date drugMfgdate;// 生产日期 12
	private String drugEid;// 电子监管码 13
	private Double wiPrice;// 零售价14
	private String drugSpecification;// 规格15
	private String drugUnit;// 单位16
	private String drugHcscode;// 本位码(医保编码)17
	private String wiWhcode;// 库房编码18
	private String wiWhname;// 库房名称19
	private String wiLocation;// 货位/货架号20
	private String drugBuyer;// 采购员编码 21
	private String drugExaminer;// 验收员编码 23
	private String drugBuyername;// 采购员姓名22
	private String drugExaminername;// 验收员姓名24
	private String cusDareway;// 医院编码
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date wiAddtime;// 创建日期

	@GenericGenerator(name = "Warehouseitem_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "Warehouseitem_id")
	@Column(name = "WI_ID", unique = true, nullable = false, length = 32)
	public String getWiId() {
		return this.wiId;
	}

	public void setWiId(String wiId) {
		this.wiId = wiId;
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

	@Column(name = "DRUG_PURCHASEPRICE", precision = 126, scale = 0)
	public Double getDrugPurchaseprice() {
		return this.drugPurchaseprice;
	}

	public void setDrugPurchaseprice(Double drugPurchaseprice) {
		this.drugPurchaseprice = drugPurchaseprice;
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

	@Column(name = "DRUG_BUYER", length = 32)
	public String getDrugBuyer() {
		return this.drugBuyer;
	}

	public void setDrugBuyer(String drugBuyer) {
		this.drugBuyer = drugBuyer;
	}

	@Column(name = "DRUG_EXAMINER", length = 32)
	public String getDrugExaminer() {
		return this.drugExaminer;
	}

	public void setDrugExaminer(String drugExaminer) {
		this.drugExaminer = drugExaminer;
	}

	@Column(name = "DRUG_EID", length = 32)
	public String getDrugEid() {
		return this.drugEid;
	}

	public void setDrugEid(String drugEid) {
		this.drugEid = drugEid;
	}

	@Column(name = "DRUG_COLLECTDATE", length = 7)
	public Date getDrugCollectdate() {
		return this.drugCollectdate;
	}

	public void setDrugCollectdate(Date drugCollectdate) {
		this.drugCollectdate = drugCollectdate;
	}

	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusDareway() {
		return this.cusDareway;
	}

	public void setCusDareway(String cusDareway) {
		this.cusDareway = cusDareway;
	}

	@Column(name = "WI_TYPE", length = 32)
	public String getWiType() {
		return this.wiType;
	}

	public void setWiType(String wiType) {
		this.wiType = wiType;
	}

	@Column(name = "DRUG_NAME", length = 200)
	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	@Column(name = "WI_CODE", length = 32)
	public String getWiCode() {
		return this.wiCode;
	}

	public void setWiCode(String wiCode) {
		this.wiCode = wiCode;
	}

	@Column(name = "WI_DATETIME", length = 7)
	public Date getWiDatetime() {
		return this.wiDatetime;
	}

	public void setWiDatetime(Date wiDatetime) {
		this.wiDatetime = wiDatetime;
	}

	@Column(name = "WI_PRICE", precision = 126, scale = 0)
	public Double getWiPrice() {
		return this.wiPrice;
	}

	public void setWiPrice(Double wiPrice) {
		this.wiPrice = wiPrice;
	}

	@Column(name = "DRUG_SPECIFICATION", length = 200)
	public String getDrugSpecification() {
		return this.drugSpecification;
	}

	public void setDrugSpecification(String drugSpecification) {
		this.drugSpecification = drugSpecification;
	}

	@Column(name = "DRUG_UNIT", length = 32)
	public String getDrugUnit() {
		return this.drugUnit;
	}

	public void setDrugUnit(String drugUnit) {
		this.drugUnit = drugUnit;
	}

	@Column(name = "DRUG_HCSCODE", length = 32)
	public String getDrugHcscode() {
		return this.drugHcscode;
	}

	public void setDrugHcscode(String drugHcscode) {
		this.drugHcscode = drugHcscode;
	}

	@Column(name = "WI_WHCODE", length = 32)
	public String getWiWhcode() {
		return this.wiWhcode;
	}

	public void setWiWhcode(String wiWhcode) {
		this.wiWhcode = wiWhcode;
	}

	@Column(name = "WI_WHNAME", length = 200)
	public String getWiWhname() {
		return this.wiWhname;
	}

	public void setWiWhname(String wiWhname) {
		this.wiWhname = wiWhname;
	}

	@Column(name = "WI_LOCATION", length = 200)
	public String getWiLocation() {
		return this.wiLocation;
	}

	public void setWiLocation(String wiLocation) {
		this.wiLocation = wiLocation;
	}

	@Column(name = "DRUG_BUYERNAME", length = 32)
	public String getDrugBuyername() {
		return this.drugBuyername;
	}

	public void setDrugBuyername(String drugBuyername) {
		this.drugBuyername = drugBuyername;
	}

	@Column(name = "DRUG_EXAMINERNAME", length = 32)
	public String getDrugExaminername() {
		return this.drugExaminername;
	}

	public void setDrugExaminername(String drugExaminername) {
		this.drugExaminername = drugExaminername;
	}
	@JsonSerialize(using = JacksonDateSerializer.class)
	@Column(name = "WI_ADDTIME", length = 7)
	public Date getWiAddtime() {
		return this.wiAddtime;
	}

	public void setWiAddtime(Date wiAddtime) {
		this.wiAddtime = wiAddtime;
	}

}