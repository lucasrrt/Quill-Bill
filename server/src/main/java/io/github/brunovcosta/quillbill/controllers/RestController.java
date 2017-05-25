package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;
import spark.Request;
import spark.Response;

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

	public String index(Request req,Response res){
		return DB.table2string(db.query("select * from "+getTableName()));
	}
	
	public String show(Request req,Response res){
		return DB.table2string(db.query("select * from "+getTableName()+" where id ="+req.queryParams("id")));
	}

	public String destroy(Request req,Response res){
		return Integer.toString(db.update("delete * from "+getTableName()+" where id ="+req.queryParams("id")));
	}

	public String update(Request req,Response res) {
		// TODO Auto-generated method stub
		return null;
	}

	public String create(Request req,Response res) {
		// TODO Auto-generated method stub
		return null;
	}
}
