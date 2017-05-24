package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

public class BillsController extends RestController {
	@Override
	public String create(Map<String, String> params) {
		String userId = params.get("user_id");
		String name = params.get("name");
		
		return Integer.toString(db.update("insert into bills(name,user_id) values (?,?)", name,userId));
	}
}
