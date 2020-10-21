package com.melan.aplikasibooking;

public class Booking {
    private String id;
    private String nama_konsumen;
    private String jenis_kelamin;
    private String nama_motor;
    private String keluhan;
    private String no_polisi;
    private String no_hp;
    private String email;
    private String alamat;
    private String tanggal_booking;
    private String jam_booking;

    public Booking(){}

    public Booking(String id, String nama_konsumen, String jenis_kelamin, String nama_motor,
                   String keluhan, String no_polisi, String no_hp, String email, String alamat,
                   String tanggal_booking, String jam_booking) {
        this.id = id;
        this.nama_konsumen = nama_konsumen;
        this.jenis_kelamin = jenis_kelamin;
        this.nama_motor = nama_motor;
        this.keluhan = keluhan;
        this.no_polisi = no_polisi;
        this.no_hp = no_hp;
        this.email = email;
        this.alamat = alamat;
        this.tanggal_booking = tanggal_booking;
        this.jam_booking = jam_booking;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_konsumen() {
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNama_motor() {
        return nama_motor;
    }

    public void setNama_motor(String nama_motor) {
        this.nama_motor = nama_motor;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public void setNo_polisi(String no_polisi) {
        this.no_polisi = no_polisi;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggal_booking() {
        return tanggal_booking;
    }

    public void setTanggal_booking(String tanggal_booking) {
        this.tanggal_booking = tanggal_booking;
    }

    public String getJam_booking() {
        return jam_booking;
    }

    public void setJam_booking(String jam_booking) {
        this.jam_booking = jam_booking;
    }
}
