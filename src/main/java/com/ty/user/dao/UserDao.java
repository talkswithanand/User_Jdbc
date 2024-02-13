package com.ty.user.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ty.user.dto.User;
import com.ty.user.helper.ConnectionPool;

public class UserDao {

	public static User saveUser(User user) {
		Connection con = ConnectionPool.getConnectionObject();
		if (con != null) {
			String sql = "insert into usertable values (?,?,?,?)";
			try {
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1, user.getUserId());
				pstm.setString(2, user.getName());
				pstm.setString(3, user.getEmail());
				pstm.setString(4, user.getPassword());
				if (pstm.executeUpdate() == 1) {
					System.out.println("Signed Up Successfully!!!");
					System.out.println();}
				ConnectionPool.receiveConnectionObject(con);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Data inconsistency.");
			}
			return user;
		} else {
			System.out.println("Connection not Established!!");
			return null;
		}
	}

	public static User validateUser(String email, String password) {
		Connection con = ConnectionPool.getConnectionObject();
		boolean found = false;

		String sql = "Select * from usertable";

		try {
			PreparedStatement pstm =  con.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			User u1 = null;
			while(rs.next()) {
				if(rs.getString(3).equals(email) && rs.getString(4).equals(password)) {
					u1 = new User();
					u1.setUserId(rs.getString(1));
					u1.setName(rs.getString(2));
					u1.setEmail(rs.getString(3));
					u1.setPassword(rs.getString(4));
					found = true;
					break;
				}
			}
			if(!found) {
				System.out.println("User does not exist!!");
			}

			ConnectionPool.receiveConnectionObject(con);

			return u1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
