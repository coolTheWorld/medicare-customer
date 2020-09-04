package yibao.yiwei.common.handler;

public enum HandlerMapKey {

    PARAM_USER_KEY("userParam"),
    PARAM_CODE_KEY("code"),
    CONFIRM_CODE_KEY("confirmCode"),
    USER_KEY("user");

    private String value;
    private HandlerMapKey(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
