package io.mockxe.carDatabase.car.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import io.mockxe.carDatabase.car.dao.CarDAO;
import io.mockxe.carDatabase.car.model.Car;

public class JdbcCarDAO implements CarDAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addEntry(Car car) {
		// construct SQL statement
		String sql = "INSERT INTO `cars` (`Id_car`, `Id_brand`, `Id_engine`, `displacement`, `date`, `doors`, `color`, `weight`, `chassis`, `pollutant`, `description`) "
				+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							// index/Id_car is always NULL - auto increment will handle it
		
		Connection conn = null;
		
		try {
			// get connection
			conn = dataSource.getConnection();
			
			// insert values into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, car.getBrand().getIndex());
			ps.setInt(2, car.getEngine().getIndex());
			ps.setInt(3, car.getDisplacement());
			ps.setDate(4, car.getDate());
			ps.setInt(5, car.getDoors());
			ps.setString(6, car.getColor());
			ps.setInt(7, car.getWeight());
			ps.setString(8, car.getChassis());
			ps.setInt(9, car.getPollutant());
			ps.setString(10, car.getDescription());
			
			// execute statement
			ps.executeUpdate();
			ps.close();
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
	public Car getEntry(int index) {
		// construct SQL statement
		String sql = "SELECT * FROM `cars` WHERE `cars`.`Id_car` = ?";
		
		Connection conn = null;
		
		try {
			// get connection
			conn = dataSource.getConnection();
			
			// insert values into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			
			// create empty car object
			Car car = null;
			
			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				//initialize car and write values of resultSet into it
				car = new Car(
						rs.getInt("Id_car"),
						rs.getInt("Id_brand"),
						rs.getInt("Id_engine"),
						rs.getInt("displacement"),
						rs.getDate("date"),
						rs.getInt("doors"),
						rs.getString("color"),
						rs.getInt("weight"),
						rs.getString("chassis"),
						rs.getInt("pollutant"),
						rs.getString("description")
						);
			}
			rs.close();
			ps.close();
			return car;
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
	public ArrayList<Car> getAllEntries() {
		// construct SQL statement
		String sql = "SELECT * FROM `cars`";
		
		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ArrayList<Car> cars = new ArrayList<Car>();
			
			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				// add car to arraylist and write values of resultSet into it
				cars.add(new Car(
						rs.getInt("Id_car"), 
						rs.getInt("Id_brand"), 
						rs.getInt("Id_engine"),
						rs.getInt("displacement"), 
						rs.getDate("date"), 
						rs.getInt("doors"), 
						rs.getString("color"),
						rs.getInt("weight"), 
						rs.getString("chassis"), 
						rs.getInt("pollutant"),
						rs.getString("description")
						));
			}
			rs.close();
			ps.close();
			
			return cars;
			
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
	public void updateEntry(Car car, int index) {
		// construct SQL statement
		String sql = "UPDATE `cars` SET `Id_brand` = ?, `Id_engine` = ?, `displacement` = ?, `date` = ?, `doors` = ?, `color` = ?, `weight` = ?, `chassis` = ?, `pollutant`= ?, `description` = ? WHERE `cars`.`Id_car` = ? ";
		// Note that index can't be updated
		
		Connection conn = null;
		
		try {
			// get connection
			conn = dataSource.getConnection();
			
			// insert values into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, car.getBrand().getIndex());
			ps.setInt(2, car.getEngine().getIndex());
			ps.setInt(3, car.getDisplacement());
			ps.setDate(4, car.getDate());
			ps.setInt(5, car.getDoors());
			ps.setString(6, car.getColor());
			ps.setInt(7, car.getWeight());
			ps.setString(8, car.getChassis());
			ps.setInt(9, car.getPollutant());
			ps.setString(10, car.getDescription());
			ps.setInt(11, index);
			
			// execute statement
			ps.executeUpdate();
			ps.close();
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
	public void deleteEntry(int index) {
		// construct SQL statement
		String sql = "DELETE FROM `cars` WHERE `cars`.`Id_car` = ?";
		
		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			// insert index into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			// execute query
			ps.executeUpdate();
			ps.close();
			
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
		String sql = "SELECT `Id_car` FROM `cars` WHERE `cars`.`Id_car` = ?";

		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			// insert values into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);

			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			
			//check if result is empty
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

	@Override
	public int getRows() {
		// construct SQL statement
		String sql = "SELECT COUNT(*) AS 'rows' FROM `cars`";

		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();

			rs.next();
			int rows = rs.getInt("rows");
			
			rs.close();
			ps.close();

			return rows;
			
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
