package com.base.service.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.base.Constants;
import com.base.base.Return;
import com.base.lib.db.model.auth.ModelStation;
import com.base.service.admin.requests.RequestStation;
import com.base.service.response.ResponseInsights;
import com.base.service.service.StationService;

@RestController
@RequestMapping("station")
public class StationController extends BaseController {

	@Autowired
	private StationService stationService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ModelStation>> get(
			@RequestParam(name = "searchPhrase", required = false) String searchPhrase,
			@RequestParam(name = "all", required = false,defaultValue = "false") Boolean all) {
		Return<List<ModelStation>> result = stationService.get(searchPhrase,(all==null||!all)?Constants.ACTIVE:null);
		return generateResponse(result);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ModelStation>> getById(@PathVariable("id") long id) {
		Return<ModelStation> result = stationService.get(id);
		return generateResponse(result);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ModelStation> put(@RequestBody RequestStation requestBody) {
		Return<ModelStation> result = stationService.put(requestBody);
		return generateResponse(result);
	}

	@RequestMapping(value = "", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ModelStation> update(@RequestBody RequestStation requestBody) {
		Return<ModelStation> result = stationService.update(requestBody);
		return generateResponse(result);
	}
	
	@RequestMapping(value = "/insights", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseInsights> insights(@RequestParam(name = "fromDate",required = false) 
	  @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,@RequestParam(name="toDate",required = false) 
	  @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) {
		System.out.println("fromDate "+fromDate );
		System.out.println("toDate "+toDate );
		Return<ResponseInsights> result = stationService.insights(session.getUser().getStation().getId(), 
				fromDate,toDate);
		return generateResponse(result);
	}
}
