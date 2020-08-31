package yibao.yiwei.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.common.factory.page.IPageFactory;
import yibao.yiwei.common.factory.page.IndexHomePageFactory;
import yibao.yiwei.common.factory.page.IndexPageFactory;
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
	@Qualifier("IndexPageFactory")
	IPageFactory indexPageFactory;

	@Autowired
	@Qualifier("IndexHomePageFactory")
	IPageFactory indexHomePageFactory;

	/**
	 * 跳转index页面
	 * 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return indexPageFactory.pageInit(request);

	}
	
	/**
	 * 首页
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return indexHomePageFactory.pageInit(request);
	}
	
}
