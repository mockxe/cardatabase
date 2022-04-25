package io.mockxe.carDatabase.car.dao;

import java.util.ArrayList;

import io.mockxe.carDatabase.car.model.Car;

public interface CarDAO {
	
	public void addEntry(Car car);

	public Car getEntry(int index);
	
	public ArrayList<Car> getAllEntries();
	
	public void updateEntry(Car car, int index);
	
	public void deleteEntry(int index);
	
	public boolean indexExists(int index);
	
	public int getRows();

}
