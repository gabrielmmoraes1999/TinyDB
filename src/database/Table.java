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
        List<JSONObject> list;

        if (where.length == 0) {
            list = new ArrayList<>();
            this.dataTable.keySet().forEach((key) -> {
                list.add(this.dataTable.getJSONObject(key));
            });
            return list;
        }

        list = new ArrayList<>();
        List<String> remove = new ArrayList<>();
        JSONObject fullData = new JSONObject(this.dataTable.toString());
        int condition = -1;

        for (Where wh : where) {
            int size = wh.getKey().size();

            if (size >= 2) {
                for (Integer conditio : wh.getConditions()) {
                    condition = conditio;
                }
            }

            switch (condition) {
                case -1:
                    break;
                case Where.AND:
                    for (String key : wh.getKey()) {
                        int indexOf = wh.getKey().indexOf(key);
                        Object value = wh.getValue().get(indexOf);

                        for (String keyData : fullData.keySet()) {
                            JSONObject row = fullData.getJSONObject(keyData);

                            if (!value.equals(row.get(key))) {
                                if (!remove.contains(keyData)) {
                                    remove.add(keyData);
                                }
                            }
                        }
                    }
                    break;
                case Where.OR:
                    for (String key : wh.getKey()) {
                        int indexOf = wh.getKey().indexOf(key);
                        Object value = wh.getValue().get(indexOf);

                        for (String keyData : fullData.keySet()) {
                            JSONObject row = fullData.getJSONObject(keyData);
                            if (value.equals(row.get(key))) {
                                if (!list.contains(row)) {
                                    list.add(row);
                                }
                            }
                        }
                    }
                    break;
            }
        }

        if (condition == Where.AND) {
            for (String keyData : remove) {
                fullData.remove(keyData);
            }

            for (String keyData : fullData.keySet()) {
                JSONObject row = fullData.getJSONObject(keyData);
                list.add(row);
            }
        }

        return list;
    }

    public void delete(Where... where) {
        List<String> delete = new ArrayList<>();

        if (where.length == 0) {
            this.dataTable.clear();
        }

        int condition = -1;

        for (Where wh : where) {
            int size = wh.getKey().size();

            if (size >= 2) {
                for (Integer conditio : wh.getConditions()) {
                    condition = conditio;
                }
            }

            switch (condition) {
                case -1:
                    for (String key : wh.getKey()) {
                        int indexOf = wh.getKey().indexOf(key);
                        Object value = wh.getValue().get(indexOf);

                        for (String keyData : this.dataTable.keySet()) {
                            JSONObject row = this.dataTable.getJSONObject(keyData);

                            if (value.equals(row.get(key))) {
                                if (!delete.contains(keyData)) {
                                    delete.add(keyData);
                                }
                            }
                        }
                    }
                    break;
                case Where.AND:
                    for (String key : wh.getKey()) {
                        int indexOf = wh.getKey().indexOf(key);
                        Object value = wh.getValue().get(indexOf);

                        for (String keyData : this.dataTable.keySet()) {
                            JSONObject row = this.dataTable.getJSONObject(keyData);

                            if (value.equals(row.get(key))) {
                                if (!delete.contains(keyData)) {
                                    delete.add(keyData);
                                }
                            }
                        }
                    }
                    break;
                case Where.OR:
                    for (String key : wh.getKey()) {
                        int indexOf = wh.getKey().indexOf(key);
                        Object value = wh.getValue().get(indexOf);

                        for (String keyData : this.dataTable.keySet()) {
                            JSONObject row = this.dataTable.getJSONObject(keyData);
                            if (value.equals(row.get(key))) {
                                if (!delete.contains(keyData)) {
                                    delete.add(keyData);
                                }
                            }
                        }
                    }
                    break;
            }
        }

        for (String keyData : delete) {
            this.dataTable.remove(keyData);
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
