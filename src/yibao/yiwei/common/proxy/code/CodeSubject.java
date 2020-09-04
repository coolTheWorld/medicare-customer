package yibao.yiwei.common.proxy.code;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.handler.code.CodeConcreteHandler;
import yibao.yiwei.common.handler.code.ParamerConcreteHandler;
import yibao.yiwei.entity.system.CustomerUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description:
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class CodeSubject implements ICodeSubject {

    @Override
    public boolean validateCode(HttpServletRequest request, String code, CustomerUser userParam, Map<String,Object> mapParam, RedirectAttributes ra) {
        ParamerConcreteHandler paramerConcreteHandler = new ParamerConcreteHandler();
        CodeConcreteHandler codeConcreteHandler = new CodeConcreteHandler();
        paramerConcreteHandler.setNext(codeConcreteHandler);
        return  paramerConcreteHandler.handler(mapParam,ra);
    }
}
