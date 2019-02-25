package com.n.ysb.web.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *  接口请求白名单
 */
public class WhiteListFilter extends OncePerRequestFilter {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String whiteDomain;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		List<String> whiteList = Arrays.asList(whiteDomain.split(","));
		
		String reqIp = request.getHeader("x-forwarded-for");
		if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {
			reqIp = request.getHeader("X-Forwarded-For");
		}
        if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {  
            reqIp = request.getHeader("Proxy-Client-IP");  
        }  
        if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {  
            reqIp = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {  
            reqIp = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {  
            reqIp = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (reqIp == null || reqIp.length() == 0 || "unknown".equalsIgnoreCase(reqIp)) {  
            reqIp = request.getRemoteAddr();  
        }
        if (reqIp != null && reqIp.indexOf(",") != -1) {
			String[] ips = reqIp.split(",");
			if (ips.length > 0 && !ips[0].equalsIgnoreCase("unknown")) {
				reqIp = ips[0];
			}
		}
        
        String localAddr = request.getLocalAddr();
        log.info("{} 's reqIp={}, localAddr={} ", this.getClass().getName(), reqIp, localAddr);
        
        if(reqIp.equals(localAddr) || whiteDomain.equals("*")) { // 本机 和 * 放行
        	filterChain.doFilter(request, response);
        } else {
        	boolean pass = false;
        	for(String white : whiteList) {
        		if(reqIp.startsWith(white)) {
        			pass = true;
        			break;
        		}
        	}
        	if(pass) {
        		filterChain.doFilter(request, response);
        	}
        	if(!pass) {
        		response.setStatus(HttpStatus.FORBIDDEN.value());
        		response.setHeader("desc", HttpStatus.FORBIDDEN.value()+"-"+HttpStatus.FORBIDDEN.getReasonPhrase());
        		response.getWriter().print(SimpleResponse.failOnlyDesc(HttpStatus.FORBIDDEN.name()));
        	}
        }
        

	}

	public String getWhiteDomain() {
		return whiteDomain;
	}

	public void setWhiteDomain(String whiteDomain) {
		this.whiteDomain = whiteDomain;
	}

}
