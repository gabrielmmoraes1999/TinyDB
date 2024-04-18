package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gabriel Moraes
 */
public class Table {

    private int index;
    private final String name;
    private final JSONObject data;
    private final JSONObject table;
    private JSONObject dataTable;

    public Table(MoraesDB db, String name) {
        this.name = name;
        this.data = db.getData();
        this.index = this.getLastIndex();

        try {
            this.setDataTable(this.data.getJSONObject(name));
        } catch (JSONException ex) {
            this.setDataTable(new JSONObject());
        }

        this.data.put(name, dataTable);
        this.table = new JSONObject();
        this.table.put(name, dataTable);
    }

    public Integer insert(JSONObject obj) throws IOException {
        String indexNext = String.valueOf(this.nextIndex());
        dataTable.put(indexNext, obj);
        return Integer.valueOf(indexNext);
    }

    public List<JSONObject> select(Where... where) {
        List<JSONObject> list = new ArrayList<>();
        List<JSONObject> remove = new ArrayList<>();
        JSONObject fullData = new JSONObject(this.dataTable.toString());

        if (where.length == 0) {
            fullData.keySet().forEach((key) -> {
                list.add(fullData.getJSONObject(key));
            });
            return list;
        }

        for (Where wh : where) {
            switch (wh.getCondition()) {
                case "AND":
                    switch (wh.getOperator()) {
                        case "=":
                            list.forEach(row -> {
                                if (!wh.getValue().equals(row.get(wh.getField()))) {
                                    if (!remove.contains(row)) {
                                        remove.add(row);
                                    }
                                }
                            });
                            break;
                        case "<":
                            break;
                        case ">":
                            break;
                        default:
                            break;
                    }
                    break;
                case "OR":
                    switch (wh.getOperator()) {
                        case "=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().equals(row.get(wh.getField()))) {
                                    if (!list.contains(row)) {
                                        list.add(row);
                                    }
                                }
                            });
                            break;
                        case "<":
                            break;
                        case ">":
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    switch (wh.getOperator()) {
                        case "=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().equals(row.get(wh.getField()))) {
                                    if (!list.contains(row)) {
                                        list.add(row);
                                    }
                                }
                            });
                            break;
                        case "<":
                            break;
                        case ">":
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }

        remove.forEach((row) -> {
            list.remove(row);
        });

        return list;
    }

    public void delete(Where... where) {
        if (where.length == 0) {
            this.dataTable.clear();
        }

        for (Where wh : where) {
            switch (wh.getOperator()) {
                case "=":
                    this.dataTable.keySet().forEach(keyData -> {
                        JSONObject row = this.dataTable.getJSONObject(keyData);

                        if (wh.getValue().equals(row.get(wh.getField()))) {
                            this.dataTable.remove(keyData);
                        }
                    });
                    break;
                case "<":
                    break;
                case ">":
                    break;
            }
        }
    }

    private int nextIndex() {
        this.index++;
        return index;
    }

    private Integer getLastIndex() {
        try {
            JSONObject object = this.data.getJSONObject(this.name);
            Set<String> set = object.keySet();
            Iterator<String> iterador = set.iterator();

            String indexIn = "0";
            while (iterador.hasNext()) {
                indexIn = iterador.next();
            }

            return Integer.valueOf(indexIn);
        } catch (JSONException ex) {
            return 0;
        }
    }

    private void setDataTable(JSONObject dataTable) {
        this.dataTable = dataTable;
    }
}
