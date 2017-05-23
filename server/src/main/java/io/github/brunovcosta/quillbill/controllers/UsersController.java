package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;

public class UsersController implements RestController{
	private DB db;

	public UsersController(){
		db = new DB();
	}

	@Override
	public String index(Map<String,String> params){
		return DB.table2string(db.query("select id,login from users"));
	}
	
	@Override
	public String show(Map<String,String> params){
		return DB.table2string(db.query("select * from users where id ="+params.get("id")));
	}

	@Override
	public String destroy(Map<String,String> params){
		return Integer.toString(db.update("delete * from users where id ="+params.get("id")));
	}

	@Override
	public String update(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}
}
