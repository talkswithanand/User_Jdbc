package com.ty.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ty.user.dto.LoginInfo;
import com.ty.user.helper.ConnectionPool;

public class LoginInfoDao {

	public static LoginInfo saveLoginInfo(LoginInfo loginInfo) {

		Connection con = ConnectionPool.getConnectionObject();

		String sql = "insert into logininfo values (?, ?, ?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, loginInfo.getId());
			pstm.setString(2, loginInfo.getLoginUserId());
			pstm.setString(3, loginInfo.getTime());

			pstm.execute();

			ConnectionPool.receiveConnectionObject(con);

			return loginInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<LoginInfo> findAll(String userid) {

		List<LoginInfo> logins = new ArrayList<LoginInfo>();

		Connection con = ConnectionPool.getConnectionObject();

		String sql = "Select * from logininfo";
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				if (rs.getString(2).equals(userid)) {
					LoginInfo l = new LoginInfo();
					l.setId(rs.getInt(1));
					l.setLoginUserId(rs.getString(2));
					l.setTime(rs.getString(3));
					logins.add(l);
				}
			}
			
			Iterator<LoginInfo> i = logins.iterator();
			System.out.println("---------+-----------+--------------------------");
			System.out.println("|   Id   |   UserId  |        LoginTime        |");
			System.out.println("---------+-----------+--------------------------");
			
			int c = 0;
			while(i.hasNext()) {
				LoginInfo l = i.next();
				l.setId(++c);
				System.out.println(l);
			}
			System.out.println("---------+-----------+--------------------------");

			ConnectionPool.receiveConnectionObject(con);

			return logins;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
