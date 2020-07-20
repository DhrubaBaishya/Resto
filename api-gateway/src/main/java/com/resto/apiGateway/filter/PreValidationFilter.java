package com.resto.apiGateway.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.resto.apiGateway.util.JWTUtils;
import com.resto.commonModel.exception.AuthorizationNotFoundException;
import com.resto.commonModel.exception.ExceptionResponse;
import com.resto.commonModel.exception.IncorrectAuthorizationException;

@Component
public class PreValidationFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JWTUtils jwtUtil;
	
	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String uri = request.getRequestURI();
		if(uri.startsWith("/auth-service"))
			return false;
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		try {
			jwtUtil.validateJWTToken(request);
		}
		catch(AuthorizationNotFoundException e) {
			denyAccess(e);
		}
		catch(IncorrectAuthorizationException e) {
			denyAccess(e);
		}
		return null;
	}
	
	private void denyAccess(Exception e) {
		ObjectMapper om = new ObjectMapper();
		ExceptionResponse exception = new ExceptionResponse(new Date(), "Access Denied", e.getMessage());
		RequestContext.getCurrentContext().setSendZuulResponse(false);
		RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
		try {
			RequestContext.getCurrentContext().setResponseBody(om.writeValueAsString(exception));
		} catch (JsonProcessingException e1) {
			RequestContext.getCurrentContext().setResponseBody("Access Denied");
		}
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
