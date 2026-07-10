/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import DBKoneksi.DBKoneksi;
import ClassModel.Kategori;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk mengelola data kategori di database.
 * Berisi method-method CRUD (Create, Read, Update, Delete).
 * 
 * @author Admin
 */
public class KategoriDAO {
    private Connection conn;

    public KategoriDAO() {
        this.conn = DBKoneksi.getConnection();
    }

    /**
     * Ambil semua data kategori dari database.
     * Data diurutkan dari kode terbaru (DESC).
     * 
     * @return list berisi semua objek Kategori
     */
    public List<Kategori> getAllKategori() {
        List<Kategori> list = new ArrayList<>();
        String query = "SELECT * FROM kategori ORDER BY kode DESC";
        
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Kategori k = new Kategori();
                k.setKode(rs.getInt("kode"));
                k.setKategori(rs.getString("kategori"));
                k.setBrand(rs.getString("brand"));
                list.add(k);
            }
        } catch (SQLException e) {
            System.err.println("Error ambil semua kategori: " + e.getMessage());
        }
        return list;
    }

    /**
     * Cek apakah kombinasi kategori dan brand sudah ada di database.
     * Biar gak ada data ganda.
     * 
     * @param kategori nama kategori
     * @param brand merek/brand
     * @return true kalau udah ada, false kalau belum
     */
    public boolean isDuplikat(String kategori, String brand) {
        String query = "SELECT COUNT(*) FROM kategori WHERE kategori = ? AND brand = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kategori);
            ps.setString(2, brand);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error cek duplikat: " + e.getMessage());
        }
        return false;
    }

    /**
     * Tambah data kategori baru ke database.
     * 
     * @param k objek Kategori yang mau ditambah
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean insert(Kategori k) {
        String query = "INSERT INTO kategori (kategori, brand) VALUES (?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, k.getKategori());
            ps.setString(2, k.getBrand());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error tambah kategori: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ubah data kategori yang sudah ada.
     * 
     * @param k objek Kategori dengan data baru
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean update(Kategori k) {
        String query = "UPDATE kategori SET kategori = ?, brand = ? WHERE kode = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, k.getKategori());
            ps.setString(2, k.getBrand());
            ps.setInt(3, k.getKode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update kategori: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cek apakah kategori ini masih dipakai oleh produk.
     * Biar gak bisa dihapus kalau masih ada produk yang pake.
     * 
     * @param kodeKategori ID kategori yang mau dicek
     * @return true kalau masih dipakai, false kalau aman dihapus
     */
    public boolean isDipakaiProduk(int kodeKategori) {
        String query = "SELECT COUNT(*) FROM produk WHERE kode_produk = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kodeKategori);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error cek relasi produk: " + e.getMessage());
        }
        return false;
    }

    /**
     * Hapus kategori dari database berdasarkan kode.
     * 
     * @param kode ID kategori yang mau dihapus
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean delete(int kode) {
        String query = "DELETE FROM kategori WHERE kode = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kode);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error hapus kategori: " + e.getMessage());
            return false;
        }
    }
}