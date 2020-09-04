package yibao.yiwei.common.proxy.identity;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.handler.HandlerMapKey;
import yibao.yiwei.common.handler.identity.PasswordConcreteHandler;
import yibao.yiwei.common.handler.identity.UserConcreteHandler;
import yibao.yiwei.common.handler.identity.UserLockConcreteHandler;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

import java.util.Map;

/**
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class IdentitySubject implements IIdentity{
    @Override
    public boolean validateIdentity(CustomerUser userParam, CustomerUser user, Map<String,Object> mapParam, RedirectAttributes ra) {
        UserConcreteHandler userConcreteHandler = new UserConcreteHandler();
        PasswordConcreteHandler passwordConcreteHandler = new PasswordConcreteHandler();
        UserLockConcreteHandler userLockConcreteHandler = new UserLockConcreteHandler();

        userConcreteHandler.setNext(passwordConcreteHandler);
        passwordConcreteHandler.setNext(userLockConcreteHandler);
        return userConcreteHandler.handler(mapParam,ra);
    }
}
