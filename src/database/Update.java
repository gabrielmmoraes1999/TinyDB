package database;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Gabriel Moraes
 */
public class Update {

    List<JSONObject> list;

    protected Update(Table table, JSONObject obj, Where[] where) {
        list = new ArrayList<>();

        if (where.length == 0) {
            JSONObject json = table.getDataTable();

            json.keySet().forEach(index -> {
                JSONObject row = json.getJSONObject(index);

                obj.keySet().forEach(colunm -> {
                    row.get(colunm);
                    row.put(colunm, obj.get(colunm));
                });

                list.add(row);
            });
        }
    }

    public List<JSONObject> getResult() {
        return list;
    }
}
