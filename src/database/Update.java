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
        JSONObject json = table.getDataTable();
        list = new ArrayList<>();

        if (where.length == 0) {
            json.keySet().forEach(index -> {
                JSONObject row = json.getJSONObject(index);

                obj.keySet().forEach(colunm -> {
                    row.get(colunm);
                    row.put(colunm, obj.get(colunm));
                });

                list.add(row);
            });
        }
        
        for (Where wh : where) {
            switch (wh.getCondition()) {
                case "AND":
                    break;
                case "OR":
                    break;
                default:
                    switch (wh.getOperator()) {
                        case "=":
                            break;
                        case "<":
                            break;
                        case "<=":
                            break;
                        case ">":
                            break;
                        case ">=":
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }
    }

    public List<JSONObject> getResult() {
        return list;
    }
}
