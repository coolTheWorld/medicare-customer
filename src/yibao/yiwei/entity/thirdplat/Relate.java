package yibao.yiwei.entity.thirdplat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大华平台进销存对应关系
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_DHPLAT_RELATE", schema = "YIWEI")
public class Relate implements java.io.Serializable {
	
	private String cusid;//注册信息id
	private String cusname;//注册信息名称
	private String cusdareway;//地玮定点编号
	private String plantname;//大华平台对应名称
	private String custype;//定点类型 1血透 2康复 3其他
	private Integer settreatmin;//阀值 最小治疗时长
	private Integer ishandle;
	@GenericGenerator(name = "cusid", strategy = "guid")
	@Id
	@GeneratedValue(generator = "cusid")
	@Column(name = "CUS_ID", unique = true, nullable = false, length = 32)
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	@Column(name = "CUS_NAME", length = 400)
	public String getCusname() {
		return cusname;
	}
	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusdareway() {
		return cusdareway;
	}
	public void setCusdareway(String cusdareway) {
		this.cusdareway = cusdareway;
	}
	@Column(name = "PLATNAME", length = 400)
	public String getPlantname() {
		return plantname;
	}
	public void setPlantname(String plantname) {
		this.plantname = plantname;
	}
	@Column(name = "CUS_TYPE", length = 10)
	public String getCustype() {
		return custype;
	}
	public void setCustype(String custype) {
		this.custype = custype;
	}
	@Column(name = "SET_TREAT_MIN", length = 10)
	public Integer getSettreatmin() {
		return settreatmin;
	}
	public void setSettreatmin(Integer settreatmin) {
		this.settreatmin = settreatmin;
	}
	@Column(name = "IS_HANDLE", length = 10)
	public Integer getIshandle() {
		return ishandle;
	}
	public void setIshandle(Integer ishandle) {
		this.ishandle = ishandle;
	}
	@Override
	public String toString() {
		return "Relate [cusid=" + cusid + ", cusname=" + cusname + ", cusdareway=" + cusdareway + ", plantname="
				+ plantname + ", custype=" + custype + ", settreatmin=" + settreatmin + ", ishandle=" + ishandle + "]";
	}
	
	
}
