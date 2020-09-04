package yibao.yiwei.common.proxy.identity;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

import java.util.Map;

/**
 * @author sunshy
 */
public interface IIdentity {
    boolean validateIdentity(CustomerUser userParam,CustomerUser user, Map<String,Object> mapParam, RedirectAttributes ra);
}
