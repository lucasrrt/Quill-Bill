package io.github.brunovcosta.quillbill;
import static spark.Spark.*;

import io.github.brunovcosta.quillbill.controllers.AuthenticationsController;
import io.github.brunovcosta.quillbill.controllers.BillsController;
import io.github.brunovcosta.quillbill.controllers.UsersController;
import io.github.brunovcosta.quillbill.lib.DB;
import io.github.brunovcosta.quillbill.lib.RestController;
import io.github.brunovcosta.quillbill.models.User;

public class App {
	public static void main( String[] args ) {
		staticFileLocation( "/public");


		restMap(new UsersController());
		restMap(new AuthenticationsController());
		restMap(new BillsController());
	}

	private static void restMap(RestController controller){
		String table = controller.getTableName();

		get("/"+table, (req, res)->{
			DB db = new DB();
			org.javalite.activejdbc.Base.open(db.DRIVER,"jdbc:postgresql://"+db.HOST+":"+db.PORT+"/"+db.DATABASE,db.USER,db.PASSWORD);
			return controller.index(req,res);
		});
		get("/"+table+"/:id", (req, res)->{
			DB db = new DB();
			org.javalite.activejdbc.Base.open(db.DRIVER,"jdbc:postgresql://"+db.HOST+":"+db.PORT+"/"+db.DATABASE,db.USER,db.PASSWORD);
			return controller.show(req,res);
		});
		post("/"+table, (req, res)->{
			DB db = new DB();
			org.javalite.activejdbc.Base.open(db.DRIVER,"jdbc:postgresql://"+db.HOST+":"+db.PORT+"/"+db.DATABASE,db.USER,db.PASSWORD);
			return controller.create(req,res);
		});
		put("/"+table+"/:id", (req, res)->{
			DB db = new DB();
			org.javalite.activejdbc.Base.open(db.DRIVER,"jdbc:postgresql://"+db.HOST+":"+db.PORT+"/"+db.DATABASE,db.USER,db.PASSWORD);
			return controller.update(req,res);
		});
		delete("/"+table+"/:id", (req, res)->{
			DB db = new DB();
			org.javalite.activejdbc.Base.open(db.DRIVER,"jdbc:postgresql://"+db.HOST+":"+db.PORT+"/"+db.DATABASE,db.USER,db.PASSWORD);
			return controller.destroy(req,res);
		});
	}
}
