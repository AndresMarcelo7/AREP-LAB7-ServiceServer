package edu.eci.arep.SecureSparkApp;

/**
 * Hello world!
 *
 */
import com.google.gson.Gson;
import spark.Filter;
import spark.staticfiles.StaticFilesConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class SecureSparkServices {

    public static void main(String[] args) {
        port(getPort());

        //Se le dice a Spark: Utilice estos certificados y en particular utilice este certificado:
        //secure("keystores/ecikeystore.p12", "123456", null, null);

        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);
        //secure("keystores/ecikeystore.p12","123456",null,null);
        secure("keystores/ecikeystoreB.p12","123456","keystores/myTrustStore","567890");

        get("/service",((req, res) -> "Hello! I'm the Service Server and today is: " + now()
        ));
    }

    private static String now() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001; //returns default port if heroku-port isn't set(i.e on localhost)
    }

}