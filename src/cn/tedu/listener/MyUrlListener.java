package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyUrlListener implements ServletContextListener {
	//ServletContext��������ʱ����
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("app");
	}
	//ServletContext���󴴽�ʱ����
	public void contextInitialized(ServletContextEvent sce) {
		//��ȡServletContext����
		ServletContext application = sce.getServletContext();
		//ʹ�øö����ȡcontextPath
		String path = application.getContextPath();
		//��·�����浽application��������
		application.setAttribute("app", path);
	}
}
