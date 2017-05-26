package io.github.brunovcosta.quillbill.controllers;

import io.github.brunovcosta.quillbill.models.Bill;
import spark.Request;
import spark.Response;

public class UsersBillsController extends ApplicationController {
	public String create(Request req, Response res){
		int billId = Integer.parseInt(req.queryParams("bill_id"));
		int userId = Integer.parseInt(req.queryParams("user_id"));

		return Bill.createIt("bill_id", billId, "user_id", userId).getString("id");

	}

	@Override
	public String index(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String destroy(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}
}
