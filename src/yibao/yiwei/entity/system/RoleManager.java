package yibao.yiwei.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色用户关联表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_ROLE_MANAGER", schema = "YIWEI")
public class RoleManager implements java.io.Serializable {
	
	private String muUserid;
	private String roId;

	@Id
	@Column(name = "MU_USERID")
	public String getMuUserid() {
		return muUserid;
	}

	public void setMuUserid(String muUserid) {
		this.muUserid = muUserid;
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
