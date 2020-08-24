package yibao.yiwei.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 医嘱执行记录
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_ORDERPERFORM", schema = "YIWEI")
public class Orderperform implements java.io.Serializable {

	private String doId;//主键
	private String cusId;//客户id
	private String cusPid;//客户上级id
	private String cusDareway;//医院编码
	private String caseNo;//病案号1
	private String hospNo;//住院号2
	private String doNo;//医嘱号3
	private String doType;//医嘱类型4
	private String itemCode;//项目编码5
	private String itemName;//项目名称6
	private Double itemPrice;//单价7
	private String itemFreq;//频次8
	private String itemAmount;//用量9
	private String itmeRoute;//用法10
	private String doPerform;//执行护士编码11
	private String doPerformname;//执行护士姓名12
	private Date doPerformtime;//执行日期13
	private Date doPicktime;//采集日期
	private Date doAddtime;//创建日期

	@GenericGenerator(name = "OrderperformId", strategy = "guid")
	@Id
	@GeneratedValue(generator = "OrderperformId")
	@Column(name = "DO_ID", unique = true, nullable = false, length = 32)
	public String getDoId() {
		return this.doId;
	}

	public void setDoId(String doId) {
		this.doId = doId;
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

	@Column(name = "DO_NO", length = 32)
	public String getDoNo() {
		return this.doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	@Column(name = "DO_TYPE", length = 32)
	public String getDoType() {
		return this.doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	@Column(name = "ITEM_CODE", length = 32)
	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "ITEM_NAME", length = 120)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "ITEM_PRICE", precision = 8)
	public Double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Column(name = "ITEM_FREQ", length = 32)
	public String getItemFreq() {
		return this.itemFreq;
	}

	public void setItemFreq(String itemFreq) {
		this.itemFreq = itemFreq;
	}

	@Column(name = "ITEM_AMOUNT", length = 32)
	public String getItemAmount() {
		return this.itemAmount;
	}

	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}

	@Column(name = "ITME_ROUTE", length = 32)
	public String getItmeRoute() {
		return this.itmeRoute;
	}

	public void setItmeRoute(String itmeRoute) {
		this.itmeRoute = itmeRoute;
	}

	@Column(name = "DO_PERFORM", length = 32)
	public String getDoPerform() {
		return this.doPerform;
	}

	public void setDoPerform(String doPerform) {
		this.doPerform = doPerform;
	}

	@Column(name = "DO_PERFORMNAME", length = 32)
	public String getDoPerformname() {
		return this.doPerformname;
	}

	public void setDoPerformname(String doPerformname) {
		this.doPerformname = doPerformname;
	}

	@Column(name = "DO_PERFORMTIME", length = 7)
	public Date getDoPerformtime() {
		return this.doPerformtime;
	}

	public void setDoPerformtime(Date doPerformtime) {
		this.doPerformtime = doPerformtime;
	}

	@Column(name = "DO_PICKTIME", length = 7)
	public Date getDoPicktime() {
		return this.doPicktime;
	}

	public void setDoPicktime(Date doPicktime) {
		this.doPicktime = doPicktime;
	}

	@Column(name = "DO_ADDTIME", length = 7)
	public Date getDoAddtime() {
		return this.doAddtime;
	}

	public void setDoAddtime(Date doAddtime) {
		this.doAddtime = doAddtime;
	}

}