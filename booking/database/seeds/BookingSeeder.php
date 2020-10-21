<?php

use App\Booking;
use Illuminate\Database\Seeder;
use Faker\Factory as Faker;

class BookingSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //menghapus record sebelumnya
        Booking::truncate();

        $faker = Faker::create();
        
    }
}
