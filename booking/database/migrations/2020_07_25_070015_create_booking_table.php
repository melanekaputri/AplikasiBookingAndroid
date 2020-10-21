<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBookingTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('booking', function (Blueprint $table) {
            $table->increments('id');
            $table->string('nama_konsumen');
            $table->string('jenis_kelamin');
            $table->string('nama_motor');
            $table->string('keluhan');
            $table->string('no_polisi');
            $table->string('no_hp');
            $table->string('email')->unique();
            $table->string('alamat');
            $table->string('tanggal_booking');
            $table->string('jam_booking');
            $table->timestamps();
        
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('booking');
    }
}
