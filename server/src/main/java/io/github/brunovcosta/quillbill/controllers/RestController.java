package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;

public abstract class RestController {
	private String overrideTable;
	protected DB db;

	public RestController(){
		db = new DB();
	}

	public RestController(String table){
		this();
		this.overrideTable = table;
	}
	
	public String getTableName(){
		if(overrideTable==null){
			return this
				.getClass().
				getName()
				.replace("Controller", "")
				.replaceAll(".*\\.", "")
				.toLowerCase();
		}else{
			return overrideTable;
		}
	}

	public String index(Map<String,String> params){
		return DB.table2string(db.query("select * from "+getTableName()));
	}
	
	public String show(Map<String,String> params){
		return DB.table2string(db.query("select * from "+getTableName()+" where id ="+params.get("id")));
	}

	public String destroy(Map<String,String> params){
		return Integer.toString(db.update("delete * from "+getTableName()+" where id ="+params.get("id")));
	}

	public String update(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String create(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}
}
