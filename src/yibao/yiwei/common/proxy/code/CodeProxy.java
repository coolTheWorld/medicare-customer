package yibao.yiwei.common.proxy.code;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.SessionKey;
import yibao.yiwei.common.handler.HandlerMapKey;
import yibao.yiwei.common.handler.code.CodeConcreteHandler;
import yibao.yiwei.common.handler.code.ParamerConcreteHandler;
import yibao.yiwei.entity.system.CustomerUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码代理类
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class CodeProxy implements ICodeSubject {

    private final CodeSubject codeSubject;

    public CodeProxy(CodeSubject codeSubject) {
        this.codeSubject = codeSubject;
    }

    private void preValidateCode(HttpServletRequest request, String code, CustomerUser userParam,Map<String,Object> mapParam){
        String confirmCode = request.getSession().getAttribute(SessionKey.RANDOM_CODE.getValue()).toString();

        mapParam.put(HandlerMapKey.PARAM_CODE_KEY.getValue(),code);
        mapParam.put(HandlerMapKey.CONFIRM_CODE_KEY.getValue(),confirmCode);
        mapParam.put(HandlerMapKey.PARAM_USER_KEY.getValue(),userParam);
    }

    @Override
    public boolean validateCode(HttpServletRequest request, String code, CustomerUser userParam,Map<String,Object> mapParam, RedirectAttributes ra) {
        preValidateCode(request,code,userParam,mapParam);
        boolean flag = codeSubject.validateCode(request,code,userParam,mapParam,ra);
        postValidateCode();
        return flag;
    }

    private void postValidateCode(){

    }
}
