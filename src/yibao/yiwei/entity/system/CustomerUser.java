package yibao.yiwei.entity.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 定点信息用户表
 * 
 * @author sunshy
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_CUSTOMER_USER", schema = "YIWEI")
public class CustomerUser implements Serializable {

	private String userId; // 主键
	private String cusId; // 关联tbl_customer表主键
	private String userAccount;// 用户账号，关联tbl_customer表cus_dareway字段
	private String userPassword;// 用户密码
	private String userStatus;// 用户状态 0.初始化 1.已启用 -1.作废

	@Id
	@GenericGenerator(name = "userId", strategy = "guid")
	@GeneratedValue(generator = "userId")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "USER_ACCOUNT", length = 32)
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "USER_PASSWORD", length = 32)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_STATUS", length = 32)
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
