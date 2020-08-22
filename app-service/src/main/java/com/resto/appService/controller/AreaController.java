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
import com.resto.commonModel.entity.Area;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class AreaController {

	@Autowired
	private AppService service;
	
	@GetMapping("/area")
	public Response<Area> getAllArea(){
		return new Response<Area>("Area", service.getAllArea()); 
	}
	
	@PostMapping("/area")
	public Response<Area> saveArea(@RequestBody Area area){
		List<Area> areaList = new ArrayList<Area>();
		areaList.add(service.saveArea(area));
		return new Response<Area>("Area", areaList); 
	}
	
	@DeleteMapping("/area/{areaId}")
	public void deleteArea(@PathVariable Long areaId){
		service.deleteArea(areaId);
	}
	
}
