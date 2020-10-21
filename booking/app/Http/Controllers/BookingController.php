<?php

namespace App\Http\Controllers;

use App\Booking;
use Illuminate\Http\Request;

class BookingController extends Controller
{
    public function index()
    {
        $booking = Booking::all();
        return response()->json($booking);
        
    }

    public function search($nama_konsumen)
    {
        $booking = Booking::where('nama_konsumen', 'like', "%{$nama_konsumen}%")->get();
        return response()->json([
          'booking' => $booking
        ]);
    }

    public function store(Request $request)
    {
        $this->validate($request,[
            'nama_konsumen' => 'required',
            'jenis_kelamin' => 'required',
            'alamat'        => 'required',
            'no_hp'         => 'required',
            'email'         => 'required', 
            'tanggal_booking'   => 'required',
            'jam_booking'       => 'required',
            'nama_motor'        => 'required',
            'no_polisi'         => 'required',
            'keluhan'           => 'required'
    	]);
 
        $booking = Booking::create($request->all());
        $response["success"] = 1;
        $response["booking"] = $booking;       

        return response()->json($response);

    }

    public function update(Request $request, $id)
    {
        $booking = Booking::find($id);
        $booking->update($request->all()); 
        $response["success"] = 1;
        $response["booking"] = $booking;
       
        return response()->json($response);
    }

    public function delete($id)
    {
        $booking = Booking::find($id);
        $booking->delete($booking);
        $response["success"] = "Data Berhasil Di Hapus!";
           
        return response()->json($response);
    }

    public function show($id)
    {
      
        $booking = Booking::find($id);
        $response['success'] = 1;
        $response['booking'] = $booking;
        
        return response()->json($response);
    }

}
