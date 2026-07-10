/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import DBKoneksi.DBKoneksi;
import ClassModel.Produk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk mengelola data produk di database.
 * Berisi method-method CRUD (Create, Read, Update, Delete)
 * dan juga JOIN dengan tabel kategori.
 * 
 * @author Admin
 */
public class ProdukDAO {
    private Connection conn;

    public ProdukDAO() {
        this.conn = DBKoneksi.getConnection();
    }

    /**
     * Ambil semua data produk sekaligus nama kategori dan brand-nya.
     * Pake JOIN biar data dari tabel kategori ikut kebawa.
     * Data diurutkan dari kode terbaru (DESC).
     * 
     * @return list berisi semua objek Produk lengkap dengan kategori & brand
     */
    public List<Produk> getAllProduk() {
        List<Produk> list = new ArrayList<>();
        String query = "SELECT p.kode, p.nama, p.harga, p.stock, p.kode_produk, k.kategori, k.brand " +
                       "FROM produk p INNER JOIN kategori k ON p.kode_produk = k.kode " +
                       "ORDER BY p.kode DESC";
        
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Produk p = new Produk();
                p.setKode(rs.getInt("kode"));
                p.setNama(rs.getString("nama"));
                p.setHarga(rs.getInt("harga"));
                p.setStock(rs.getInt("stock"));
                p.setKodeProduk(rs.getInt("kode_produk"));
                p.setNamaKategori(rs.getString("kategori"));
                p.setBrand(rs.getString("brand"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error ambil semua produk: " + e.getMessage());
        }
        return list;
    }

    /**
     * Cari satu produk berdasarkan ID/kode-nya.
     * Hasilnya juga sekalian sama nama kategori dan brand-nya.
     * 
     * @param kode ID produk yang mau dicari
     * @return objek Produk kalau ketemu, null kalau gak ada
     */
    public Produk getProdukById(int kode) {
        String query = "SELECT p.kode, p.nama, p.harga, p.stock, p.kode_produk, k.kategori, k.brand " +
                       "FROM produk p INNER JOIN kategori k ON p.kode_produk = k.kode " +
                       "WHERE p.kode = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kode);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Produk p = new Produk();
                p.setKode(rs.getInt("kode"));
                p.setNama(rs.getString("nama"));
                p.setHarga(rs.getInt("harga"));
                p.setStock(rs.getInt("stock"));
                p.setKodeProduk(rs.getInt("kode_produk"));
                p.setNamaKategori(rs.getString("kategori"));
                p.setBrand(rs.getString("brand"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Error cari produk by ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Tambah data produk baru ke database.
     * 
     * @param p objek Produk yang mau ditambah
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean insert(Produk p) {
        String query = "INSERT INTO produk (kode_produk, nama, harga, stock) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, p.getKodeProduk());
            ps.setString(2, p.getNama());
            ps.setInt(3, p.getHarga());
            ps.setInt(4, p.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error tambah produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ubah data produk yang sudah ada di database.
     * 
     * @param p objek Produk dengan data baru
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean update(Produk p) {
        String query = "UPDATE produk SET kode_produk = ?, nama = ?, harga = ?, stock = ? WHERE kode = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, p.getKodeProduk());
            ps.setString(2, p.getNama());
            ps.setInt(3, p.getHarga());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getKode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Hapus produk dari database berdasarkan kode.
     * 
     * @param kode ID produk yang mau dihapus
     * @return true kalau berhasil, false kalau gagal
     */
    public boolean delete(int kode) {
        String query = "DELETE FROM produk WHERE kode = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kode);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error hapus produk: " + e.getMessage());
            return false;
        }
    }
}