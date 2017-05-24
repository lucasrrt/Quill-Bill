package io.github.brunovcosta.quillbill;
import static spark.Spark.*;

import io.github.brunovcosta.quillbill.controllers.AuthenticationsController;
import io.github.brunovcosta.quillbill.controllers.BillsController;
import io.github.brunovcosta.quillbill.controllers.RestController;
import io.github.brunovcosta.quillbill.controllers.UsersController;

public class App {
	public static void main( String[] args ) {
		staticFileLocation( "/public");

		restMap(new UsersController());
		restMap(new AuthenticationsController());
		restMap(new BillsController());
	}

	private static void restMap(RestController controller){
		String table = controller.getTableName();

		get("/"+table+"", (req, res) -> controller.index(req,res));
		get("/"+table+"/:id", (req, res) -> controller.show(req,res));
		put("/"+table+"/:id", (req, res) -> controller.update(req,res));
		post("/"+table, (req, res) -> controller.create(req,res));
		delete("/"+table+"/:id", (req, res) -> controller.destroy(req,res));
	}
}
