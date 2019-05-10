package wsconsumer.services;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class HttpConn {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    private String url;
    private HashMap<String, String> parametros;

    private String type;

    public HttpConn(String url, HashMap<String, String> parametros, String type) {
        this.url = url;
        this.parametros = parametros;
        this.type = type;
    }

    public HttpConn(String url) {
        this.url = url;
        this.parametros = new HashMap<>();
        this.type = GET;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, String> parametros) {
        this.parametros = parametros;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String execute() throws MalformedURLException, IOException {
        String charset = "UTF-8";
        String paramStr = "";
        if (this.parametros.size() > 0) {
            StringBuilder qry = new StringBuilder();
            for (Map.Entry<String, String> entry : parametros.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                qry.append(key + "=" + URLEncoder.encode(value, charset) + "&");
            }

            if (type.equals(GET)) {
                this.url += ("?" + qry.toString());
            }
            paramStr = qry.toString();
        }
        URL urla = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) urla.openConnection();
        connection.setRequestMethod(type);

        if (type.equals(POST) || type.equals(PUT) || type.equals(DELETE)) {
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(paramStr);
            wr.flush();
            wr.close();
        }

        String responseBody = "";
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {

            responseBody = scanner.useDelimiter("\\A").next();
        }
        return responseBody;
    }
}