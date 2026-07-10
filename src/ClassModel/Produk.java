/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 * Class model untuk data produk.
 * Merepresentasikan tabel produk di database.
 * Digunakan untuk menyimpan data produk seperti nama, harga, stok,
 * dan juga hasil JOIN dengan tabel kategori.
 * 
 * @author Admin
 */

public class Produk {
    // ID unik produk
    private int kode;
    
    // ID kategori (foreign key) - buat nyambungin ke tabel kategori
    private int kodeProduk;
    
    // Nama produk, contoh: "TV 32 Inch" atau "Sepatu Lari"
    private String nama;
    
    // Harga jual (dalam Rupiah)
    private int harga;
    
    // Jumlah stok barang di gudang
    private int stock;
    
    /**
     * Atribut tambahan (gak ada di database).
     * Ini hasil JOIN dari tabel produk dan kategori.
     * Biar pas ditampilkan di JTable langsung keliatan nama kategori dan brand-nya,
     * tanpa perlu query lagi.
     */
    private String namaKategori; // Nama kategori, contoh: "Elektronik"
    private String brand;        // Brand, contoh: "Samsung"
    
    /**
     * Konstruktor kosong.
     * Buat objek produk dulu, nanti diisi pakai setter.
     */
    public Produk() {
    }

    // === Getter dan Setter ===
    // Getter: buat ngambil nilai
    // Setter: buat ngubah/ngisi nilai

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public int getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(int kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter & Setter untuk atribut tambahan (hasil JOIN)
    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}