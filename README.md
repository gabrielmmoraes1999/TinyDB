# MoraesDB

MoraesDB é uma lib para criar e manipular um banco de dados com base em um arquivo json.

# Exemplo de uso

<br/>
Exemplo de Insert.

```java
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
```

<br/>
Exemplo de Select

```java
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
```

<br/>
Exemplo de Delete

```java
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
```