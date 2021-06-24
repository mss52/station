package com.base.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.base.base.Dialog;
import com.base.base.ResponseBase;
import com.base.base.Return;

public class BaseController {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> ResponseEntity<T> generateResponse(Return data)
	{
		ResponseEntity response =null;
		ResponseBase b=new ResponseBase<>();
		if(data.getCode()!=null)
		{
			b.setDialog(new Dialog(data.getCode(),data.getCode()));
		}
		if(data.isSuccess())
		{
			b.setData(data.getData());
			response= new ResponseEntity(b,HttpStatus.OK);
		}else if(data.getCode().contains("not.found")) {
			b.setCode(data.getCode());
			response= new ResponseEntity(b,HttpStatus.NOT_FOUND);
		}else {
			b.setCode(data.getCode());
			response= new ResponseEntity(b,HttpStatus.METHOD_FAILURE);
		}
		
		return response;
	}
}
