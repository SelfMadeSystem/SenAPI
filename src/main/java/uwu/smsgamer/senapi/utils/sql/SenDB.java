package uwu.smsgamer.senapi.utils.sql;

import uwu.smsgamer.senapi.utils.Pair;

import java.sql.*;
import java.util.*;

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

    Table createTable(String name, String contents);

    interface Table {
        void addToRow(String row, Object value);

        List<String> getRows();

        List<Object> getRow(String row);

        default Map<String, List<Object>> getAllRow() {
            return getMultipleRows(getRows().toArray(new String[0]));
        }

        Map<String, List<Object>> getMultipleRows(String... rows);

        default int getRowLength(String row) {
            return getRow(row).size();
        }
    }
}
