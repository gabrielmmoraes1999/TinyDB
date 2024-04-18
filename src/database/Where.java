package database;

/**
 *
 * @author Gabriel Moraes
 */
public class Where {

    private final String field;
    private String operator;
    private Object value;

    public Where(String field) {
        this.operator = null;
        this.field = field;
        this.value = null;
    }

    public Where equalThan(Object value) {
        this.operator = "=";
        this.value = value;
        return this;
    }

    public Where greaterThan(Object value) {
        this.operator = ">";
        this.value = value;
        return this;
    }

    public Where lessThan(Object value) {
        this.operator = "<";
        this.value = value;
        return this;
    }

    public String getField() {
        return field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Query{" + "field=" + field + ", operator=" + operator + ", value=" + value + '}';
    }

}
