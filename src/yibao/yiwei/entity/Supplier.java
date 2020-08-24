package yibao.yiwei.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 供应商/生产商信息
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_SUPPLIER", schema = "YIWEI")
public class Supplier implements java.io.Serializable {

	private String spId;// 主键
	private String cusId;// 客户ID
	private String cusParentid;// 客户上级ID 0单体
	private String spCode;// 1供应商/生产商编码
	private String spName;// 2供应商/生产商名称
	private String spContact;// 3联系人
	private String spPhone;// 4联系电话
	private String spCertificateno;// 5 GMP/GSP证书编号
	private String spQuality;// 6质量认证情况
	private String spCat;// 7企业类别
	private String spPostcode;//8邮政编码
	private String spEmail;//9电子邮件
	private String spAddress;//10详细地址
	private String spFax;//11企业传真
	private String spAnnex;// 12附件
	private String spRemark;// 13备注
	private String spStatus;//14状态
	private String cusDareway;//医院编码
	private String acCode;//统筹区划编码
	private String acName;//统筹区划名称
	private Date spPicktime;//采集时间
	private Date spAddtime;//创建时间

	@GenericGenerator(name = "Supplier_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "Supplier_id")
	@Column(name = "SP_ID", unique = true, nullable = false, length = 32)
	public String getSpId() {
		return this.spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
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

	@Column(name = "SP_CODE", length = 32)
	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	@Column(name = "SP_NAME", length = 150)
	public String getSpName() {
		return this.spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	@Column(name = "SP_CONTACT", length = 16)
	public String getSpContact() {
		return this.spContact;
	}

	public void setSpContact(String spContact) {
		this.spContact = spContact;
	}

	@Column(name = "SP_PHONE", length = 32)
	public String getSpPhone() {
		return this.spPhone;
	}

	public void setSpPhone(String spPhone) {
		this.spPhone = spPhone;
	}

	@Column(name = "SP_CERTIFICATENO", length = 32)
	public String getSpCertificateno() {
		return this.spCertificateno;
	}

	public void setSpCertificateno(String spCertificateno) {
		this.spCertificateno = spCertificateno;
	}

	@Column(name = "SP_QUALITY", length = 120)
	public String getSpQuality() {
		return this.spQuality;
	}

	public void setSpQuality(String spQuality) {
		this.spQuality = spQuality;
	}

	@Column(name = "SP_ANNEX", length = 120)
	public String getSpAnnex() {
		return this.spAnnex;
	}

	public void setSpAnnex(String spAnnex) {
		this.spAnnex = spAnnex;
	}

	@Column(name = "SP_REMARK")
	public String getSpRemark() {
		return this.spRemark;
	}

	public void setSpRemark(String spRemark) {
		this.spRemark = spRemark;
	}

	@Column(name = "SP_CAT", length = 32)
	public String getSpCat() {
		return this.spCat;
	}

	public void setSpCat(String spCat) {
		this.spCat = spCat;
	}

	@Column(name = "SP_POSTCODE", length = 32)
	public String getSpPostcode() {
		return this.spPostcode;
	}

	public void setSpPostcode(String spPostcode) {
		this.spPostcode = spPostcode;
	}

	@Column(name = "SP_EMAIL", length = 32)
	public String getSpEmail() {
		return this.spEmail;
	}

	public void setSpEmail(String spEmail) {
		this.spEmail = spEmail;
	}

	@Column(name = "SP_ADDRESS", length = 200)
	public String getSpAddress() {
		return this.spAddress;
	}

	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}

	@Column(name = "SP_FAX", length = 32)
	public String getSpFax() {
		return this.spFax;
	}

	public void setSpFax(String spFax) {
		this.spFax = spFax;
	}

	@Column(name = "SP_STATUS", length = 32)
	public String getSpStatus() {
		return this.spStatus;
	}

	public void setSpStatus(String spStatus) {
		this.spStatus = spStatus;
	}

	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusDareway() {
		return this.cusDareway;
	}

	public void setCusDareway(String cusDareway) {
		this.cusDareway = cusDareway;
	}

	@Column(name = "AC_CODE", length = 32)
	public String getAcCode() {
		return this.acCode;
	}

	public void setAcCode(String acCode) {
		this.acCode = acCode;
	}

	@Column(name = "AC_NAME", length = 32)
	public String getAcName() {
		return this.acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	@Column(name = "SP_PICKTIME", length = 7)
	public Date getSpPicktime() {
		return this.spPicktime;
	}

	public void setSpPicktime(Date spPicktime) {
		this.spPicktime = spPicktime;
	}

	@Column(name = "SP_ADDTIME", length = 7)
	public Date getSpAddtime() {
		return this.spAddtime;
	}

	public void setSpAddtime(Date spAddtime) {
		this.spAddtime = spAddtime;
	}

}