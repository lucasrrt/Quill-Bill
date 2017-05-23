package io.github.brunovcosta.quillbill.controllers;

import java.util.HashMap;
import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;

public class UsersController {
	public static String index(Map<String,String> params){
		return DB.table2string(DB.query("select * from users"));
	}
}
