package yibao.yiwei.entity.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 文件上传预警统计(按最后上传日期)
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "COU_UPLOADFILEBYUPDATE", schema = "YIWEI")
public class CouUploadfileByDate implements java.io.Serializable {

	private String cufId; // 主键
	private String cusId; // 定点id
	private String cufFileflag; // 用作医院编码331067(测试)
	private String cusName; // 客户名称
	private String cufUpdate; // 上传时间(最后)
	private int cufRecordcount; //昨日上传项目总数
	private int cufFilecount; // 历史上传项目总数
	private Date cufCreatedate; // 创建日期
	private String cusFlag; // 定点标识：101单体药店， 102 连锁药店，201门诊 ，301医院

	@GenericGenerator(name = "CouUploadfileByDate_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "CouUploadfileByDate_id")
	@Column(name = "CUF_ID", unique = true, nullable = false, length = 32)
	public String getCufId() {
		return this.cufId;
	}

	public void setCufId(String cufId) {
		this.cufId = cufId;
	}

	@Column(name = "CUS_ID", length = 64)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUF_FILEFLAG", length = 6)
	public String getCufFileflag() {
		return this.cufFileflag;
	}

	public void setCufFileflag(String cufFileflag) {
		this.cufFileflag = cufFileflag;
	}

	@Column(name = "CUS_NAME", length = 200)
	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "CUF_UPDATE", length = 32)
	public String getCufUpdate() {
		return this.cufUpdate;
	}

	public void setCufUpdate(String cufUpdate) {
		this.cufUpdate = cufUpdate;
	}

	@Column(name = "CUF_FILECOUNT", precision = 22, scale = 0)
	public int getCufFilecount() {
		return this.cufFilecount;
	}

	public void setCufFilecount(int cufFilecount) {
		this.cufFilecount = cufFilecount;
	}

	@Column(name = "CUF_RECORDCOUNT", precision = 22, scale = 0)
	public int getCufRecordcount() {
		return this.cufRecordcount;
	}

	public void setCufRecordcount(int cufRecordcount) {
		this.cufRecordcount = cufRecordcount;
	}

	@Column(name = "CUF_CREATEDATE", nullable = false, length = 7)
	public Date getCufCreatedate() {
		return this.cufCreatedate;
	}

	public void setCufCreatedate(Date cufCreatedate) {
		this.cufCreatedate = cufCreatedate;
	}

	@Column(name = "CUS_FLAG", length = 3)
	public String getCusFlag() {
		return this.cusFlag;
	}

	public void setCusFlag(String cusFlag) {
		this.cusFlag = cusFlag;
	}

}