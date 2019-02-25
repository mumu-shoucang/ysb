package com.newNet.lottery;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 字符编码过滤器.
 * 
 */
public class CharacterEncodingFilter implements Filter {

	private String encode;
	private String uriEncode;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.setCharacterEncoding(encode);
		
		// 解决tongweb把媒体类型响应成text/plain的问题.
		response.setContentType("text/html; charset=gbk");
		chain.doFilter(request, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		this.encode = config.getInitParameter("encode");
		if (StringUtils.isBlank(encode)) {
			encode = "GBK";
		}
		this.uriEncode = config.getInitParameter("uriEncode");
		if (StringUtils.isBlank(uriEncode)) {
			uriEncode = "ISO-8859-1";
		}
	}

}
