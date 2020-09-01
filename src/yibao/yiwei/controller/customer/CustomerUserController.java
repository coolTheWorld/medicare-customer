package yibao.yiwei.controller.customer;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.RandomNumber;
import yibao.yiwei.utils.Utils;
import yibao.yiwei.utils.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 定点信息登录接口
 * 
 * @author sunshy
 *
 */
@Controller
@RequestMapping("/customerUser")
public class CustomerUserController {
	@Autowired
	IBaseService baseService;
	@Autowired
	IBaseService<CustomerUser> customerService;

	// 获取日志记录器Logger，名字为本类类名
	private static Logger logger = Logger.getLogger(CustomerUserController.class);

	/**
	 * 跳转登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "/customer/login";
	}

	/**
	 * 登录动作
	 * 
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(CustomerUser userParam, String code, Boolean remember, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ra) {

		if (userParam == null || code == null) {
			ra.addFlashAttribute("mes", "必填项不能为空");
			return "redirect:/customerUser/login";
		}

		String confirmCode = request.getSession().getAttribute(SessionKey.RANDOM_CODE.getValue()).toString();
		// 验证码失败
		if (!code.equals(confirmCode)) {
			ra.addFlashAttribute("mes", "验证码失败");
			ra.addFlashAttribute("userAccount", userParam.getUserAccount());
			return "redirect:/customerUser/login";
		}

		// md5加密
		Utils utils = new Utils();
		String md5Password = utils.getMD5(userParam.getUserPassword());
		userParam.setUserPassword(md5Password);

		// 验证用户
		String sql = "select USER_ID, CUS_ID, USER_ACCOUNT, USER_PASSWORD, USER_STATUS from SYS_CUSTOMER_USER t where t.USER_ACCOUNT = ?0 ";
		CustomerUser user = customerService.findUniqueSql(sql, CustomerUser.class, userParam.getUserAccount());
		if (user == null) {
			// 未查询到数据
			ra.addFlashAttribute("mes", "用户名不存在");
			ra.addFlashAttribute("userAccount", userParam.getUserAccount());
			return "redirect:/customerUser/login";
		} else if (!userParam.getUserPassword().equals(user.getUserPassword())) {
			// 密码错误
			ra.addFlashAttribute("mes", "用户密码错误");
			ra.addFlashAttribute("userAccount", userParam.getUserAccount());
			return "redirect:/customerUser/login";
		} else if (user.getUserStatus().equals("-1")) {
			// 用户被锁定
			ra.addFlashAttribute("mes", "用户被锁定");
			ra.addFlashAttribute("userAccount", userParam.getUserAccount());
			return "redirect:/customerUser/login";
		}

		String result = "redirect:/customer/index";

//		if (user.getCusId() == null || user.getCusId().equals("-1") || user.getCusId().equals("")) {
//			 //定点信息未注册
//			ra.addFlashAttribute("mes", "定点信息未注册");
//			ra.addFlashAttribute("flag", false);
//			ra.addFlashAttribute("userAccount", userParam.getUserAccount());
//			result = "redirect:/customer/index";
//		} else if (user.getUserStatus().equals("1")) {
//			// 用户已ra启用
//			result = "redirect:/customer/index";
//		} 
//		else if (user.getUserStatus().equals("0")) {
//			// 用户初始化，跳转到修改密码界面
//			request.setAttribute("mes", "为保证您的数据安全,请在首次登录时修改初始密码");
//			result = "/customer/initPassword";
//		}ra

		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("cusId", user.getCusId());
		userMap.put("userAccount", user.getUserAccount());
		userMap.put("userStatus", user.getUserStatus().toString());
		String userJson = JSON.toJSONString(userMap);

		// 把用户信息放到session
		request.getSession().setAttribute(SessionKey.USER.getValue(), user);

		// 将用户信息保存到cookie
		if (remember != null && remember) {
			Cookie cookie = new Cookie(SessionKey.USER.getValue(), userJson);
			cookie.setMaxAge(365 * 24 * 60 * 60);
			cookie.setPath("/");// 如果不设置默认为当前页面生效
			response.addCookie(cookie);
		}
		return result;
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			RandomNumber rn = new RandomNumber();
			// 防止页面保存在缓冲区
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ValidateCode vc = rn.generateImage();
			request.getSession().setAttribute(SessionKey.RANDOM_CODE.getValue(), vc.getRand());// 随即验证码
			ServletOutputStream outStream = response.getOutputStream();
			// 输出图像到页面
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			logger.info("========验证码[" + vc.getRand() + "]图像输入完成，关闭流=======");
		} catch (IOException e) {
			logger.debug("execption message" + e.getMessage());
			// logger.debug("execption toString"+e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到初始化密码页面
	 * 
	 * @return
	 */

	@RequestMapping("/init")
	public String initPassword(HttpServletRequest request) {
		request.setAttribute("mes", "为保证您的数据安全,请在首次登录时修改初始密码");
		return "/customer/initPassword";
	}

	/**
	 * 初始化用户密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/doInit")
	public String doInitPassword(HttpServletRequest request, HttpServletResponse response,String password, String confirmPassword,RedirectAttributes ra) {

		if (StringUtils.isEmpty(confirmPassword) || StringUtils.isEmpty(password)) {
			request.setAttribute("mes", "必填项不能为空");
			return "/customer/initPassword";
		}
		// 两次密码输入是否一致
		if (!password.equals(confirmPassword)) {
			request.setAttribute("mes", "两次密码输入不一致");
			return "/customer/initPassword";
		}

		// 从session获取用户信息
		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());
		String account = user.getUserAccount();
		// md5加密
		Utils utils = new Utils();
		password = utils.getMD5(password);

		// 验证与原密码输入是否相同
		String countSql = "select count(CUS_ID) from SYS_CUSTOMER_USER t where t.USER_ACCOUNT = ?0 and t.USER_PASSWORD = ?1";
		int count = baseService.findCountSql(countSql, account, password);
		if (count > 0) {
			request.setAttribute("mes", "与原密码相同");
			return "/customer/initPassword";
		}

		// 执行sql
		String sql = "update SYS_CUSTOMER_USER set USER_PASSWORD = ?0,USER_STATUS = 1 where USER_ACCOUNT = ?1";
		int num = baseService.updateOrDeleteSql(sql, password, account);
		if (num == 1) {
			// 销毁session
			request.getSession().invalidate();
//			// 修改成功
//			return "redirect:/customer/index";
			ra.addFlashAttribute("relogin", "密码修改成功,请重新登录");
			ra.addFlashAttribute("userAccount", account);
		}
		
		return "redirect:/customerUser/login";
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	@RequestMapping("/doModify")
	@ResponseBody
	public Map doModifyPassword(HttpServletRequest request, String oldPassword, String password,
			String confirmPassword) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String sql = null;

		if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(confirmPassword) || StringUtils.isEmpty(password)) {
			resultMap.put("flag", "0");
			resultMap.put("mes", "必填项不能为空");
			return resultMap;
		}

		// 两次密码输入是否一致
		if (!password.equals(confirmPassword)) {
			resultMap.put("flag", "2");
			resultMap.put("mes", "新密码两次密码输入不一致");
			return resultMap;
		}

		// 从session获取用户信息
		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());
		String account = user.getUserAccount();
		// md5加密密码
		Utils utils = new Utils();
		password = utils.getMD5(password);
		oldPassword = utils.getMD5(oldPassword);

		// 重新验证身份
		sql = "select USER_ID, CUS_ID, USER_ACCOUNT, USER_PASSWORD, USER_STATUS from SYS_CUSTOMER_USER t where t.USER_ACCOUNT = ?0 ";
		CustomerUser dbUser = customerService.findUniqueSql(sql, CustomerUser.class, user.getUserAccount());
		if (dbUser == null || !dbUser.getUserPassword().equals(oldPassword)) {
			resultMap.put("flag", "4");
			resultMap.put("mes", "原始密码输入错误,身份验证失败");
			return resultMap;
		}

		// 验证与原密码输入是否相同
		if (password.equals(oldPassword)) {
			resultMap.put("flag", "3");
			resultMap.put("mes", "旧密码与新密码相同，请重新修改密码");
			return resultMap;
		}
//		String countSql = "select count(CUS_ID) from SYS_CUSTOMER_USER t where t.USER_ACCOUNT = ?0 and t.USER_PASSWORD = ?1";
//		int count = baseService.findCountSql(countSql, account, password);
//		if (count > 0) {
//			resultMap.put("flag", "3");
//			resultMap.put("mes", "旧密码与新密码相同，请重新修改密码");
//			return resultMap;
//		}

		// 修改密码
		sql = "update SYS_CUSTOMER_USER set USER_PASSWORD = ?0,USER_STATUS = 1 where USER_ACCOUNT = ?1";
		int num = baseService.updateOrDeleteSql(sql, password, account);
		if (num == 1) {
			// 修改成功
			resultMap.put("flag", "1");
			resultMap.put("mes", "修改成功");
			return resultMap;
		}
		resultMap.put("flag", "-1");
		resultMap.put("mes", "未知错误");
		return resultMap;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request, HttpServletResponse response) {
		// 销毁session
		request.getSession().invalidate();
		// 删除cookie
		Cookie cookie = new Cookie(SessionKey.USER.getValue(), null);
		cookie.setMaxAge(0);
		cookie.setPath("/");// 如果不设置默认为当前页面生效
		response.addCookie(cookie);
		return "redirect:/customerUser/login";
	}
}
