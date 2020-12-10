package uwu.smsgamer.senapi;

import org.junit.jupiter.api.Test;
import uwu.smsgamer.senapi.utils.sql.*;

public class SenAPITest {
    @Test
    public void testMySQLDatabase() {
        /*
         * Idc if u see this information. It's on localhost.
         * What u gonna do even if u could connect, mess around with a TEST DATABASE!? lol
         *
         * MySQL DB Name: sen_api_test
         * MySQL Username: sen_api_user
         * MySQL User passwd: SenAPIMySQLPassword0
         */
        SenDB db = new MySQLDB("localhost", 3306, "sen_api_test", "sen_api_user", "SenAPIMySQLPassword0", "test_");
        db.connect();
        SenDB.Table table = db.createTable("TestTable", "string VARCHAR(100), owoteger INT(100)");
        db.getTable("TestTable").truncate();
        table.add("string, owoteger", "'OwO UwU', 13");
        table.add("string, owoteger", "'Haha', 15");
        table.add("string, owoteger", "'XD cool', 12");
        System.out.println(table.get("string", "owoteger", "=", "15"));
        table.set("string", "PP", "owoteger", "=", "15");
        System.out.println(table.get("string", "owoteger", "=", "15"));
        table.add("string, owoteger", "'nIIce', 14");
        table.add("string, owoteger", "'owoII', 17");
        table.add("string, owoteger", "'IIgjwd', 16");
        System.out.println(table.getList("owoteger", "string", " LIKE ", "'%II%'"));
        System.out.println(table.getAll("owoteger"));
        db.disconnect();
    }
}
