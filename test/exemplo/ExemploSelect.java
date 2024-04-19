package exemplo;

import database.MoraesDB;
import database.Table;
import database.Where;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;

public class ExemploSelect {

    public static void main(String[] args) throws IOException {

        //Criar uma instancia do banco
        MoraesDB db = new MoraesDB("database.json");

        //Cria ou Localiza uma tabela no banco de dados
        Table table = db.table("USUARIO");

        //Select sem where retorna todos os dados da tabela
        List<JSONObject> js = table.select();
        System.out.println(js);

        //Exemplo de uso com Where
        js = table.select(
                new Where("CODIGO").equalThan(1).and(
                        new Where("NOME").equalThan("GABRIEL MORAES")));
        System.out.println(js);

        js = table.select(
                new Where("NUMERO").lessThan(6.0).and(
                        new Where("CODIGO").equalThan(2)));
        System.out.println(js);
    }

}
