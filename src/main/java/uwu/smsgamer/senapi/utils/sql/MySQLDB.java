package uwu.smsgamer.senapi.utils.sql;

import uwu.smsgamer.senapi.utils.Pair;

import java.sql.*;

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
        return null;
    }

    @Override
    public Table createTable(String name, String contents) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
              "CREATE TABLE IF NOT EXISTS " + tablePrefix + name + " (" + contents + ")");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}