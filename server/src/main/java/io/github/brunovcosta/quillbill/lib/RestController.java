package io.github.brunovcosta.quillbill.lib;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;

import spark.Request;
import spark.Response;

public abstract class RestController {
	private String overrideTable;

	public RestController(){
	}

	public RestController(String table){
		this();
		this.overrideTable = table;
	}
	
	public String getTableName(){
		if(overrideTable==null){
			return this
				.getClass().
				getName()
				.replace("Controller", "")
				.replaceAll(".*\\.", "")
				.toLowerCase();
		}else{
			return overrideTable;
		}
	}

	public abstract String index(Request req,Response res);
	public abstract String show(Request req,Response res);
	public abstract String destroy(Request req,Response res);
	public abstract String update(Request req,Response res);
	public abstract String create(Request req,Response res);
}
