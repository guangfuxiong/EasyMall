package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyUrlListener implements ServletContextListener {
	//ServletContext对象销毁时调用
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("app");
	}
	//ServletContext对象创建时调用
	public void contextInitialized(ServletContextEvent sce) {
		//获取ServletContext对象
		ServletContext application = sce.getServletContext();
		//使用该对象获取contextPath
		String path = application.getContextPath();
		//将路径保存到application作用域中
		application.setAttribute("app", path);
	}
}
