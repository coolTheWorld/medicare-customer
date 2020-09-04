package yibao.yiwei.common.proxy.code;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.entity.system.CustomerUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author sunshy
 */
public interface ICodeSubject {
    boolean validateCode(HttpServletRequest request, String code, CustomerUser userParam, Map<String,Object> mapParam, RedirectAttributes ra);
}
