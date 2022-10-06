import static spark.Spark.*;

public class Aplication {

    public static void main(String[] args) {

        port(15694);
        staticFileLocation("/front-end");

    }

}
