package com.mixicoding.manageapp;

import java.awt.Font;

import com.minhhuy.developeui.*;
import com.mixicoding.manageapp.database.*;
import com.mixicoding.manageapp.giaodien.*;

public class Main {
	 public static void main(String args[]) {
		 Database.getInstance().connect();
		 AppForm appForm = new AppForm();

	 }
}
