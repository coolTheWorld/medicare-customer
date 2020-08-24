package yibao.yiwei.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

/**
 * 
 * @author sunshy
 *
 */
@RequestMapping("/customer")
@Controller
public class IndexController {

	//获取日志记录器Logger，名字为本类类名
    private static Logger logger = Logger.getLogger(IndexController.class);
	
    
    @Autowired
    IBaseService<Customer> customerService;
	/**
	 * 跳转index页面
	 * 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());
		String sql = "select CUS_ID, CUS_PARENTID, CUS_STATUS, CUS_NAME,CUS_REGIP,CUS_PHONE,CUS_CONTACT,CUS_ADDR,CUS_FLAG,CUS_UNIQURE,CUS_REGDATE,CUS_DAREWAY,CUS_BRANCHCODE,CUS_REMARK,CUS_PCODE from TBL_CUSTOMER where CUS_ID = ?0";
		Customer customer = customerService.findUniqueSql(sql,Customer.class,user.getCusId());
		request.setAttribute("customer", customer);
		return "/customer/index";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());
		String sql = "select CUS_ID, CUS_PARENTID, CUS_STATUS, CUS_NAME,CUS_REGIP,CUS_PHONE,CUS_CONTACT,CUS_ADDR,CUS_FLAG,CUS_UNIQURE,CUS_REGDATE,CUS_DAREWAY,CUS_BRANCHCODE,CUS_REMARK,CUS_PCODE from TBL_CUSTOMER where CUS_ID = ?0";
		Customer customer = customerService.findUniqueSql(sql,Customer.class,user.getCusId());
		request.setAttribute("customer", customer);
		return "/customer/home";
	}
	
}
