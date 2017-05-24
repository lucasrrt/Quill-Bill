package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;
import spark.Request;
import spark.Response;

public class UsersController extends RestController{
	@Override
	public String create(Request req,Response res) {
		String username = req.queryParams("username");
		String password_hash = req.queryParams("password");
		
		return Integer.toString(db.update("insert into users(username,password_hash) values (?,?)", username,password_hash));
	}
}
