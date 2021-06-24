package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.base.Return;
import com.base.lib.db.model.ModelCar;
import com.base.service.admin.requests.RequestCar;
import com.base.service.admin.requests.RequestFillCar;
import com.base.service.admin.requests.RequestLogin;
import com.base.service.service.AuthenticationService;
import com.base.service.service.CarService;

@RestController
@RequestMapping("car")
public class CarController extends BaseController {
	
	
	@Autowired
	private CarService carService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ModelCar> getCar(@RequestParam(name="plateNumber") String plateNumber,
			@RequestParam(name="plateCode") String plateCode) {
		Return<ModelCar> result = carService.getCar(plateNumber,plateCode);
		return generateResponse(result);
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ModelCar> addCar(@RequestBody RequestCar car) {
		Return<ModelCar> carModel = carService.addCar(car);
		return generateResponse(carModel);
	}
	
	@RequestMapping(value="", method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<ModelCar> updateCar(@RequestBody RequestCar car) {
		Return<ModelCar> carModel = carService.update(car);
		return generateResponse(carModel);
	}
	
	@RequestMapping(value="/fill", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ModelCar> fill(@RequestBody RequestFillCar car) {
		Return<ModelCar> carModel = carService.fillCar(car);
		return generateResponse(carModel);
	}
}
