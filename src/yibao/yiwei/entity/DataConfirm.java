package yibao.yiwei.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import yibao.yiwei.common.ConfirmInit;
import yibao.yiwei.common.NotEmpty;

/**
 * 数据确认
 * @author sunshy
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_DATA_CONFIRM", schema = "YIWEI")
public class DataConfirm {
	private String confirmId;//主键
	private String cusId;//customerId
	//@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date recordDate;//确认哪天的数据
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;//确认时间
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	//private String createTime;
	@ConfirmInit
	@NotEmpty
	private String recordWarehouse;//入库记录
	@ConfirmInit
	@NotEmpty
	private String recordDelivery;//出库记录
	@ConfirmInit
	@NotEmpty
	private String recordSales;//销售记录
	@ConfirmInit
	@NotEmpty
	private String recordClinicrecords;//门诊记录
	@ConfirmInit
	@NotEmpty
	private String recordPrescribe;//处方记录
	@ConfirmInit
	@NotEmpty
	private String recordHospitalized;//住院记录
	@ConfirmInit
	@NotEmpty
	private String recordDischarged;//出院记录
	@ConfirmInit
	@NotEmpty
	private String recordItemstock;//库存记录
	@ConfirmInit
	@NotEmpty
	private String recordBak;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak1;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak2;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak3;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak4;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak5;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak6;//备用字段
	@ConfirmInit
	@NotEmpty
	private String recordBak7;//备用字段
	
	@Id
	@GenericGenerator(name = "confirmId", strategy = "guid")
	@GeneratedValue(generator = "confirmId")
	@Column(name = "CONFIRM_ID", unique = true, nullable = false, length = 32)
	public String getConfirmId() {
		return confirmId;
	}
	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}
	
	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	
	@Column(name = "RECORD_DATE", length = 7)
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "RECORD_WAREHOUSE", length = 2)
	public String getRecordWarehouse() {
		return recordWarehouse;
	}
	public void setRecordWarehouse(String recordWarehouse) {
		this.recordWarehouse = recordWarehouse;
	}
	
	@Column(name = "RECORD_DELIVERY", length = 2)
	public String getRecordDelivery() {
		return recordDelivery;
	}
	public void setRecordDelivery(String recordDelivery) {
		this.recordDelivery = recordDelivery;
	}
	
	@Column(name = "RECORD_SALES", length = 2)
	public String getRecordSales() {
		return recordSales;
	}
	public void setRecordSales(String recordSales) {
		this.recordSales = recordSales;
	}
	
	@Column(name = "RECORD_CLINICRECORDS", length = 2)
	public String getRecordClinicrecords() {
		return recordClinicrecords;
	}
	public void setRecordClinicrecords(String recordClinicrecords) {
		this.recordClinicrecords = recordClinicrecords;
	}
	@Column(name = "RECORD_PRESCRIBE", length = 2)
	public String getRecordPrescribe() {
		return recordPrescribe;
	}
	public void setRecordPrescribe(String recordPrescribe) {
		this.recordPrescribe = recordPrescribe;
	}
	@Column(name = "RECORD_HOSPITALIZED", length = 2)
	public String getRecordHospitalized() {
		return recordHospitalized;
	}
	public void setRecordHospitalized(String recordHospitalized) {
		this.recordHospitalized = recordHospitalized;
	}
	@Column(name = "RECORD_DISCHARGED", length = 2)
	public String getRecordDischarged() {
		return recordDischarged;
	}
	public void setRecordDischarged(String recordDischarged) {
		this.recordDischarged = recordDischarged;
	}
	@Column(name = "RECORD_ITEMSTOCK", length = 2)
	public String getRecordItemstock() {
		return recordItemstock;
	}
	public void setRecordItemstock(String recordItemstock) {
		this.recordItemstock = recordItemstock;
	}
	@Column(name = "RECORD_BAK", length = 2)
	public String getRecordBak() {
		return recordBak;
	}
	public void setRecordBak(String recordBak) {
		this.recordBak = recordBak;
	}
	@Column(name = "RECORD_BAK1", length = 2)
	public String getRecordBak1() {
		return recordBak1;
	}
	public void setRecordBak1(String recordBak1) {
		this.recordBak1 = recordBak1;
	}
	@Column(name = "RECORD_BAK2", length = 2)
	public String getRecordBak2() {
		return recordBak2;
	}
	public void setRecordBak2(String recordBak2) {
		this.recordBak2 = recordBak2;
	}
	@Column(name = "RECORD_BAK3", length = 2)
	public String getRecordBak3() {
		return recordBak3;
	}
	public void setRecordBak3(String recordBak3) {
		this.recordBak3 = recordBak3;
	}
	@Column(name = "RECORD_BAK4", length = 2)
	public String getRecordBak4() {
		return recordBak4;
	}
	public void setRecordBak4(String recordBak4) {
		this.recordBak4 = recordBak4;
	}
	@Column(name = "RECORD_BAK5", length = 2)
	public String getRecordBak5() {
		return recordBak5;
	}
	public void setRecordBak5(String recordBak5) {
		this.recordBak5 = recordBak5;
	}
	@Column(name = "RECORD_BAK6", length = 2)
	public String getRecordBak6() {
		return recordBak6;
	}
	public void setRecordBak6(String recordBak6) {
		this.recordBak6 = recordBak6;
	}
	@Column(name = "RECORD_BAK7", length = 2)
	public String getRecordBak7() {
		return recordBak7;
	}
	public void setRecordBak7(String recordBak7) {
		this.recordBak7 = recordBak7;
	}
//	public String getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(String createTime) {
//		this.createTime = createTime;
//	}
}
