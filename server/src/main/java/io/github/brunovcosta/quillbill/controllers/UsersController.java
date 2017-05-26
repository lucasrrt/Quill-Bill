package io.github.brunovcosta.quillbill.controllers;

import org.javalite.activejdbc.LazyList;

import io.github.brunovcosta.quillbill.models.User;
import spark.Request;
import spark.Response;

public class UsersController extends ApplicationController{
	@Override
	public String create(Request req,Response res) {
		User user = new User();
		user.set("username",req.queryParams("username"));
		user.set("password_hash",req.queryParams("password"));
		return Boolean.toString(user.save());
	}

	@Override
	public String index(Request req, Response res) {
		LazyList<User> users = User.findAll();
		return users.toJson(false, "username");
	}

	@Override
	public String show(Request req, Response res) {
		int id = Integer.parseInt(req.params(":id"));
		User user = User.findById(id);
		return user.toJson(false, "username");
	}

	@Override
	public String destroy(Request req, Response res) {
		int id = Integer.parseInt(req.params(":id"));
		int count = User.delete("id = ?",id);
		return Integer.toString(count);
	}

	@Override
	public String update(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		int id = Integer.parseInt(req.params(":id"));
		int count = User.updateAll("username = ? , password = ?","id = ?", username, password, id);

		return Integer.toString(count);
	}
}
