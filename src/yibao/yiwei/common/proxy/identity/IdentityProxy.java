package yibao.yiwei.common.proxy.identity;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.handler.HandlerMapKey;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

import java.util.Map;

/**
 * 身份认证代理类
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class IdentityProxy implements IIdentity{

    private final IdentitySubject identitySubject;

    public IdentityProxy(IdentitySubject identitySubject) {
        this.identitySubject = identitySubject;
    }

    private void preValidateIdentity(CustomerUser userParam, CustomerUser user, Map<String,Object> mapParam){
        // md5加密
        Utils utils = new Utils();
        String md5Password = utils.getMD5(userParam.getUserPassword());
        userParam.setUserPassword(md5Password);

        mapParam.put(HandlerMapKey.PARAM_USER_KEY.getValue(),userParam);
        mapParam.put(HandlerMapKey.USER_KEY.getValue(),user);
    }

    @Override
    public boolean validateIdentity(CustomerUser userParam, CustomerUser user, Map<String,Object> mapParam, RedirectAttributes ra) {
        preValidateIdentity(userParam,user,mapParam);
        boolean flag = identitySubject.validateIdentity(userParam,user,mapParam,ra);
        postValidateIdentity();
        return flag;
    }

    private void postValidateIdentity(){

    }
}
