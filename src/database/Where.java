package database;

/**
 *
 * @author Gabriel Moraes
 */
public class Where {

    private final Where[] vector = new Where[2];
    private final String field;
    private String condition;
    private String operator;
    private Object value;

    public Where(String field) {
        this.operator = null;
        this.field = field;
        this.value = null;
        this.condition = "";
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

    public Where greaterOrEqualThan(Object value) {
        this.operator = ">=";
        this.value = value;
        return this;
    }

    public Where lessThan(Object value) {
        this.operator = "<";
        this.value = value;
        return this;
    }

    public Where lessOrEqualThan(Object value) {
        this.operator = "<=";
        this.value = value;
        return this;
    }

    public Where[] and(Where where) {
        this.vector[0] = this;

        where.setCondition("AND");
        this.vector[1] = where;
        return vector;
    }

    public Where[] or(Where where) {
        this.vector[0] = this;

        where.setCondition("OR");
        this.vector[1] = where;
        return vector;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Query{" + "field=" + field + ", operator=" + operator + ", value=" + value + '}';
    }

}
