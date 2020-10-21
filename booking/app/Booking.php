<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Booking extends Model
{
    protected $table = "booking";
    protected $fillable = [
        'nama_konsumen',
        'jenis_kelamin',
        'nama_motor',
        'keluhan',
        'no_polisi',
        'no_hp',
        'email',
        'alamat',
        'tanggal_booking',
        'jam_booking',
    ];
}
