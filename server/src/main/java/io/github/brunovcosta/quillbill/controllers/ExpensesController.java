package io.github.brunovcosta.quillbill.controllers;

import org.javalite.activejdbc.LazyList;

import io.github.brunovcosta.quillbill.models.Authentication;
import io.github.brunovcosta.quillbill.models.BillsUsers;
import io.github.brunovcosta.quillbill.models.Expense;
import spark.Request;
import spark.Response;

public class ExpensesController extends ApplicationController {
	@Override
	public String create(Request req, Response res) {
		int userId = Authentication.findFirst("token = ?", req.queryParams("token")).getInteger("user_id");
		int billId = Integer.parseInt(req.queryParams("bill_id"));
		int amount = Integer.parseInt(req.queryParams("amount"));
		String desc = req.queryParams("description");

		if(BillsUsers.where("user_id = ? and bill_id = ?", userId,billId).size()>0){
			return Expense.create("description",desc,"user_id",userId,"bill_id",billId,"amount",amount).toJson(true, "amount","bill_id","user_id","description");
		}else{
			res.status(500);
			return "Usuário ou conta incoerente";
		}
	}

	@Override
	public String index(Request req, Response res) {
		LazyList<Expense> expenses = Expense.where("bill_id = ?",req.queryParams("bill_id"));
		return expenses.toJson(false, "description","id","mount","bill_id");
	}

	@Override
	public String show(Request req, Response res) {
		Expense expense = Expense.findFirst("id = ?",req.queryParams("id"));
		return expense.toJson(false, "description","id","mount","bill_id");
	}

	@Override
	public String destroy(Request req, Response res) {
		return Integer.toString(Expense.delete("id = ?",req.queryParams("id")));
	}

	@Override
	public String update(Request req, Response res) {
		int id = Integer.parseInt(req.queryParams("id"));
		int amount = Integer.parseInt(req.queryParams("amount"));
		int count = Expense.update("id = ?", "amount = ?",id,amount);
		return Integer.toString(count);
	}
}
