package io.mockxe.carDatabase.brand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import io.mockxe.carDatabase.brand.dao.BrandDAO;
import io.mockxe.carDatabase.brand.model.Brand;

public class JdbcBrandDAO implements BrandDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Brand getEntry(int index) {
		// construct SQL statement
		String sql = "SELECT * FROM `brands` WHERE `brands`.`Id_brand` = ?";
		
		Connection conn = null;
		
		try {
			// get connection
			conn = dataSource.getConnection();
			
			// insert value into statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			
			// create empty brand object
			Brand brand = null;
			
			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				//initialize brand and write value of resultSet into it
				brand = new Brand(
						rs.getInt("Id_brand"),
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
	public ArrayList<Brand> getAllEntries() {
		// construct SQL statement
		String sql = "SELECT * FROM `brands`";

		Connection conn = null;

		try {
			// get connection
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ArrayList<Brand> brands = new ArrayList<Brand>();

			// execute query and write output into ResultSet
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				// add car to arraylist and write values of resultSet into it
				brands.add(new Brand(rs.getInt("Id_brand"), rs.getString("name")));
			}
			rs.close();
			ps.close();

			return brands;

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
		String sql = "SELECT `Id_brand` FROM `brands` WHERE `brands`.`Id_brand` = ?";

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
