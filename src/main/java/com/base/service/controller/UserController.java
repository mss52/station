package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin requestLogin,
			@ApiParam(value = "auth_session_id", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "auth_session_id") String session,
			@ApiParam(value = "auth_session_token	", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "auth_session_token	") String auth_session_token,
			@ApiParam(value = "device_id", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "device_id") String device_id) {
		Return<ResponseLogin> result = authenticationService.login(requestLogin.getUsername(),
				requestLogin.getPassword(), requestLogin.getVerificationId(), requestLogin.getCode());
		return generateResponse(result);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ModelUser> signup(@RequestBody RequestSignUp requestLogin,
			@ApiParam(value = "auth_session_id", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "auth_session_id") String session,
			@ApiParam(value = "auth_session_token", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "auth_session_token") String auth_session_token,
			@ApiParam(value = "device_id", required = false, hidden = false, defaultValue = "this is global header not required here") @RequestHeader(required = false, name = "device_id") String device_id) {
		Return<ModelUser> result = userService.signUp(requestLogin);
		return generateResponse(result);
	}

	@RequestMapping(value = "", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ModelUser> update(@RequestBody RequestUpdateUser requestLogin) {
		Return<ModelUser> result = userService.update(requestLogin);
		return generateResponse(result);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> test() {
		return generateResponse(null);
	}
}
