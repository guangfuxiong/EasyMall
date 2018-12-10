package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.utils.VerifyCode;
/**
 * 生成验证码图片
 */
public class ValiImageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//控制浏览器不要缓存验证码图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		
		VerifyCode vc = new VerifyCode();
		
		vc.drawImage(response.getOutputStream());
		
		//获取图片验证码
		String valistr = vc.getCode();
		
		//保存验证码到session中
		request.getSession().setAttribute("valistr", valistr);
		
		
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
