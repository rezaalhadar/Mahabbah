package com.randfiq.mahabbah.utils;

public interface Constant {

    // TODO WebApp Google Apps Script

    // Sample Url Web App
    // https://script.google.com/macros/s/AKfycbzzZxxxxxxxxxxxxxxxxxxxxxxrg4KQjS9d0/exec?action=getItems
    // https://script.google.com/macros/s/AKfycbzqCw8ydb13cVpkVfky5NMlkAJaHASkc7XugvwD2AAZPKLDmhegkZEcgIrzz_NKfmyp/exec

    String WebAppBaseURL = "https://script.google.com/macros/s/AKfycbzqCw8ydb13cVpkVfky5NMlkAJaHASkc7XugvwD2AAZPKLDmhegkZEcgIrzz_NKfmyp/exec";
    String WebApp_ActionGetDatas = "https://script.google.com/macros/s/AKfycbzqCw8ydb13cVpkVfky5NMlkAJaHASkc7XugvwD2AAZPKLDmhegkZEcgIrzz_NKfmyp/exec?action=getDatas";

    // Data Key
    String key_keyRootData = "items";

    // Data Key - Data Diri
    String key_nama = "nama";
    String key_nama_ayah = "nama_ayah";
    String key_marga = "marga";
    String key_status = "status";

    // Data Key - Kontak
    String key_nomor_hp = "nomor_hp";
    String key_email = "email";
    String key_alamat = "alamat";

    // Data Key - Data Identitas
    String key_nik_ktp = "nik_ktp";
    String key_tempat_lahir = "tempat_lahir";
    String key_tanggal_lahir = "tanggal_lahir";

    // Data Key - Data Perbankan
    String key_nama_bank = "nama_bank";
    String key_nomor_rekening = "nomor_rekening";
}
