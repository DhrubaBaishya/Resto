package com.resto.appService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Tax;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class TaxController {

	@Autowired
	private AppService service;
	
	@PostMapping("/tax")
	public Response<Tax> saveTax(@RequestBody Tax tax){
		List<Tax> taxes = new ArrayList<Tax>();
		taxes.add(service.saveTax(tax));
		return new Response<Tax>("Tax", taxes);
	}
	
	@GetMapping("/tax")
	public Response<Tax> getAllTaxes(){
		return new Response<Tax>("Tax", service.getAllTaxes());
	}
	
	@DeleteMapping("/tax/{taxId}")
	public void deleteTax(@PathVariable Long taxId) {
		service.deleteTax(taxId);
	}

}
