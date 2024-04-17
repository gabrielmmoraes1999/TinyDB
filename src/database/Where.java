package database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Moraes
 */
public class Where {

    public static final int AND = 0;
    public static final int OR = 1;

    private List<String> key;
    private List<Object> value;
    private List<Integer> conditions;

    public Where(Object... objs) {
        this.key = new ArrayList();
        this.value = new ArrayList();
        this.conditions = new ArrayList();

        if (objs.length == 2) {
            this.key.add((String) objs[0]);
            this.value.add(objs[1]);
        } else if (objs.length > 2) {
            int order = 0;
            for (Object obj : objs) {
                order++;
                
                switch(order) {
                    case 1:
                        this.key.add((String) obj);
                        break;
                    case 2:
                        this.value.add(obj);
                        break;
                    case 3:
                        this.conditions.add((Integer) obj);
                        break;
                }

                if (order == 3) {
                    order = 0;
                }
            }
        }
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }

    public List<Integer> getConditions() {
        return conditions;
    }

    public void setConditions(List<Integer> conditions) {
        this.conditions = conditions;
    }

}
