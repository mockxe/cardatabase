package io.mockxe.carDatabase.brand.dao;

import java.util.ArrayList;

import io.mockxe.carDatabase.brand.model.Brand;

public interface BrandDAO {

	public Brand getEntry(int index);
	
	public ArrayList<Brand> getAllEntries();
	
	public boolean indexExists(int index);
}
