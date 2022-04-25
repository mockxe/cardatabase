package io.mockxe.carDatabase.engine.dao;

import java.util.ArrayList;

import io.mockxe.carDatabase.engine.model.Engine;

public interface EngineDAO {

	public Engine getEntry(int index);
	
	public ArrayList<Engine> getAllEntries();
	
	public boolean indexExists(int index);
	
}
