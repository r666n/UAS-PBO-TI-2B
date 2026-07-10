/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 * Class model untuk data kategori.
 * Merepresentasikan tabel kategori di database.
 * Digunakan untuk menyimpan dan mengelola data kategori seperti
 * elektronik, pakaian, dll beserta brand-nya.
 * 
 * @author Admin
 */

public class Kategori {
    // ID unik kategori
    private int kode;
    
    // Nama kategori, contoh: "Elektronik", "Pakaian"
    private String kategori;
    
    // Merek/brand, contoh: "Samsung", "Adidas"
    private String brand;

    /**
     * Konstruktor kosong.
     * Dipakai kalau mau bikin objek dulu, nanti diisi pakai setter.
     */
    public Kategori() {
    }

    /**
     * Konstruktor lengkap.
     * Langsung bikin objek dengan semua data sekaligus.
     * 
     * @param kode    ID kategori
     * @param kategori Nama kategori
     * @param brand   Merek/brand
     */
    public Kategori(int kode, String kategori, String brand) {
        this.kode = kode;
        this.kategori = kategori;
        this.brand = brand;
    }

    // === Getter dan Setter ===
    // Getter: buat ngambil nilai
    // Setter: buat ngubah nilai

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Biar objek Kategori bisa langsung ditampilkan rapi di JComboBox.
     * Formatnya: "Kode - Nama Kategori (Brand)"
     * Contoh: "1 - Elektronik (Samsung)"
     */
    @Override
    public String toString() {
        return kode + " - " + kategori + " (" + brand + ")";
    }
}