package uwu.smsgamer.senapi.utils.sql;

import uwu.smsgamer.senapi.utils.Pair;

import java.sql.*;
import java.util.List;

public interface SenDB {
    void initialize(Pair<String, String>... rows);

    void connect();

    default void disconnect() {
        if (!isConnected()) {
            try {
                getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    default void tryReconnect() {
        if (!isConnected()) connect();
    }

    Connection getConnection();

    boolean isConnected();

    Table getTable(String table);

    Table createTable(String name, String columns);

    boolean tableExists(String name);

    default void update(String update) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(update);
        ps.executeUpdate();
    }

    default ResultSet query(String query) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(query);
        return ps.executeQuery();
    }


    interface Table {
        void delete();

        void truncate(); // Clears

        int countRows();

        boolean exists(String column, String values);

        void addToTable(String columns, String values);

        void removeFromTable(String columns, String operand, String checkData);

        void upsert(String selected, Object object, String column, String checkData);

        default void set(String selected, Object object, String column, String operand, String checkData) {
            set(selected, object, new String[]{column + operand + checkData});
        }

        void set(String selected, Object object, String[] where);

        default Object get(String selected, String column, String operand, String checkData) {
            return get(selected, new String[]{column + operand + checkData});
        }

        Object get(String selected, String[] where);

        default List<Object> getList(String selected, String column, String operand, String checkData){
            return getList(selected, new String[]{column + operand + checkData});
        }

        List<Object> getList(String selected, String[] where);

        static String getCondition(String[] where) {
            StringBuilder condition = new StringBuilder();
            for (int i = 0; i < where.length - 1; i++) {
                String s = where[i];
                condition.append(s).append(" AND ");
            }
            condition.append(where[where.length - 1]);
            return condition.toString();
        }
    }
}
