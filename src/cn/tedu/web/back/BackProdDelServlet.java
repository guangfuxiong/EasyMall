package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.exception.MsgException;
import cn.tedu.service.ProdService;

public class BackProdDelServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1��������Ʒid
		String pid = request.getParameter("pid");
		//2������ҵ������
		ProdService prodService = BasicFactory.getFactory().
				getInstance(ProdService.class);
		try{
			//3������ɾ���ķ���
			prodService.deleteProdById(pid);
			//4��ɾ���ɹ�
			response.getWriter().write("ɾ���ɹ���3����Զ��Զ���ת�����û����" +
					"ת����<a href='"+request.getContextPath()+"/servlet/BackProdListServlet'>����˴�</a>");
		}catch (MsgException e) {
			//4��ɾ��ʧ��
			response.getWriter().write(e.getMessage()+"3����Զ��Զ���ת�����û����" +
					"ת����<a href='"+request.getContextPath()+"/servlet/BackProdListServlet'>����˴�</a>");
		}
		//5�������Զ���ת
		response.setHeader("Refresh", "3;url="+
		    request.getContextPath()+"/servlet/BackProdListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
