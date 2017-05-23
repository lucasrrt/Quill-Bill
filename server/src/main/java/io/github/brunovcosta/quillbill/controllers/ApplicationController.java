package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;

public class ApplicationController {
	protected static String TABLE_NAME;

	public static String index(Map<String,String> params){
		return DB.table2string(DB.query("select * from"+TABLE_NAME));
	}
	
	public static String show(Map<String,String> params){
		return DB.table2string(DB.query("select * from"+TABLE_NAME+" where id ="+params.get("id")));
	}

	public static String delete(Map<String,String> params){
		return Integer.toString(DB.update("delete * from"+TABLE_NAME+" where id ="+params.get("id")));
	}

}
