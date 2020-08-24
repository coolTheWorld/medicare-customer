package yibao.yiwei.controller.customer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 预警
 * @author sunshy
 *
 */
@Controller
@RequestMapping("/warning")
public class WarningController {

	//获取日志记录器Logger，名字为本类类名
    private static Logger logger = Logger.getLogger(WarningController.class);
	
	/**
	 * 预警首页
	 * @return
	 */
	@RequestMapping("/home")
	public String home() {
		return "customer/warning/home";
	}
	
	/**
	 * 上传异常
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload() {
		return "customer/warning/upload";
	}
	
	/**
	 * 库存异常
	 * @return
	 */
	@RequestMapping("/stock")
	public String stock() {
		return "customer/warning/stock";
	}
	
	/**
	 * 销售异常
	 * @return
	 */
	@RequestMapping("/sales")
	public String sales() {
		return "customer/warning/sales";
	}
}
