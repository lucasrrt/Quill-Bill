package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;

public class UsersController extends RestController{
	@Override
	public String create(Map<String, String> params) {
		String username = params.get("username");
		String password_hash = params.get("password");
		
		return Integer.toString(db.update("insert into users(username,password_hash) values (?,?)", username,password_hash));
	}
}
