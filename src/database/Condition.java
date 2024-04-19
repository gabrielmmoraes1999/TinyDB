package database;

/**
 *
 * @author Gabriel Moraes
 */
interface Condition {

    boolean compare(Object x, Object y);
}
