package exemplo;

import database.MoraesDB;
import database.Table;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Gabriel Moraes
 */
public class ExemploInsert {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        MoraesDB db = new MoraesDB("database.json");
        Table table = db.table("USUARIO");
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("CODIGO", 1);
        jsonObj.put("NOME", "GABRIEL MORAES");
        jsonObj.put("USUARIO", "GABRIEL");
        jsonObj.put("STATUS", true);
        jsonObj.put("NUMERO", 5.0);
        table.insert(jsonObj);
        
        db.close();
    }
    
}
