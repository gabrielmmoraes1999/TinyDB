package database;

import org.json.JSONObject;

import java.util.Objects;

public class Delete {

    protected Delete(Table table, Where[] where) {
        if (where.length == 0) {
            table.getDataTable().clear();
        }

        for (Where wh : where) {
            switch (wh.getOperator()) {
                case "=":
                    table.getDataTable().keySet().forEach(keyData -> {
                        JSONObject row = table.getDataTable().getJSONObject(keyData);

                        if (wh.getValue().getClass() == Integer.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (int) a == (int) b);
                        } else if (wh.getValue().getClass() == Double.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> Objects.equals((Double) a, (Double) b));
                        } else {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> a.equals(b));
                        }
                    });
                    break;
                case "<":
                    table.getDataTable().keySet().forEach(keyData -> {
                        JSONObject row = table.getDataTable().getJSONObject(keyData);

                        if (wh.getValue().getClass() == Integer.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (int) a < (int) b);
                        } else if (wh.getValue().getClass() == Double.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (Double) a < (Double) b);
                        }
                    });
                    break;
                case "<=":
                    table.getDataTable().keySet().forEach(keyData -> {
                        JSONObject row = table.getDataTable().getJSONObject(keyData);

                        if (wh.getValue().getClass() == Integer.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (int) a <= (int) b);
                        } else if (wh.getValue().getClass() == Double.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (Double) a <= (Double) b);
                        }
                    });
                    break;
                case ">":
                    table.getDataTable().keySet().forEach(keyData -> {
                        JSONObject row = table.getDataTable().getJSONObject(keyData);

                        if (wh.getValue().getClass() == Integer.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (int) a > (int) b);
                        } else if (wh.getValue().getClass() == Double.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (Double) a > (Double) b);
                        }
                    });
                    break;
                case ">=":
                    table.getDataTable().keySet().forEach(keyData -> {
                        JSONObject row = table.getDataTable().getJSONObject(keyData);

                        if (wh.getValue().getClass() == Integer.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (int) a >= (int) b);
                        } else if (wh.getValue().getClass() == Double.class) {
                            Util.remove(table.getDataTable(), keyData, row, wh, (a, b) -> (Double) a >= (Double) b);
                        }
                    });
                    break;
            }
        }
    }
}
