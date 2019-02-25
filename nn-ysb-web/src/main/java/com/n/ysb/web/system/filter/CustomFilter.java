package com.n.ysb.web.system.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFilter extends AccessControlFilter {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String deniedUrl;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		if(request instanceof HttpServletRequest) {
			HttpServletRequest hrequest = (HttpServletRequest)request;
			String refUrl = StringUtils.defaultString(hrequest.getHeader("Referer"), "");
			String reqUrl = hrequest.getServletPath();
			String pcode = "";
			if(refUrl.contains("pcode=")) {
				String pars = refUrl.substring(refUrl.indexOf("?") + 1);
				String[] parItems = pars.split("&");
				for(String parItem : parItems) {
					if(parItem.contains("pcode=")) {
						pcode = parItem.substring(parItem.indexOf("=") + 1);
					}
				}
			} else {
				pcode = StringUtils.defaultString(request.getParameter("pcode"), "null");
			}
			
			Subject subject = getSubject(request, response);
			boolean bl = subject.isPermitted(pcode);
			log.info("{} --- {}", reqUrl, bl);
			return bl;
		}
		return true;
		
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		redirectTo401(request, response);
		return false;
	}
	
	private void redirectTo401(ServletRequest request, ServletResponse response) throws Exception {
		String url = getDeniedUrl();
        WebUtils.issueRedirect(request, response, url);
	}

	public String getDeniedUrl() {
		return deniedUrl;
	}

	public void setDeniedUrl(String deniedUrl) {
		this.deniedUrl = deniedUrl;
	}
	
}
