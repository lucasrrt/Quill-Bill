package io.github.brunovcosta.quillbill;
import static spark.Spark.*;

import io.github.brunovcosta.quillbill.controllers.UsersController;

public class App {
    public static void main( String[] args ) {
        get("/users", (req, res) -> UsersController.index(req.params()));
    }
}
