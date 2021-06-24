package com.base.service.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.base.Constants;
import com.base.base.Return;
import com.base.base.SessionException;
import com.base.bean.SessionBean;
import com.base.lib.db.model.auth.ModelSession;
import com.base.service.service.AuthenticationService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private SessionBean session;

	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> returnValue = new HashMap<>();
		Enumeration<String> hearderNames = request.getHeaderNames();
		while (hearderNames.hasMoreElements()) {
			String headerName = hearderNames.nextElement();
			returnValue.put(headerName, request.getHeader(headerName));
		}
		return returnValue;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> headers = getHeaders(request);
		if (!headers.containsKey(Constants.HEADER_AUTH_SESSION_ID)) {
			throw new SessionException("SESSION NOT FOUND");
		}
		Return<ModelSession> sessionResult = authenticationService.checkSession(
				headers.get(Constants.HEADER_AUTH_SESSION_ID), session.getDevice().getId(),
				headers.get(Constants.HEADER_AUTH_SESSION_TOKEN));
		if (!sessionResult.isSuccess()) {
			throw new SessionException(sessionResult.getCode());
		}
		session.setSession(sessionResult.getData());
		return super.preHandle(request, response, handler);
	}

}
