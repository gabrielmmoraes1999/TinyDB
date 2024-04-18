package exemplo;

import database.MoraesDB;
import database.Table;
import database.Where;
import java.io.IOException;

/**
 *
 * @author Gabriel Moraes
 */
public class ExemploDelete {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        MoraesDB db = new MoraesDB("database.json");
        Table table = db.table("USUARIO");
        table.delete(new Where("USUARIO").equal("GABRIEL"));
    }
    
}
