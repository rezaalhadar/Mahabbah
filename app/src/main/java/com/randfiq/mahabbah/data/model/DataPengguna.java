package com.randfiq.mahabbah.data.model;

public class DataPengguna {

    private String timeStamp;
    private String dataID;

    private String nama;
    private String nama_ayah;
    private String marga;
    private String status;

    private String nomor_hp;
    private String email;
    private String wilayah;
    private String alamat;

    private String nik_ktp;
    private String tempat_lahir;
    private String tanggal_lahir;

    private String nama_bank;
    private String nomor_rekening;

    public DataPengguna(String timeStamp, String dataID, String nama, String nama_ayah, String marga, String status, String nomor_hp, String email, String wilayah, String alamat, String nik_ktp, String tempat_lahir, String tanggal_lahir, String nama_bank, String nomor_rekening) {
        this.timeStamp = timeStamp;
        this.dataID = dataID;
        this.nama = nama;
        this.nama_ayah = nama_ayah;
        this.marga = marga;
        this.status = status;
        this.nomor_hp = nomor_hp;
        this.email = email;
        this.wilayah = wilayah;
        this.alamat = alamat;
        this.nik_ktp = nik_ktp;
        this.tempat_lahir = tempat_lahir;
        this.tanggal_lahir = tanggal_lahir;
        this.nama_bank = nama_bank;
        this.nomor_rekening = nomor_rekening;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getDataID() {
        return dataID;
    }

    public String getNama() {
        return nama;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public String getMarga() {
        return marga;
    }

    public String getStatus() {
        return status;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public String getEmail() {
        return email;
    }

    public String getWilayah() {
        return wilayah;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNik_ktp() {
        return nik_ktp;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getNama_bank() {
        return nama_bank;
    }

    public String getNomor_rekening() {
        return nomor_rekening;
    }
}
