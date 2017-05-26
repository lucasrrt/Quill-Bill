package io.github.brunovcosta.quillbill.controllers;

import org.javalite.activejdbc.LazyList;

import io.github.brunovcosta.quillbill.helpers.HashHelper;
import io.github.brunovcosta.quillbill.models.Authentication;
import io.github.brunovcosta.quillbill.models.User;
import spark.Request;
import spark.Response;

public class AuthenticationsController extends ApplicationController{

	@Override
	public String create(Request req, Response res) {
		String username = req.queryParams("username");
		String hash = req.queryParams("password");

		int id = User.findFirst("username = ? and password_hash = ?", username,hash).getInteger("id");

		String token = HashHelper.hash();

		return Authentication.create("token",token,"user_id",id).toJson(true, "token","user_id");
	}

	@Override
	public String index(Request req, Response res) {
		LazyList<Authentication> auth = Authentication.find("token = ?", req.queryParams("token"));
		return auth.toJson(false, "token","user_id");
	}

	@Override
	public String show(Request req, Response res) {
		Authentication auth = Authentication.findFirst("token = ?", req.queryParams("token"));
		return auth.toJson(false, "token","user_id");
	}

	@Override
	public String destroy(Request req, Response res) {
		Authentication.delete("token = ?", req.queryParams("token"));
		return null;
	}

	@Override
	public String update(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}
}