package io.github.brunovcosta.quillbill.controllers;

import org.javalite.activejdbc.LazyList;

import io.github.brunovcosta.quillbill.models.Authentication;
import io.github.brunovcosta.quillbill.models.Bill;
import io.github.brunovcosta.quillbill.models.BillsUsers;
import spark.Request;
import spark.Response;

public class BillsController extends ApplicationController {
	@Override
	public String create(Request req, Response res) {
		String token = req.queryParams("token");
		int userId = Authentication.findFirst("token = ?",token).getInteger("id");

		String name = req.queryParams("name");
		
		return Bill.create("name",name,"user_id",userId).toJson(true, "name","user_id");
	}

	@Override
	public String index(Request req, Response res) {
		int userId = Authentication.findFirst("token = ?", req.queryParams("token")).getInteger("user_id");
		LazyList<BillsUsers> billsUsers = BillsUsers.where("user_id = ?", userId);
		
		LazyList<Bill> bills = Bill.where("id in ?", billsUsers.collect("bill_id"));
		
		return bills.toJson(false, "name");
	}

	@Override
	public String show(Request req, Response res) {
		Bill bill = Bill.findFirst("id = ?", req.queryParams("id"));
		
		return bill.toJson(false, "name");
	}

	@Override
	public String destroy(Request req, Response res) {
		return Integer.toString(Bill.delete("id = ?", req.queryParams("id")));
	}

	@Override
	public String update(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}
}
