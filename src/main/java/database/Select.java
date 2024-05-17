package database;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Select {
    private final Table table;
    private final Where[] where;

    protected Select(Table table, Where[] where) {
        this.table = table;
        this.where = where;
    }

    protected List<JSONObject> getResult() {
        List<JSONObject> list = new ArrayList<>();
        List<JSONObject> remove = new ArrayList<>();
        JSONObject fullData = new JSONObject(table.getDataTable().toString());

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
                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (int) a == (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> Objects.equals((Double) a, (Double) b));
                                } else {
                                    Util.compareRemove(remove, row, wh, Object::equals);
                                }
                            });
                            break;
                        case "<":
                            list.forEach(row -> {
                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (int) a >= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (Double) a >= (Double) b);
                                }
                            });
                            break;
                        case "<=":
                            list.forEach(row -> {
                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (int) a > (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (Double) a > (Double) b);
                                }
                            });
                            break;
                        case ">":
                            list.forEach(row -> {
                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (int) a <= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (Double) a <= (Double) b);
                                }
                            });
                            break;
                        case ">=":
                            list.forEach(row -> {
                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (int) a < (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareRemove(remove, row, wh, (a, b) -> (Double) a < (Double) b);
                                }
                            });
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

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a == (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> Objects.equals((Double) a, (Double) b));
                                } else {
                                    Util.compareAdd(list, row, wh, (a, b) -> a.equals(b));
                                }
                            });
                            break;
                        case "<":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a < (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a < (Double) b);
                                }
                            });
                            break;
                        case "<=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a <= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a <= (Double) b);
                                }
                            });
                            break;
                        case ">":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a > (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a > (Double) b);
                                }
                            });
                            break;
                        case ">=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a >= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a >= (Double) b);
                                }
                            });
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

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a == (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> Objects.equals((Double) a, (Double) b));
                                } else {
                                    Util.compareAdd(list, row, wh, Object::equals);
                                }
                            });
                            break;
                        case "<":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a < (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a < (Double) b);
                                }
                            });
                            break;
                        case "<=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a <= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a <= (Double) b);
                                }
                            });
                            break;
                        case ">":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a > (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a > (Double) b);
                                }
                            });
                            break;
                        case ">=":
                            fullData.keySet().forEach(keyData -> {
                                JSONObject row = fullData.getJSONObject(keyData);

                                if (wh.getValue().getClass() == Integer.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (int) a >= (int) b);
                                } else if (wh.getValue().getClass() == Double.class) {
                                    Util.compareAdd(list, row, wh, (a, b) -> (Double) a >= (Double) b);
                                }
                            });
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }

        remove.forEach(list::remove);
        return list;
    }
}
