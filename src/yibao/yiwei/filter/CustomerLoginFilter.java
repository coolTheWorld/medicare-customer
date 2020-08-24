package yibao.yiwei.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;

public class CustomerLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * 判断用户是否登陆， 若能从cookie或session中得到登录信息 则视为已登录状态
		 */

		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse  = (HttpServletResponse)response;
		// 从session中获得用户信息
		CustomerUser user = (CustomerUser)hRequest.getSession().getAttribute(SessionKey.USER.getValue());
		if (user != null) {
			chain.doFilter(request, response);
			return;
		}

		// 从cookies中查询用户登录信息
		Cookie[] cookies = hRequest.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(SessionKey.USER.getValue())) {
					//获取用户信息cookie并转化为CustomerUser
					String  userJson = cookies[i].getValue().toString();
					JSONObject jsonObject = JSONObject.parseObject(userJson);
					CustomerUser userCookie = jsonObject.toJavaObject(jsonObject, CustomerUser.class);
					//用户信息加入session
					hRequest.getSession().setAttribute(SessionKey.USER.getValue(),userCookie );
					chain.doFilter(request, response);
					return;
				}
			}
		}

		String path = hRequest.getRequestURI();
		// 重定向到登录页面
		if(!path.equals("/customerUser/login")) {
			hResponse.sendRedirect(hRequest.getContextPath()+"/customerUser/login");
		}
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
