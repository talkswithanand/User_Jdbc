package com.ty.user.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ty.user.dao.LoginInfoDao;
import com.ty.user.dao.UserDao;
import com.ty.user.dto.LoginInfo;
import com.ty.user.dto.User;

public class UserController {

	public static void main(String[] args) {

		boolean flag = true;
		User u = null;
		LoginInfo l = null;
		Scanner sc = null;
		int i = 0;

		System.out.println("Welcome to portal!!!");

		while (flag) {
			try {
				System.out.println("Press 1 to SignUp\nPress 2 to LoginIn");
				sc = new Scanner(System.in);
				int choice = sc.nextInt();
				switch (choice) {
				case 1: {
					u = new User();
					System.out.println("Create User Id: ");
					u.setUserId(sc.next());
					System.out.println("Enter Name: ");
					u.setName(sc.next());
					System.out.println("Enter email");
					u.setEmail(sc.next());
					System.out.println("Create password");
					u.setPassword(sc.next());
					u = UserDao.saveUser(u);
				}
				break;

				case 2: {
					System.out.println("Enter Email");
					String email = sc.next();
					System.out.println("Enter Password");
					String password = sc.next();
					u = UserDao.validateUser(email, password);
					if (u != null) {
						l = new LoginInfo();
						l.setId(++i);
						// l.setTime();
						l.setLoginUserId(u.getUserId());
						LoginInfoDao.saveLoginInfo(l);
						System.out.println(
								"Press 1 to fetch all records of " + l.getLoginUserId() + "\nPress 2 to Logout");
						int option = sc.nextInt();
						switch (option) {
						case 1:{
							LoginInfoDao.findAll(l.getLoginUserId());
							System.out.println("Press 1 to logout\nPress 2 to continue.");
							int opt = sc.nextInt();
							if( opt == 1) {
								flag = false;
								sc.close();
							}
							else if(opt != 2) {
								System.out.println("Wrong input!!");
							}
						}

						break;

						case 2: {
							flag = false;
							sc.close();
						}
						break;
						default:
							System.out.println("Wrong option selected !!!");
						}
					}
				}
				break;

				default:
					System.out.println("Option not available.");
				}

			} catch (InputMismatchException e) {
				System.out.println("Wrong Input.");
			}
		}
	}
}
