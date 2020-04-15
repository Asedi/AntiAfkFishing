package pl.chilleraiutopia.AntiAfkFishing;

import org.bukkit.Bukkit;

import java.io.File;
import java.sql.*;

public class DatabaseHandler {
    /*public void insert(String playername,int x, int z){
        String query="INSERT INTO "
    }*/
    public static File pFolder=new File("plugins" + File.separator + "AntiAfkFishing");
    public static void createNewDatabase() {
        //Create plugin folder
        if (!pFolder.exists()) {
            pFolder.mkdir();
        }
        String url = "jdbc:sqlite:" + pFolder.getAbsolutePath() + "/" + "fishermans.db";
        Bukkit.broadcastMessage(url);
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println("[Error when creating new database]"+e.getMessage());
        }
    }
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + pFolder.getAbsolutePath() + "/" + "fishermans.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + pFolder.getAbsolutePath() + "/" + "fishermans.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS fishermans (id INTEGER PRIMARY KEY AUTOINCREMENT, playername text, x integer, z integer);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("[Error when creating new table]"+e.getMessage());
        }
    }
    public void insert(String playername, Integer x, Integer z) {
        String sql = "INSERT INTO fishermans(id, playername, x, z) VALUES(null,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playername);
            pstmt.setInt(2, x);
            pstmt.setInt(3, z);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

