package com.base.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
