package yibao.yiwei.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;

/**
 * 定点用户身份验证
 * 
 * @author cool
 *
 */
public class CustomerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		// 从session中获得用户信息
		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());
		if (user != null) {
			return true;
		}
		
		// 从cookies中查询用户登录信息
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(SessionKey.USER.getValue())) {
					// 获取用户信息cookie并转化为CustomerUser
					String userJson = cookies[i].getValue().toString();
					JSONObject jsonObject = JSONObject.parseObject(userJson);
					CustomerUser userCookie = jsonObject.toJavaObject(jsonObject, CustomerUser.class);
					// 用户信息加入session
					request.getSession().setAttribute(SessionKey.USER.getValue(), userCookie);
					
					return true;
				}
			}
		}
		
		/*
		 * String path = request.getRequestURI(); // 重定向到登录页面
		 * if(!path.equals("/customerUser/login")) {
		 * response.sendRedirect(request.getContextPath()+"/customerUser/login"); }
		 */
		
		response.sendRedirect(request.getContextPath()+"/customerUser/login");
		return false;
	}

}
