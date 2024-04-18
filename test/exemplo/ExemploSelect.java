package exemplo;

import database.MoraesDB;
import database.Table;
import database.Where;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Gabriel Moraes
 */
public class ExemploSelect {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        MoraesDB db = new MoraesDB("database.json");
        Table table = db.table("USUARIO");

        List<JSONObject> js = table.select();
        System.out.println(js);

        js = table.select(new Where("CODIGO").equalThan(1));

        System.out.println(js);
    }

}
