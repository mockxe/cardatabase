package io.mockxe.carDatabase.engine.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import io.mockxe.carDatabase.engine.dao.EngineDAO;
import io.mockxe.carDatabase.engine.model.Engine;

public class JdbcEngineDAO implements EngineDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Engine getEntry(int index) {
		// construct SQL statement
		String sql = "SELECT * FROM `engines` WHERE `engines`.`Id_engine` = ?";
		
		Connection conn = null;
		
		try {
			// get connection
			conn = dataSource.getConnection();
			
			// insert value into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			
			// create empty engine object
			Engine brand = null;
			
			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				//initialize engine and write value of resultSet into it
				brand = new Engine(
						rs.getInt("Id_engine"),
						rs.getString("name")
						);
			}
			rs.close();
			ps.close();
			return brand;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public ArrayList<Engine> getAllEntries() {
		// construct SQL statement
		String sql = "SELECT * FROM `engines`";

		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ArrayList<Engine> engines= new ArrayList<Engine>();

			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				// add car to arraylist and write values of resultSet into it
				engines.add(new Engine(rs.getInt("Id_engine"), rs.getString("name")));
			}
			rs.close();
			ps.close();

			return engines;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public boolean indexExists(int index) {
		// construct SQL statement
		String sql = "SELECT `Id_engine` FROM `engines` WHERE `engines`.`Id_engine` = ?";

		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			// insert values into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();

			// check if result is empty
			if (!rs.isBeforeFirst()) {
				rs.close();
				ps.close();
				return false;
			} else {
				rs.close();
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
