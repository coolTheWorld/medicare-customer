package yibao.yiwei.common.handler.password;

import java.util.Map;

/**
 * @description:
 * @author: sunshy
 * @create: 2020-09-08
 **/
public abstract class PasswordHandler {
    protected PasswordHandler next;
    public void setNext(PasswordHandler next){
        this.next = next;
    }
    abstract void Handler(Map<String,Object> map);
}
