# Catatan praktikum Abstraction

Nama: Althaf. NIM: 2511533018. Kelas: belum tersedia dalam proyek.
Parameter: CARD_ADMIN_PERCENT = 3; TRANSFER_ADMIN_FEE = 4000; SMS_MAX_LENGTH = 118.

## L1, L3, L4: alasan error compiler

- `new Product(...)` ditolak karena Product abstract. Objek harus dibuat melalui subclass konkret.
- Refund dengan BankTransferPayment ditolak karena parameter memerlukan RefundablePayment. Transfer tidak menjanjikan kemampuan refund.
- Override calculateCost ditolak karena method final. Subclass hanya menentukan tarif melalui ratePerKg, sedangkan rumus pembulatan tetap sama.

## T1: evolusi API

Jika adminFee ditambahkan sebagai abstract pada versi latihan, EWalletPayment, BankTransferPayment, CreditCardPayment, dan anonymous class voucher wajib menyediakan implementasi. Ada empat implementasi terdampak pada susunan ShopApp ini. Jumlah diagnostik compiler bergantung pada versi kode saat eksperimen; bagian ini bukan rekaman eksperimen compiler.

Default adminFee mengembalikan nol sehingga implementasi lama tetap sah. Kartu dan transfer mengganti perilaku itu sesuai parameter NIM. Checkout menggunakan grandTotal agar biaya admin benar-benar ikut dibayar.

EWalletPayment hanya mewarisi policy dari RefundablePayment sehingga tidak punya konflik. CreditCardPayment mewarisi dua default policy dengan signature sama dari dua interface yang tidak saling menurunkan. Java meminta override eksplisit karena tidak ada kebijakan yang lebih spesifik. Implementasi menggabungkan keduanya dengan pemanggilan Interface.super.policy().

Total ekspres: e-wallet Rp268000, transfer Rp272000, kartu Rp276040.

## T2: notifikasi

send dibuat final agar subclass tidak melewati validasi, persiapan pesan, dan pencatatan keberhasilan. Hook resolveRecipient, prepare, dan deliver memberi ruang variasi yang diperlukan setiap kanal.

Interface menentukan kontrak agar kanal independen dan anonymous class juga dapat dipakai. AbstractChannel membagikan field serta algoritma pengiriman kepada kanal yang memerlukannya. NotificationService menyimpan List<NotificationChannel> agar tidak mewajibkan semua kanal mewarisi AbstractChannel; kanal LOG adalah contohnya.

Diagram UML tersedia dalam `notification.puml`. Email memerlukan @; SMS memerlukan awalan 08 dan dipotong menjadi 118 karakter termasuk tiga titik; WhatsApp memerlukan awalan 08 atau +62, panjang minimal 10, dan menambahkan awalan toko. Sent count dan last delivered berubah hanya setelah pengiriman berhasil. Pengiriman saat ini merupakan simulasi konsol.

## T3: batas bukti dan desain perbaikan

File review/BadShop.java yang diberikan hanya berisi class kosong. Karena itu lokasi method dan penyebab internal bug pada sumber asli belum dapat ditinjau. Tabel berikut merupakan analisis risiko berdasarkan gejala yang tertulis dalam modul, bukan temuan baris kode BadShop asli. BadShop.java dipertahankan. BadShopRefactored adalah demo desain perbaikan yang memakai abstraksi proyek ini.

| Gejala/area | Risiko desain yang perlu diperiksa pada sumber asli | Dampak | Desain perbaikan demo |
| --- | --- | --- | --- |
| Ongkir EXPRES 0 | Pemilihan kurir lewat string dengan fallback nol | Salah eja menghilangkan ongkir | ShippingService dan ExpressShipping konkret |
| Alamat ORD-1 berubah | Dua order berbagi alamat mutable | Riwayat tujuan ikut berubah | Address final dengan field private final dan tanpa setter |
| Voucher gagal ship | Produk digital dipaksa menyediakan kemampuan pengiriman | UnsupportedOperationException | Hanya PhysicalProduct implements Shippable |
| Saldo voucher 70000.0 | Nominal memakai floating point | Risiko pembulatan uang | Seluruh uang memakai long |
| Harga flash sale | Konsumen membaca field harga langsung atau mengabaikan override | Harga promo tidak terpakai | subtotal memanggil getFinalPrice secara polimorfis |
| Variasi kurir | Subclass bebas mengubah pembulatan | Aturan ongkir tidak konsisten | calculateCost final, ratePerKg abstract |

Demo menghasilkan total barang 170000, ongkir 36000, alamat Padang dan Bukittinggi terpisah, harga promo 70000, serta saldo voucher 70000 tanpa exception.

## T4: keputusan desain

| Pertanyaan | Pilihan | Alasan dan contoh |
| --- | --- | --- |
| Subclass berbagi field dan constructor | Abstract class | Product memusatkan SKU, nama, harga dan validasinya |
| Satu class perlu beberapa kemampuan | Interface | CreditCardPayment implements RefundablePayment dan InstallmentPayment |
| Rumus bersama tidak boleh berubah | Abstract class dengan final method | ShippingService.calculateCost mengunci rumus dan meminta tarif dari subclass |
| Kontrak perlu method baru | Interface dengan default method | PaymentMethod.adminFee menambahkan perilaku nol tanpa memaksa implementasi lama berubah |

| Kondisi | Pilihan | Alasan dan contoh |
| --- | --- | --- |
| Pembantu tidak membaca objek luar | Static nested | Order.Line hanya memakai produk dan kuantitasnya sendiri |
| Objek membaca field objek luar | Inner | Order.Receipt membaca id, alamat, dan baris milik Order |
| Implementasi sekali pakai | Anonymous | Voucher di ShopApp serta kanal LOG di TugasApp |

## Bukti dan kelengkapan pengumpulan

Hasil aktual pemeriksa dan demo disimpan dalam folder docs/hasil. Catatan ini belum merupakan laporan PDF lengkap pengumpulan: nama resmi/kelas, screenshot IDE dan eksperimen error compiler, serta review berdasarkan BadShop asli masih perlu dilengkapi. Tidak ada screenshot atau temuan kode asli yang direkayasa.
