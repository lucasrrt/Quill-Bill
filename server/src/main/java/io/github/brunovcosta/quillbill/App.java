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

		get("/"+table+"", (req, res) -> controller.index(req.params()));
		get("/"+table+"/:id", (req, res) -> controller.show(req.params()));
		put("/"+table+"/:id", (req, res) -> controller.update(req.params()));
		post("/"+table, (req, res) -> controller.create(req.params()));
		delete("/"+table+"/:id", (req, res) -> controller.destroy(req.params()));
	}
}
