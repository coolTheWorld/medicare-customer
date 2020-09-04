package yibao.yiwei.common.handler.code;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:验证码认证接口
 * @author: sunshy
 * @create: 2020-09-04
 **/
public abstract class CodeHandler {
    protected CodeHandler next;

    public void setNext(CodeHandler next) {
        this.next = next;
    }

    /**
     * 验证码认证处理
     * @return
     */
    public abstract boolean handler(Map<String,Object> map, RedirectAttributes ra);
}
