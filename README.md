# 🏪 Aplikasi Manajemen Toko - Sistem Kasir Berbasis Desktop

[![Java](https://img.shields.io/badge/Java-17+-orange?logo=java)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql)](https://www.mysql.com/)
[![NetBeans](https://img.shields.io/badge/IDE-NetBeans-green)](https://netbeans.apache.org/)
[![Swing](https://img.shields.io/badge/GUI-Swing-lightgrey)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![DAO](https://img.shields.io/badge/Pattern-DAO-red)](https://en.wikipedia.org/wiki/Data_access_object)
[![MVC](https://img.shields.io/badge/Architecture-MVC-purple)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
[![Version](https://img.shields.io/badge/Version-1.0.0-brightgreen)]()

**Aplikasi Manajemen Toko** adalah sistem kasir berbasis desktop yang dibangun menggunakan **Java Swing** dan **MySQL**. Aplikasi ini dirancang untuk mengelola data produk dan kategori dengan fitur CRUD lengkap, validasi data yang ketat, integrasi antar modul, serta menerapkan konsep Object-Oriented Programming (OOP), pola MVC (Model-View-Controller), dan DAO (Data Access Object) yang baik.

---

## 📋 Daftar Isi

- [Fitur Utama](#-fitur-utama)
- [Teknologi yang Digunakan](#-teknologi-yang-digunakan)
- [Struktur Proyek](#-struktur-proyek)
- [Cara Menjalankan Aplikasi](#-cara-menjalankan-aplikasi)
- [Tim Pengembang](#-tim-pengembang)

---

## ✨ Fitur Utama

### 🏷️ Manajemen Produk
- **Tambah Produk** - Input produk baru melalui dialog terpisah dengan validasi lengkap
- **Edit Produk** - Ubah data produk yang sudah ada melalui dialog update internal
- **Hapus Produk** - Hapus produk dengan konfirmasi terlebih dahulu
- **Lihat Semua Produk** - Tampilan tabel dengan informasi lengkap (kode, nama, harga, stock, kategori, brand) hasil JOIN dengan tabel kategori

### 📂 Manajemen Kategori
- **Tambah Kategori** - Input kategori dan brand baru dengan cegah duplikat
- **Edit Kategori** - Ubah data kategori dengan validasi duplikat cerdas
- **Hapus Kategori** - Hapus dengan validasi relasi (tidak bisa hapus jika masih dipakai produk)
- **Lihat Semua Kategori** - Tampilan tabel kategori dengan kode, nama kategori, dan brand

### ✅ Validasi Data Lengkap
- **Field Kosong** - Semua field wajib diisi sebelum diproses
- **Tipe Data Angka** - Harga dan stok harus berupa angka valid (NumberFormatException handling)
- **Nilai Negatif** - Harga dan stok tidak boleh bernilai negatif
- **Data Duplikat** - Cegah kombinasi kategori + brand yang sama di database
- **Relasi Foreign Key** - Cegah penghapusan kategori yang masih memiliki produk terkait
- **Konfirmasi Hapus** - Konfirmasi sebelum menghapus data untuk mencegah kesalahan
- **Seleksi Data** - Wajib memilih baris sebelum operasi update/delete
- **User Cancel** - Menangani pembatalan input dialog dengan baik

### 🔄 User Experience
- **Auto Refresh** - Data otomatis diperbarui setelah operasi CRUD
- **Form Terintegrasi** - Produk dan kategori saling terhubung (tombol Kategori di Form Produk)
- **Dialog Modal** - Input/Update menggunakan dialog terpisah yang tidak mengganggu form utama
- **Pesan Error Informatif** - Feedback jelas untuk setiap jenis kesalahan
- **ComboBox Dinamis** - Data kategori di-load dari database ke combobox secara otomatis
- **Format Tampilan** - Produk menampilkan kategori dan brand hasil JOIN, combobox kategori menampilkan "Kategori - Brand"

---

## 🛠️ Teknologi yang Digunakan

| Teknologi | Deskripsi |
|-----------|-----------|
| **Java 17+** | Bahasa pemrograman utama berbasis OOP |
| **Java Swing** | Framework untuk GUI desktop dengan komponen lengkap |
| **MySQL 8.0** | Database management system relasional |
| **JDBC** | Java Database Connectivity untuk koneksi ke MySQL |
| **DAO Pattern** | Data Access Object untuk abstraksi dan kapsulasi akses database |
| **MVC Pattern** | Model-View-Controller untuk arsitektur aplikasi yang terstruktur |
| **Inheritance** | Pewarisan dari JFrame dan Dialog untuk reusable code |
| **Encapsulation** | Getter/Setter untuk akses data terkontrol |

### 📦 Library
- **MySQL Connector/J 8.0.33** - Driver untuk koneksi Java-MySQL

---

## 📁 Struktur Proyek

```
UASPBOSemester2/
│
├── src/                                 # Source code utama
│   ├── ClassModel/                      # Model (MVC) - Representasi data
│   │   ├── Kategori.java                # Model untuk tabel kategori dengan atribut: kode, kategori, brand
│   │   └── Produk.java                  # Model untuk tabel produk dengan atribut tambahan hasil JOIN (namaKategori, brand)
│   │
│   ├── Dao/                             # Data Access Object (Controller) - Akses database
│   │   ├── KategoriDAO.java             # CRUD + validasi kategori (getAll, insert, update, delete, isDuplikat, isDipakaiProduk)
│   │   └── ProdukDAO.java               # CRUD produk dengan JOIN tabel kategori (getAll, getById, insert, update, delete)
│   │
│   ├── DBKoneksi/                       # Koneksi database (Singleton Pattern)
│   │   └── DBKoneksi.java               # Koneksi ke MySQL dengan URL, USER, PASSWORD
│   │
│   └── GUI/                             # View (Antarmuka pengguna) - Komponen Swing
│       ├── FormProduk.java              # Form utama manajemen produk (JFrame dengan JTable, tombol aksi)
│       ├── FormKategori.java            # Form manajemen kategori (JFrame terpisah)
│       └── DialogInputProduk.java       # Dialog input produk baru (Dialog modal)
│
├── database/
│   └── db_toko.sql                      # Dump database (struktur tabel + constraints + data awal)
│
├── lib/
│   └── mysql-connector-java-8.0.33.jar  # Library JDBC MySQL 
│
└── docs/
     └── LaporanUASPBO.pdf               # Laporan lengkap proyek 

```

---

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat
- **Java JDK 17** atau lebih baru ([Download](https://www.oracle.com/java/technologies/javase-downloads.html))
- **MySQL Server 8.0** atau lebih baru ([Download](https://dev.mysql.com/downloads/mysql/))
- **IDE** (NetBeans/Eclipse/IntelliJ) atau command line
- **MySQL Connector/J** library (sudah disertakan di folder `lib/`)

---

### 📥 Persiapan Database

#### Opsi 1: Menggunakan phpMyAdmin (Rekomendasi)
1. Buka **phpMyAdmin** (biasanya di `http://localhost/phpmyadmin`)
2. Klik **New** di sidebar kiri
3. Isi nama database: **db_toko**
4. Pilih **utf8mb4_general_ci** sebagai collation
5. Klik **Create**
6. Pilih database **db_toko**
7. Klik tab **Import**
8. Pilih file `database/db_toko.sql`
9. Klik **Go** / **Import**

#### Opsi 2: Menggunakan Command Line (MySQL CLI)
```bash
# Buka terminal dan login ke MySQL
mysql -u root -p

# Jika belum ada password, gunakan:
mysql -u root

# Buat database
CREATE DATABASE db_toko;
USE db_toko;

# Import file SQL (sesuaikan path)
SOURCE /path/to/project-pbo-kasir/database/db_toko.sql;

# Exit
EXIT;
```

#### Opsi 3: Menggunakan MySQL Workbench
1. Buka **MySQL Workbench**
2. Klik **Local instance** untuk koneksi
3. Jalankan script:
```sql
CREATE DATABASE IF NOT EXISTS db_toko;
USE db_toko;
SOURCE /path/to/db_toko.sql;
```

---

### 🔧 Menjalankan di IDE (NetBeans/Eclipse/IntelliJ)

#### **NetBeans:**
1. Buka NetBeans IDE
2. Pilih **File → Open Project**
3. Arahkan ke folder proyek dan pilih
4. Klik kanan pada **Libraries** di proyek → **Add JAR/Folder**
5. Pilih file `lib/mysql-connector-java-8.0.33.jar`
6. Klik **OK**
7. Buka file `src/GUI/FormProduk.java`
8. Klik kanan → **Run File** (Shift+F6)

#### **Eclipse:**
1. Buka Eclipse IDE
2. Pilih **File → Import → Existing Projects into Workspace**
3. Pilih folder proyek
4. Klik **Finish**
5. Klik kanan pada proyek → **Build Path → Configure Build Path**
6. Pilih **Libraries → Add External JARs**
7. Pilih `lib/mysql-connector-java-8.0.33.jar`
8. Klik **Apply and Close**
9. Klik kanan `FormProduk.java` → **Run As → Java Application**

#### **IntelliJ IDEA:**
1. Buka IntelliJ IDEA
2. Pilih **Open** dan pilih folder proyek
3. Klik kanan pada proyek → **Add Framework Support** (jika perlu)
4. File → **Project Structure** → **Modules** → **Dependencies**
5. Klik **+** → **JARs or Directories**
6. Pilih `lib/mysql-connector-java-8.0.33.jar`
7. Klik **OK**
8. Buka `FormProduk.java` dan klik tombol **Run** (▶)

---

### 💻 Menjalankan via Command Line

```bash
# 1. Compile semua file dengan classpath
javac -cp "lib/mysql-connector-java-8.0.33.jar:src" src/GUI/FormProduk.java

# 2. Jalankan aplikasi
java -cp "lib/mysql-connector-java-8.0.33.jar:src" GUI.FormProduk

# Untuk Windows (gunakan ; sebagai pemisah)
javac -cp "lib/mysql-connector-java-8.0.33.jar;src" src/GUI/FormProduk.java
java -cp "lib/mysql-connector-java-8.0.33.jar;src" GUI.FormProduk
```

---

### ⚙️ Konfigurasi Database (Jika Perlu)

Jika Anda menggunakan username/password MySQL yang berbeda, edit file:

**`src/DBKoneksi/DBKoneksi.java`**

```java
public class DBKoneksi {
    // Sesuaikan dengan setting MySQL Anda
    private static final String URL = "jdbc:mysql://localhost:3306/db_toko";
    private static final String USER = "root";        // Ganti jika username berbeda
    private static final String PASSWORD = "";        // Ganti jika ada password
    
    // ... kode lainnya
}
```

---

## 👥 Tim Pengembang

| No | Nama | NIM | Peran |
|----|------|-----|-------|
| 1 | Rendra Adnan Farid | 25104410036 | Project Lead & Tester & Documentation & Backend Developer |
| 2 | M. Rizki Desca F. | 25104410041 | Database Developer & DAO |
| 3 | Ema Netasari | 25104410052 | Frontend Developer (GUI) |
| 4 | Nahrul Mubarok | 25104410062 | Backend Developer |
| 5 | Gladis Ayu Diapitaloka | 25104410073 | UI/UX Design & Documentation |
| 6 | Adelia Wahyu Bima Sakti | 25104410094 | Frontend Developer (GUI) |

**Dosen Pengampu:** Saiful Nur Budiman, S.Kom., M.Kom.

**Mata Kuliah:** Pemrograman Berorientasi Objek (PBO)

**Program Studi:** Teknik Informatika (S1)

**Fakultas:** Teknologi dan Informasi

**Universitas:** Universitas Islam Balitar

**Tahun Akademik:** 2025/2026

---

## 📚 Referensi dan Sumber Belajar

### Dokumentasi Resmi
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/en/)
- [NetBeans IDE Documentation](https://netbeans.apache.org/tutorial/)

### Buku dan Materi Kuliah
- Materi Pemrograman Berorientasi Objek - Universitas Islam Balitar
- Java: The Complete Reference - Herbert Schildt
- Head First Java - Kathy Sierra & Bert Bates

### Video Tutorial
- [Java Swing Tutorial (YouTube)](https://www.youtube.com/results?search_query=java+swing+tutorial)
- [NetBeans GUI Builder Tutorial](https://www.youtube.com/results?search_query=netbeans+gui+builder)
- [JDBC MySQL Tutorial](https://www.youtube.com/results?search_query=jdbc+mysql+tutorial)

---

## 🙏 Ucapan Terima Kasih

Tim pengembang mengucapkan terima kasih yang sebesar-besarnya kepada:

- **Allah SWT** yang telah memberikan kemudahan, kelancaran, dan kesehatan dalam menyelesaikan proyek ini.
- **Bapak Saiful Nur Budiman, S.Kom., M.Kom.** selaku dosen pengampu mata kuliah Pemrograman Berorientasi Objek yang telah memberikan bimbingan dan arahan selama perkuliahan
- **Universitas Islam Balitar** yang telah mendukung dan menyediakan fasilitas perkuliahan
- **Semua anggota kelompok** atas kerja sama, dedikasi, dan semangat dalam menyelesaikan proyek ini
- **Keluarga dan teman-teman** yang telah memberikan dukungan moril

---

## 🏆 Penghargaan

Aplikasi ini dibuat sebagai **Tugas Akhir Semester** mata kuliah **Pemrograman Berorientasi Objek** dengan tujuan mengimplementasikan konsep-konsep OOP dalam aplikasi nyata yang fungsional dan bermanfaat.

**Nilai yang Diterapkan:**
- ✅ OOP Principles (Inheritance, Encapsulation, Polymorphism, Abstraction)
- ✅ MVC Architecture
- ✅ DAO Pattern
- ✅ Database Integration (JDBC)
- ✅ GUI Development (Swing)
- ✅ Exception Handling
- ✅ Data Validation
- ✅ Documentation

---

<div align="center">
  <br>
  <sub>
    <strong>🚀 Dibangun dengan ❤️ oleh Kelompok 2 PBO - Universitas Islam Balitar</strong><br>
    <em>© 2026 - All Rights Reserved</em>
  </sub>
  <br><br>
  <sub>
    ⭐ Jika Anda menyukai proyek ini, berikan bintang di GitHub! ⭐
  </sub>
  <br><br>
</div>
```
