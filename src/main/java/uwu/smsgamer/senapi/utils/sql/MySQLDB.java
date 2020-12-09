package uwu.smsgamer.senapi.utils.sql;

import uwu.smsgamer.senapi.utils.Pair;

import java.sql.*;
import java.util.*;

/**
 * An Implementation of {@link SenDB} for MySQL.
 */
public class MySQLDB implements SenDB {
    public String host;
    public int port;
    public String database;
    public String username;
    public String password;
    public String tablePrefix;
    public Connection con;

    public MySQLDB(String host, int port, String database, String username, String password, String tablePrefix) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.tablePrefix = tablePrefix;
    }

    @Override
    public void initialize(Pair<String, String>... rows) {
        for (Pair<String, String> row : rows) {
            createTable(row.a, row.b);
        }
    }

    @Override
    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return con;
    }

    @Override
    public boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Table getTable(String table) {
        return new MySQLTable(this, table);
    }

    @Override
    public Table createTable(String name, String columns) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
              "CREATE TABLE IF NOT EXISTS " + tablePrefix + name + " (" + columns + ")");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MySQLTable(this, name);
    }

    @Override
    public boolean tableExists(String name) {
        try {
            Connection connection = getConnection();
            if (connection == null) return false;

            DatabaseMetaData metadata = connection.getMetaData();
            if (metadata == null) return false;

            ResultSet rs = metadata.getTables(null, null, name, null);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class MySQLTable implements SenDB.Table {
        private final MySQLDB db;
        private final String name;

        private MySQLTable(MySQLDB db, String name) {
            this.db = db;
            this.name = name;
        }

        @Override
        public void delete() {
            try {
                db.update("DROP TABLE " + name + ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void truncate() {
            try {
                db.update("TRUNCATE TABLE " + name + ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int countRows() {
            try {
                ResultSet rs = db.query("SELECT * FROM " + name);
                int i = 0;
                while (rs.next()) i++;
                return i;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        public boolean exists(String column, String values) {
            try {
                ResultSet rs = db.query("SELECT * FROM " + name + " WHERE " + column + "=" + values + ";");
                return rs.next();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;
        }

        @Override
        public void addToTable(String columns, String values) {
            try {
                db.update("INSERT INTO " + name + " (" + columns + ") VALUES (" + values + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void removeFromTable(String columns, String operand, String checkData) {
            try {
                db.update("DELETE FROM " + name + " WHERE " + columns + operand + checkData + ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void upsert(String selected, Object object, String column, String checkData) {
            // TODO: 2020-12-09
        }

        @Override
        public void set(String selected, Object object, String[] where) {
            if (where.length == 0) return;
            if (object != null) object = "'" + object + "'";
            String condition = Table.getCondition(where);
            try {
                db.update("UPDATE " + name + " SET " + selected + "=" + object + " WHERE " + condition + ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object get(String selected, String[] where) {
            if (where.length == 0) return null;
            String condition = Table.getCondition(where);
            try {
                ResultSet rs = db.query("SELECT * FROM " + name + " WHERE " + condition + ";");
                if (rs.next()) {
                    return rs.getObject(selected);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<Object> getList(String selected, String[] where) {
            if (where.length == 0) return null;
            String condition = Table.getCondition(where);
            try {
                ResultSet rs = db.query("SELECT * FROM " + name + " WHERE " + condition + ";");
                List<Object> list = new ArrayList<>();
                if (rs.next()) list.add(rs.getObject(selected));
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
