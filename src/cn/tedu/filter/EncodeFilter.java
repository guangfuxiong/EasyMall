package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {
	private String encode;
	public void init(FilterConfig config) throws ServletException {
		encode = config.getInitParameter("encode");
	}
	class MyHSR extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		private boolean hasEncode = false;
		public MyHSR(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public Map<String,String[]> getParameterMap() {
			//获取提交的方式post/get
			if("POST".equals(request.getMethod())){
				try {
					request.setCharacterEncoding(encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return request.getParameterMap();
			}else if("GET".equals(request.getMethod())){
				//定义一个Map集合对象
				Map<String,String[]> map = request.getParameterMap();
				if(!hasEncode){
					//遍历map
					for(Map.Entry<String, String[]> entry:map.entrySet()){
						//获取原来的vals
						String[] vals = entry.getValue();
						//遍历数组处理乱码
						for (int i = 0;i<vals.length;i++) {
							try {
								vals[i] = new String(vals[i].getBytes("ISO8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
					hasEncode = true;
				}
				return map;
			}else{//既不是get方式也不是post方式
				return super.getParameterMap();
			}
		}
		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
		@Override
		public String getParameter(String name) {
			return getParameterValues(name)==null?"":getParameterValues(name)[0];
		}
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset="+encode);
		chain.doFilter(new MyHSR((HttpServletRequest)request), response);

	}
	public void destroy() {
	}
}
