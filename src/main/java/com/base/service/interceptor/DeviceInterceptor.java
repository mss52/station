package com.base.service.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.base.Constants;
import com.base.base.Return;
import com.base.base.SessionException;
import com.base.bean.SessionBean;
import com.base.lib.db.model.auth.ModelDevice;
import com.base.service.service.AuthenticationService;

public class DeviceInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private SessionBean session;
	
	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> returnValue = new HashMap<>();
		Enumeration<String> hearderNames = request.getHeaderNames();
		while (hearderNames.hasMoreElements()) {
			String headerName = hearderNames.nextElement();
			System.out.println(headerName + " value : "+request.getHeader(headerName));
			returnValue.put(headerName, request.getHeader(headerName));
		}
		return returnValue;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> headers = getHeaders(request);
		if(!headers.containsKey(Constants.HEADER_DEVICE_UID)) {
			throw new SessionException("DEVICE HEADER MISSING");
		}
		Return<ModelDevice> deviceResult = authenticationService.createDevice(headers.get(Constants.HEADER_DEVICE_ID),headers.get(Constants.HEADER_DEVICE_UID),headers.get(Constants.HEADER_DEVICE_MODEL),
				headers.get(Constants.HEADER_DEVICE_BRAND),headers.get(Constants.HEADER_DEVICE_OS_VERSION),headers.get(Constants.HEADER_DEVICE_OPERATING_SYSTEM),
				headers.get(Constants.HEADER_DEVICE_OPERATING_NAME),headers.get(Constants.HEADER_DEVICE_APP_VERSION),
				headers.get(Constants.HEADER_LANGUAGE));
		if(!deviceResult.isSuccess()) {
			throw new SessionException(deviceResult.getCode());
		}
		session.setDevice(deviceResult.getData());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.addHeader("deviceId", String.valueOf(session.getDevice().getId()));
		super.postHandle(request, response, handler, modelAndView);
	}

}
