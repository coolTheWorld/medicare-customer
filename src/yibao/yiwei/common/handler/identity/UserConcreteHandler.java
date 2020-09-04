package yibao.yiwei.common.handler.identity;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.handler.HandlerMapKey;
import yibao.yiwei.entity.system.CustomerUser;

import java.util.HashMap;
import java.util.Map;


/**
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class UserConcreteHandler extends IdentityHandler {


    /**
     * 用户是否存在
     *
     * @param map
     * @param ra
     * @return
     */
    @Override
    public boolean handler(Map<String, Object> map, RedirectAttributes ra) {
        CustomerUser user = (CustomerUser) map.get(HandlerMapKey.USER_KEY.getValue());
        CustomerUser userParam = (CustomerUser) map.get(HandlerMapKey.PARAM_USER_KEY.getValue());
        if (user == null) {
            return solve(ra, userParam);
        } else if (next != null) {
            return next.handler(map, ra);
        } else {
            return true;
        }
    }

    private boolean solve(RedirectAttributes ra, CustomerUser userParam) {
        // 未查询到数据
        ra.addFlashAttribute("mes", "用户名不存在");
        ra.addFlashAttribute("userAccount", userParam.getUserAccount());
        return false;
    }
}
