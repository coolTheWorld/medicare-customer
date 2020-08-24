package yibao.yiwei.common;

public enum FilePath {
	FEEDBACK("/var/customer/feedback");

	private String value;

	private FilePath(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
