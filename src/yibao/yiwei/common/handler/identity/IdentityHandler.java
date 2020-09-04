package yibao.yiwei.common.handler.identity;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:身份认证接口
 * @author: sunshy
 * @create: 2020-09-04
 **/
public abstract class IdentityHandler {
    protected IdentityHandler next;

    public void setNext(IdentityHandler next) {
        this.next = next;
    }



    /**
     * 身份认证处理
     * @return
     */
    public abstract boolean handler(Map<String,Object> map, RedirectAttributes ra);
}
