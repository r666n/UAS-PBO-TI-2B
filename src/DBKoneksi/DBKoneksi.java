/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DBKoneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas untuk mengatur koneksi ke database MySQL.
 * 
 * @author Admin
 */
public class DBKoneksi {
    // Setting koneksi database
    private static final String URL = "jdbc:mysql://localhost:3306/db_toko";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;

    /**
     * Method untuk mendapatkan koneksi database.
     * Koneksi cuma dibuat sekali (pattern Singleton),
     * kalau udah ada bakal dipake lagi.
     * 
     * @return objek Connection buat konek ke database
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Bikin koneksi ke database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi ke database db_toko BERHASIL!");
                
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL tidak ketemu: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Koneksi database GAGAL: " + e.getMessage());
            }
        }
        return connection;
    }
}