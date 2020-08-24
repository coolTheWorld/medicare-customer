package yibao.yiwei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 问题反馈表
 * @author sunshy
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_PROBLEM_FEEDBACK", schema = "YIWEI")
public class ProblemFeedBack implements Serializable{
	
	private String proId;//主键
	private String cusId;//customerId
	private String proType;//问题反馈类型 001功能建议 002界面建议 003数据问题 004新的需求 005单据问题
	private String proContent;//问题反馈内容
	private String imgPath;//图片路径
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;//创建时间
	
	@Id
	@GenericGenerator(name = "proId", strategy = "guid")
	@GeneratedValue(generator = "proId")
	@Column(name = "PRO_ID", unique = true, nullable = false, length = 32)
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	
	@Column(name = "PRO_TYPE", length = 16)
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	
	@Column(name = "PRO_CONTENT", length = 300)
	public String getProContent() {
		return proContent;
	}
	public void setProContent(String proContent) {
		this.proContent = proContent;
	}
	
	@Column(name = "IMG_PATH", length = 256)
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
