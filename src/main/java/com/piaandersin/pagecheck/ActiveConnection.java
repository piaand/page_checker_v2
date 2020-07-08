package com.piaandersin.pagecheck;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class that makes sure connection to given URL is handled correctly, example
 * opening and closing the connection.
 *
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveConnection {

    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());

    private URL url;
    private HttpURLConnection connection;

    public void setConnection() throws IOException {
        HttpURLConnection con = this.getConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "plain/text");
        con.setConnectTimeout(7000);
        con.setReadTimeout(7000);
    }

    public void openConnection() throws IOException {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            this.setConnection(con);
        } catch (IOException exception) {
            logger.log(Level.WARNING, "Error in opening the connection.");
            throw exception;
        }
    }

    public void closeConnection() {
        try {
            HttpURLConnection con = this.getConnection();
            con.disconnect();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error in closing the connection.");
        }
    }

    public int getStatusCode() {
        try {
            HttpURLConnection con = this.getConnection();
            int code = con.getResponseCode();
            return (code);
        } catch (IOException exception) {
            logger.log(Level.WARNING, "Error in retreiving the statuscode.");
            return (-1);
        }
    }

    public InputStreamReader openInputStream() throws IOException {
        HttpURLConnection con = this.getConnection();
        InputStreamReader reader = new InputStreamReader(con.getInputStream());
        return (reader);
    }
}
