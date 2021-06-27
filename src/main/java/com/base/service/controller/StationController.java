package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.base.Return;
import com.base.service.admin.requests.RequestStation;
import com.base.service.service.StationService;

@RestController
@RequestMapping("station")
public class StationController extends BaseController {
	
	
	@Autowired
	private StationService stationService;
	
	
	@RequestMapping(value="/", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> put(@RequestBody RequestStation requestBody) {
		Return<?> result = stationService.put(requestBody);
		return generateResponse(result);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<?> update(@RequestBody RequestStation requestBody) {
		Return<?> result = stationService.update(requestBody);
		return generateResponse(result);
	}
}
