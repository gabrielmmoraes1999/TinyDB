package exemplo;

import database.MoraesDB;
import database.Table;
import database.Where;
import java.io.IOException;

public class ExemploDelete {

    public static void main(String[] args) throws IOException {

        //Criar uma instancia do banco
        MoraesDB db = new MoraesDB("database.json");

        //Cria ou Localiza uma tabela no banco de dados
        Table table = db.table("USUARIO");

        //Delete sem where apaga todo os dados da tabela
        table.delete();

        //Exemplo de delete com where
        table.delete(new Where("USUARIO").equalThan("GABRIEL"));
    }

}
