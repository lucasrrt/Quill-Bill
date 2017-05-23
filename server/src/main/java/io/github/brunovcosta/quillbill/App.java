package io.github.brunovcosta.quillbill;
import static spark.Spark.*;

import io.github.brunovcosta.quillbill.controllers.RestController;
import io.github.brunovcosta.quillbill.controllers.UsersController;

public class App {
    public static void main( String[] args ) {
    	restMap(new UsersController());
    }
    
    private static void restMap(RestController controller){
        get("/users", (req, res) -> controller.index(req.params()));
        get("/users/:id", (req, res) -> controller.index(req.params()));
        put("/users/:id", (req, res) -> controller.update(req.params()));
        post("/users", (req, res) -> controller.create(req.params()));
        delete("/users", (req, res) -> controller.destroy(req.params()));
    }
}
