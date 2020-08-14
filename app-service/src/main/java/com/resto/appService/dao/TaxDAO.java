package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Tax;

public interface TaxDAO {
	public Tax saveTax(Tax tax);
	public List<Tax> getAllTaxes();
	public void deleteTax(Long taxId);
}
