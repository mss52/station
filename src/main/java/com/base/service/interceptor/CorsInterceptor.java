package com.base.service.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.base.Constants;

public class CorsInterceptor extends HandlerInterceptorAdapter {
//	public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
//	public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

	private static final String ORIGIN = "origin";
	private static final String AC_REQUEST_METHOD = "access-control-request-method";
	private static final String AC_REQUEST_HEADERS = "access-control-request-headers";

	private static final String AC_ALLOW_ORIGIN = "access-control-allow-origin";
	private static final String AC_ALLOW_METHODS = "access-control-allow-methods";
	private static final String AC_ALLOW_HEADERS = "access-control-allow-headers";

	private CorsData corsData;

	private String origin="*";
	private String allowHeaders="*";

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setAllowHeaders(String allowHeaders) {
		this.allowHeaders = allowHeaders;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.corsData = new CorsData(request);

		if (this.corsData.isPreflighted()) {
			System.out.println(origin);
			response.setHeader(AC_ALLOW_ORIGIN, corsData.getOrigin());
			response.setHeader(AC_ALLOW_METHODS, corsData.requestMethods);
			response.setHeader(AC_ALLOW_HEADERS,corsData.requestHeaders);

			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (this.corsData.isSimple()) {
			response.setHeader(AC_ALLOW_ORIGIN, corsData.getOrigin());
		}
	}

	class CorsData {

		private String origin;
		private String requestMethods;
		private String requestHeaders;

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

		CorsData(HttpServletRequest request) {
			Map<String, String> headers = getHeaders(request);
			System.out.println(headers.get(ORIGIN));
			this.origin = headers.get(ORIGIN);
			this.requestMethods = headers.get(AC_REQUEST_METHOD);
			this.requestHeaders = headers.get(AC_REQUEST_HEADERS);
		}

		public boolean hasOrigin() {
			return origin != null && !origin.isEmpty();
		}

		public boolean hasRequestMethods() {
			return requestMethods != null && !requestMethods.isEmpty();
		}

		public boolean hasRequestHeaders() {
			return requestHeaders != null && !requestHeaders.isEmpty();
		}

		public String getOrigin() {
			return origin;
		}

		public String getRequestMethods() {
			return requestMethods;
		}

		public String getRequestHeaders() {
			return requestHeaders;
		}

		public boolean isPreflighted() {
			return hasOrigin() && hasRequestHeaders() && hasRequestMethods();
		}

		public boolean isSimple() {
			return hasOrigin() && !hasRequestHeaders();
		}
	}

}
