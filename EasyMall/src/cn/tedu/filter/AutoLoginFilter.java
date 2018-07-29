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
		//1���ж�cookie�Ƿ񱣴���Զ���¼����Ϣ
		//1.1ǿ������ת��
		HttpServletRequest req = (HttpServletRequest)request;
		//System.out.println((req==request)+".................");
		//1.2��ȡ���е�cookie
		Cookie[] cks = req.getCookies();
		//1.3�����Զ���¼��cookie
		Cookie autoCk = null;
		//1.4����cks����
		if(cks!=null){
			for(int i = 0;i<cks.length;i++){
				if("autologin".equals(cks[i].getName())){
					autoCk = cks[i];
					break;
				}
			}
		}
		//1.5�Ƿ񱣴��Զ���½��cookie
		if(autoCk!=null){
			//3����ȡ�û��������룬
			String up = URLDecoder.decode(autoCk.getValue(), "UTF-8");
			String username = up.split(":")[0];
			String password = up.split(":")[1];
			//4Ȼ�����ҵ���ķ����������û����������ѯ�û�����Ϣuser
			UserService uservice = BasicFactory.getFactory().getInstance(UserService.class);
			User user = uservice.loginUser(username, password);
			//5���û����������Ƿ���ȷ  user=!=null
			if(user!=null){
				//���û���Ϣ���浽session��
				req.getSession().setAttribute("user",user);
			}
		}
		//2����
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
