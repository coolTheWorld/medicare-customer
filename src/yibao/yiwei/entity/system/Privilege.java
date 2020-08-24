package yibao.yiwei.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 权限表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_PRIVILEGE", schema = "YIWEI")
public class Privilege implements java.io.Serializable {
	
	private String priId;
	private String priName;//权限名称
	private String priUrl;//链接
	private String priIcon;//页面图标
	private Integer priOrder;//排序
	private String priStatus;//状态：1启用，0禁用
	private String priParent;//上级id
	private List<Privilege> children;
	
	@Id
	@GeneratedValue(generator = "privilege_id")
	@GenericGenerator(name = "privilege_id", strategy = "guid")
	@Column(name = "PRI_ID", unique = true, nullable = false, length = 32)
	public String getPriId() {
		return priId;
	}
	
	public void setPriId(String priId) {
		this.priId = priId;
	}
	
	@Column(name = "PRI_NAME")
	public String getPriName() {
		return priName;
	}
	
	public void setPriName(String priName) {
		this.priName = priName;
	}
	
	@Column(name = "PRI_URL")
	public String getPriUrl() {
		return priUrl;
	}
	
	public void setPriUrl(String priUrl) {
		this.priUrl = priUrl;
	}
	
	@Column(name = "PRI_ICON")
	public String getPriIcon() {
		return priIcon;
	}
	
	public void setPriIcon(String priIcon) {
		this.priIcon = priIcon;
	}
	
	@Column(name = "PRI_ORDER")
	public Integer getPriOrder() {
		return priOrder;
	}
	
	public void setPriOrder(Integer priOrder) {
		this.priOrder = priOrder;
	}
	
	@Column(name = "PRI_STATUS")
	public String getPriStatus() {
		return priStatus;
	}
	
	public void setPriStatus(String priStatus) {
		this.priStatus = priStatus;
	}
	
	@Column(name = "PRI_PARENT")
	public String getPriParent() {
		return priParent;
	}
	
	public void setPriParent(String priParent) {
		this.priParent = priParent;
	}
	
	@Transient
	public List<Privilege> getChildren() {
		return children;
	}
	
	public void setChildren(List<Privilege> children) {
		this.children = children;
	}
}