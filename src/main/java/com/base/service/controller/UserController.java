package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.base.Return;
import com.base.lib.db.model.auth.ModelUser;
import com.base.service.admin.requests.RequestLogin;
import com.base.service.admin.requests.RequestSignUp;
import com.base.service.admin.requests.RequestUpdateUser;
import com.base.service.response.ResponseLogin;
import com.base.service.service.AuthenticationService;
import com.base.service.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin requestLogin) {
		Return<ResponseLogin> result = authenticationService.login(requestLogin.getUsername(),requestLogin.getPassword(),requestLogin.getVerificationId(),requestLogin.getCode());
		return generateResponse(result);
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ModelUser> signup(@RequestBody RequestSignUp requestLogin) {
		Return<ModelUser> result = userService.signUp(requestLogin);
		return generateResponse(result);
	}
	
	@RequestMapping(value="", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ModelUser> update(@RequestBody RequestUpdateUser requestLogin) {
		Return<ModelUser> result = userService.update(requestLogin);
		return generateResponse(result);
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> test() {
		return generateResponse(null);
	}
}
