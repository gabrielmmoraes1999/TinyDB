package exemplo;

import database.MoraesDB;
import database.Table;
import java.io.IOException;
import org.json.JSONObject;

public class ExemploInsert {

    public static void main(String[] args) throws IOException {

        //Criar uma instancia do banco
        MoraesDB db = new MoraesDB("database.json");

        //Cria ou Localiza uma tabela no banco de dados
        Table table = db.table("USUARIO");

        //Dados da tabela a ser inserido
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("CODIGO", 1);
        jsonObj.put("NOME", "GABRIEL MORAES");
        jsonObj.put("USUARIO", "GABRIEL");
        jsonObj.put("STATUS", true);
        jsonObj.put("NUMERO", 5.0);
        table.insert(jsonObj);

        //Salva as alterações e fecha o banco de dados
        db.close();
    }

}
