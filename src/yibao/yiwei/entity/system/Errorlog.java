package yibao.yiwei.entity.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import yibao.yiwei.utils.JacksonDateHMSSerializer;

/**
 * 错误日志
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_ERRORLOG", schema = "YIWEI")
public class Errorlog implements java.io.Serializable {

	private String errId; // 主键
	private String cusId; // 定点id
	private String cusName; // 定点名称
	private String cusPid; // 上级id
	private String errType; // 错误类型
	private String errLog; // 错误日志
	private String common; // 备注
	private Date errAddtime; // 创建时间
	private String errFileflag; // 文件类型
	private String errFilepath; // 文件路径名称

	@GenericGenerator(name = "ErrorlogId", strategy = "guid")
	@Id
	@GeneratedValue(generator = "ErrorlogId")
	@Column(name = "ERR_ID", unique = true, nullable = false, length = 32)
	public String getErrId() {
		return this.errId;
	}

	public void setErrId(String errId) {
		this.errId = errId;
	}

	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_NAME", length = 128)
	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "CUS_PID", length = 32)
	public String getCusPid() {
		return this.cusPid;
	}

	public void setCusPid(String cusPid) {
		this.cusPid = cusPid;
	}

	@Column(name = "ERR_TYPE", length = 32)
	public String getErrType() {
		return this.errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	@Column(name = "ERR_LOG", length = 2000)
	public String getErrLog() {
		return this.errLog;
	}

	public void setErrLog(String errLog) {
		this.errLog = errLog;
	}

	@Column(name = "COMMON", length = 400)
	public String getCommon() {
		return this.common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	@JsonSerialize(using = JacksonDateHMSSerializer.class)
	@Column(name = "ERR_ADDTIME", length = 7)
	public Date getErrAddtime() {
		return this.errAddtime;
	}

	public void setErrAddtime(Date errAddtime) {
		this.errAddtime = errAddtime;
	}

	@Column(name = "ERR_FILEFLAG", length = 32)
	public String getErrFileflag() {
		return this.errFileflag;
	}

	public void setErrFileflag(String errFileflag) {
		this.errFileflag = errFileflag;
	}

	@Column(name = "ERR_FILEPATH", length = 200)
	public String getErrFilepath() {
		return this.errFilepath;
	}

	public void setErrFilepath(String errFilepath) {
		this.errFilepath = errFilepath;
	}

}