package yibao.yiwei.common.handler.code;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.handler.HandlerMapKey;
import yibao.yiwei.entity.system.CustomerUser;

import java.util.Map;

/**
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class ParamerConcreteHandler extends CodeHandler {


    /**
     * 验证前端传递的 userParam 和 code 是否为空
     *
     * @param map
     * @param ra
     * @return
     */
    @Override
    public boolean handler(Map<String, Object> map, RedirectAttributes ra) {
        CustomerUser userParam = (CustomerUser) map.get(HandlerMapKey.PARAM_USER_KEY.getValue());
        String code = (String) map.get(HandlerMapKey.PARAM_CODE_KEY.getValue());
        if (userParam == null || code == null) {
            return solve(ra);
        } else if (next != null) {
            return next.handler(map, ra);
        } else {
            return true;
        }
    }

    private boolean solve(RedirectAttributes ra) {
        ra.addFlashAttribute("mes", "必填项不能为空");
        return false;
    }
}
