package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;
import io.github.brunovcosta.quillbill.helpers.HashHelper;

public class AuthenticationsController implements RestController{
	private DB db;

	public AuthenticationsController(){
		db = new DB();
	}

	@Override
	public String index(Map<String,String> params){
		return DB.table2string(db.query("select * from authentications"));
	}
	
	@Override
	public String show(Map<String,String> params){
		return DB.table2string(db.query("select * from authentications where id ="+params.get("id")));
	}

	@Override
	public String destroy(Map<String,String> params){
		return Integer.toString(db.update("delete * from authentications where id ="+params.get("id")));
	}

	@Override
	public String update(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(Map<String, String> params) {
		String login = params.get("login");
		String hash = params.get("password");

		String id = db.query("select id from users where login = ? and password_hash = ?",login,hash).get(0).get("id");

		String token = HashHelper.hash();
		db.update("insert into authentications(token,user_id) values (?,?)", token,id);

		return token;
	}
}
