package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.base.Return;
import com.base.service.admin.requests.RequestLogin;
import com.base.service.service.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthenticationController extends BaseController {
	
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
		Return result = authenticationService.login(requestLogin.getUsername(),requestLogin.getPassword(),requestLogin.getVerificationId(),requestLogin.getCode());
		return generateResponse(result);
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> test() {
		return generateResponse(null);
	}
}
