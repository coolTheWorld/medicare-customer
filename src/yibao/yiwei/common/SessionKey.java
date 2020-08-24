package yibao.yiwei.common;

/**
 * session的key
 * @author sunshy
 *
 */
public enum SessionKey {
	RANDOM_CODE("randomCode"),//随机数
	USER("user");//用户session
	private String value;
	
	private SessionKey(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
