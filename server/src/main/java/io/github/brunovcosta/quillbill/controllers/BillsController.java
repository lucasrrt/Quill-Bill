package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

import spark.Request;
import spark.Response;

public class BillsController extends RestController {
	@Override
	public String create(Request req, Response res) {
		String userId = req.queryParams("user_id");
		String name = req.queryParams("name");
		
		return Integer.toString(db.update("insert into bills(name,user_id) values (?,?)", name,userId));
	}
}
