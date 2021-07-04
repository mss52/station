package com.base.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.base.base.Dialog;
import com.base.base.ResponseBase;
import com.base.base.Return;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.LocalizationDao;
import com.base.lib.db.model.ModelLocalization;

public class BaseController {

	public static final String SUCCESS_DIALOG_TITLE="Success";
	public static final String FAILURE_DIALOG_TITLE="Sorry!";
	public static final String DEFAULT_LANGUAGE="en";

	@Autowired
	private SessionBean session;
	
	@Autowired
	private LocalizationDao localizationDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	protected <T> ResponseEntity<T> generateResponse(Return data,Object ... params)
	{
		ResponseEntity response =null;
		ResponseBase b=new ResponseBase<>();
		if(data.getCode()!=null)
		{
			b.setDialog(new Dialog(FAILURE_DIALOG_TITLE,getLocalizedCode(data.getCode(),params)));
		}
		if(data.isSuccess())
		{
			b.setData(data.getData());
			response= new ResponseEntity(b,HttpStatus.OK);
			if(b.getDialog()!=null) {
				b.getDialog().setTitle(SUCCESS_DIALOG_TITLE);
			}
		}else if(data.getCode()!=null && data.getCode().contains("not.found")) {
			b.setCode(data.getCode());
			response= new ResponseEntity(b,HttpStatus.NOT_FOUND);
		}else {
			b.setCode(data.getCode());
			response= new ResponseEntity(b,HttpStatus.METHOD_FAILURE);
		}
		
		return response;
	}
	
	public String getLocalizedCode(String code,Object ... params) {
		try {
			String language=session.getDevice().getLanguage();
			ModelLocalization localizad=localizationDao.getLocalizedCode(language, code,DEFAULT_LANGUAGE);
			if(localizad!=null) {
				String string=null;
				try {
					string = String.format(localizad.getValue(),params);
				}catch (Exception e) {
					e.printStackTrace();
					string=localizad.getValue();
				}
				return string;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return code;
	}
	
}
