package yibao.yiwei.common.facade.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;

@Component
/**
 * 将用户信息写到request
 * @author sunshy
 *
 */
public class PageFacade {

    @Autowired
    IBaseService<Customer> customerService;

    public void pageInit(HttpServletRequest request){
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());
        String sql = "select CUS_ID, CUS_PARENTID, CUS_STATUS, CUS_NAME,CUS_REGIP,CUS_PHONE,CUS_CONTACT,CUS_ADDR,CUS_FLAG,CUS_UNIQURE,CUS_REGDATE,CUS_DAREWAY,CUS_BRANCHCODE,CUS_REMARK,CUS_PCODE from TBL_CUSTOMER where CUS_ID = ?0";
        Customer customer = customerService.findUniqueSql(sql,Customer.class,user.getCusId());
        request.setAttribute("customer", customer);
    }
}
