package database;

import java.io.IOException;
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
        return new Select(this, where).getResult();
    }

    public List<JSONObject> update(JSONObject obj, Where... where) {
        return new Update(this, obj, where).getResult();
    }

    public void delete(Where... where) {
        new Delete(this, where).toString();
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

    protected JSONObject getDataTable() {
        return this.dataTable;
    }

    private void setDataTable(JSONObject dataTable) {
        this.dataTable = dataTable;
    }
}
