# Praktikum 7 Exception Handling

Muhammad Althaf Mulya, NIM 2511533018, Kelas Praktikum B.

Buka folder `exception-2511533018` sebagai folder proyek di VS Code. Gunakan JDK 25. Konfigurasi formatter Allman tersedia pada `java-format.xml` dan terhubung melalui `.vscode/settings.json`.

Jalankan perintah berikut dari folder proyek:

```sh
javac -encoding UTF-8 -Xlint:all -d bin @sources.txt
java -cp bin selfcheck.SelfCheck
java -cp bin shop.app.ShopApp
java -cp bin shop.app.ScenarioRunner
java -cp bin tools.NimParams
java -cp bin shop.demo.FlowDemo
java -cp bin shop.demo.CariProdukDemo 2
java -cp bin review.FragileShop
```

`katalog.csv` harus berada sejajar dengan folder `src`. Untuk menguji kegagalan membaca file:

```sh
java -cp bin shop.app.ShopApp katalog-cadangan.csv
```

Demo crash memang berhenti dengan exception dan exit code 1:

```sh
java -cp bin shop.demo.CrashDemo 1
java -cp bin shop.demo.CrashDemo 2
java -cp bin shop.demo.CrashDemo 3
```

Hasil pemeriksaan: 24 dari 24 butir SelfCheck lulus, nilai kode 50 dari 50. Saldo awal Rp250000, stok kopi 4, stok rendang 2, dan stok produk lain 5. Output pengujian tersimpan di folder `hasil`.

`SelfCheck.java` dan `NimParams.java` dipertahankan sesuai file bawaan. Label P10 pada output keduanya berasal dari bahan praktikum. Bagian tantangan opsional dinonaktifkan dalam SelfCheck bawaan, sehingga tidak mencetak baris TANTANGAN.

Berkas pengumpulan tersedia di folder `../pengumpulan`: ZIP berisi folder `src`, LK PDF, dan salinan LK DOCX yang bisa diedit. Saat source diekstrak ke folder baru, gunakan `katalog.csv` dari starter dan buat ulang daftar source sebelum kompilasi:

```sh
find src -name '*.java' > sources.txt
javac -encoding UTF-8 -Xlint:all -d bin @sources.txt
```
