package yibao.yiwei.common.handler.code;

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
public class CodeConcreteHandler extends CodeHandler {

    /**
     * 验证码是否输入正确
     *
     * @param map
     * @param ra
     * @return
     */
    @Override
    public boolean handler(Map<String, Object> map, RedirectAttributes ra) {
        String code = (String) map.get(HandlerMapKey.PARAM_CODE_KEY.getValue());
        String confirmCode = (String) map.get(HandlerMapKey.CONFIRM_CODE_KEY.getValue());
        CustomerUser userParam = (CustomerUser) map.get(HandlerMapKey.PARAM_USER_KEY.getValue());
        if (!code.equals(confirmCode)) {
            return solve(ra,userParam);
        }else if(next != null){
            return next.handler(map,ra);
        }else {
            return true;
        }
    }

    private boolean solve(RedirectAttributes ra,CustomerUser userParam){
        ra.addFlashAttribute("mes", "验证码失败");
        ra.addFlashAttribute("userAccount", userParam.getUserAccount());
        return false;
    }
}
