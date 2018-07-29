package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxProdEditPnumServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1�����ղ���
		String pid = request.getParameter("pid");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		//2������ҵ��ɵķ���
		//2.1������ʵ����ҵ���Ķ���
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		//2.2���ö�Ӧ�ķ�������ִ�еĽ��
		boolean result = prodService.changePnum(pid,pnum);
		//3����������
		response.getWriter().write(result+"");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
