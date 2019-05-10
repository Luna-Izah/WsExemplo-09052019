package wsconsumer.providers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import wsconsumer.models.Produto;
import wsconsumer.services.HttpConn;
//https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1.1.redhat-1
/**
 *
 * @author drink
 */
public class ProdutoProvider {

    public List<Produto> getProdutos() throws Exception {
        List<Produto> lp = new ArrayList<Produto>();
        HttpConn conn = new HttpConn(""
                + "http://service.adrianob.com.br/produto/");
        String res = "[]";
        try {
            res = conn.execute();
        } catch (IOException ex) {
            throw new Exception(ex);
        }
        JSONParser jp = new JSONParser();
        JSONObject parse = (JSONObject) jp.parse(res);
        if (!parse.get("status").equals("success")) {
            throw new Exception((String) parse.get("messageError"));
        }
        if (parse.get("status").equals("success")) {
            JSONArray jarr = (JSONArray) parse.get("data");
            Iterator it = jarr.iterator();
            while (it.hasNext()) {
                JSONObject prdJS = (JSONObject) it.next();
                Produto p = new Produto();
                p.setId(Integer.parseInt((String) prdJS.get("ModelProdutoid")));
                p.setNome((String) prdJS.get("ModelProdutonome"));
                lp.add(p);
            }
        }
        return lp;
    }

    public void salvar(Produto p) throws Exception {
        HttpConn conn = new HttpConn(""
                + "http://service.adrianob.com.br/produto/");
        HashMap map = new HashMap();
        map.put("id", Integer.toString(p.getId()));
        map.put("nome", p.getNome());
        conn.setParametros(map);
        if (p.getId() > 0) {
            conn.setType(HttpConn.PUT);
        } else {
            conn.setType(HttpConn.POST);
        }
        try {
            conn.execute();
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void remover(Produto p) throws Exception {
        HttpConn conn = new HttpConn(""
                + "http://service.adrianob.com.br/produto/");
        HashMap map = new HashMap();
        map.put("id", Integer.toString(p.getId()));
        conn.setParametros(map);
        conn.setType(HttpConn.DELETE);
        try {
            conn.execute();
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
