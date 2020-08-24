package yibao.yiwei.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;

public class InitPasswordInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());
		if(!user.getUserStatus().equals("0")) {
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
					if(!userCookie.getUserStatus().equals("0")) {
						return true;
					}else {
						//删除原先的cookie
						System.out.println("start:"+cookies[i].getMaxAge());
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");// 如果不设置默认为当前页面生效
						response.addCookie(cookies[i]);
						System.out.println("end:"+cookies[i].getMaxAge());
					}
				}
			}
		}
		
		
		response.sendRedirect(request.getContextPath()+"/customerUser/init");
		return false;
	}

}
