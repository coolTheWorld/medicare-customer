package yibao.yiwei.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限角色关联表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_PRIVILEGE_ROLE", schema = "YIWEI")
public class PrivilegeRole implements java.io.Serializable {

	private String priId;
	private String roId;
	
	@Id
	@Column(name = "PRI_ID")
	public String getPriId() {
		return priId;
	}
	
	public void setPriId(String priId) {
		this.priId = priId;
	}
	
	@Id
	@Column(name = "RO_ID")
	public String getRoId() {
		return roId;
	}
	
	public void setRoId(String roId) {
		this.roId = roId;
	}
}
