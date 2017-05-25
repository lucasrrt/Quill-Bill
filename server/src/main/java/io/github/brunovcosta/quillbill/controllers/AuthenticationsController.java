package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import io.github.brunovcosta.quillbill.helpers.DB;
import io.github.brunovcosta.quillbill.helpers.HashHelper;
import spark.Request;
import spark.Response;

public class AuthenticationsController extends RestController{

	@Override
	public String create(Request req, Response res) {
		String username = req.queryParams("username");
		String hash = req.queryParams("password");

		String id = db.query("select id from users where username = ? and password_hash = ?",username,hash).get(0).get("id");

		String token = HashHelper.hash();
		db.update("insert into authentications(token,user_id) values (?,?)", token,id);

		return token;
	}
}