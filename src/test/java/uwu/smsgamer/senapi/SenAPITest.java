package uwu.smsgamer.senapi;

import org.junit.jupiter.api.Test;
import uwu.smsgamer.senapi.utils.Evaluator;
import uwu.smsgamer.senapi.utils.sql.*;

import javax.script.*;
import java.util.List;

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
        if (db.tableExists("TestTable")) db.getTable("TestTable").truncate();
        SenDB.Table table = db.createTable("TestTable", "string VARCHAR(100), owoteger INT(100)");
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

    @Test
    public void testSQLiteDatabase() {
        SenDB db = new SQLiteDB("run/test.db");
        db.connect();
        if (db.tableExists("TestTable")) db.getTable("TestTable").truncate();
        SenDB.Table table = db.createTable("TestTable", "string VARCHAR(100), owoteger INT(100)");
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

    @Test
    public void evalTest() throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = mgr.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println(factory.getEngineName());
            System.out.println(factory.getEngineVersion());
            System.out.println(factory.getLanguageName());
            System.out.println(factory.getLanguageVersion());
            System.out.println(factory.getExtensions());
            System.out.println(factory.getMimeTypes());
            System.out.println(factory.getNames());
        }
        System.out.println(Evaluator.evaluate("12 < 13 < 14"));

        System.out.println(Evaluator.evaluateWithParam("12 < x < 14", "x = 13"));
    }
}
