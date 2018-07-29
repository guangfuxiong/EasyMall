package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.User;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter {
	public void destroy() {
	}
	//Integer ->Object->Integer
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1、判断cookie是否保存的自动登录的信息
		//1.1强制类型转换
		HttpServletRequest req = (HttpServletRequest)request;
		//System.out.println((req==request)+".................");
		//1.2获取所有的cookie
		Cookie[] cks = req.getCookies();
		//1.3定义自动登录的cookie
		Cookie autoCk = null;
		//1.4遍历cks数组
		if(cks!=null){
			for(int i = 0;i<cks.length;i++){
				if("autologin".equals(cks[i].getName())){
					autoCk = cks[i];
					break;
				}
			}
		}
		//1.5是否保存自动登陆的cookie
		if(autoCk!=null){
			//3、获取用户名和密码，
			String up = URLDecoder.decode(autoCk.getValue(), "UTF-8");
			String username = up.split(":")[0];
			String password = up.split(":")[1];
			//4然后调用业务层的方法，根据用户名和密码查询用户的信息user
			UserService uservice = BasicFactory.getFactory().getInstance(UserService.class);
			User user = uservice.loginUser(username, password);
			//5、用户名和密码是否正确  user=!=null
			if(user!=null){
				//将用户信息保存到session中
				req.getSession().setAttribute("user",user);
			}
		}
		//2放行
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
