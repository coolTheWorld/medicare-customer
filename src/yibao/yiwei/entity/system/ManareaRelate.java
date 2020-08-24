package yibao.yiwei.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员区划关联表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_MANAREA_RELATE", schema = "YIWEI")
public class ManareaRelate implements java.io.Serializable {

	private String muUserid;
	private String acAreacode;

	/**
	 * 定义管理员id muUserid 属性,作为标识属性的成员(联合主键B)
	 * @return
	 */
	@Id
	@Column(name = "MU_USERID", nullable = false, length = 32)
	public String getMuUserid() {
		return this.muUserid;
	}

	public void setMuUserid(String muUserid) {
		this.muUserid = muUserid;
	}

	/**
	 * 定义地区编码 acAreacode属性,作为标识属性的成员(联合主键A)
	 * @return
	 */
	@Id
	@Column(name = "AC_AREACODE", nullable = false, length = 6)
	public String getAcAreacode() {
		return this.acAreacode;
	}

	public void setAcAreacode(String acAreacode) {
		this.acAreacode = acAreacode;
	}

}