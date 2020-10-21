<?php

namespace App\Http\Controllers;

use App\Booking;
use Illuminate\Http\Request;

class ApiController extends Controller
{

    public function index()
    {
      $response = [];
      $response['success'] = 1;
      $response['booking'] = Booking::orderBy('tanggal_booking','asc')->get();
      return response()->json($response);
    }

    public function show(Request $request)
    {
      $response = [];
      $response['success'] = 1;
      $id = $request->input('id');
      $response['booking'] = Booking::find($id);
      return response()->json($response);
    }

    public function store(Request $request)
    {
      $response = [];
    //   $nama_konsumen = $request->input('nama_konsumen');
    //   $jenis_kelamin = $request->input('jenis_kelamin');
    //   $nama_motor      = $request->input('nama_motor');
    //   $keluhan         = $request->input('keluhan');
    //   $no_polisi       = $request->input('no_polisi');
    //   $no_hp          = $request->input('no_hp');
    //   $alamat        = $request->input('alamat');
    //   $email         = $request->input('email');
    //   $tanggal_booking = $request->input('tanggal_booking');
    //   $jam_booking     = $request->input('jam_booking');
      

    //   $booking = new Booking;
    //   $booking->nama_konsumen = $nama_konsumen;
    //   $booking->jenis_kelamin = $jenis_kelamin;
    //   $booking->nama_motor = $nama_motor;
    //   $booking->keluhan = $keluhan;
    //   $booking->no_polisi = $no_polisi;
    //   $booking->no_hp = $no_hp;
    //   $booking->alamat = $alamat;
    //   $booking->email = $email;
    //   $booking->tanggal_booking = $tanggal_booking;
    //   $booking->jam_booking = $jam_booking;
    //   $booking->save();

    //   $response['success'] = 1;
    //   $response['message'] = 'Booking successfully created.';

    //   return response()->json($response);
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

    public function edit(Request $request)
    {
      $response = [];
      $response['success'] = 1;

      $id = $request->input('id');
      $booking = Booking::find($id);
      $response['booking'] = $booking;
      return response()->json($response);
    }

    public function update(Request $request)
    {
      $response = [];
      $id = $request->input('id');
      $nama_konsumen = $request->input('nama_konsumen');
      $jenis_kelamin = $request->input('jenis_kelamin');
      $nama_motor      = $request->input('nama_motor');
      $keluhan         = $request->input('keluhan');
      $no_polisi       = $request->input('no_polisi');
      $no_hp          = $request->input('no_hp');
      $alamat        = $request->input('alamat');
      $email         = $request->input('email');
      $tanggal_booking = $request->input('tanggal_booking');
      $jam_booking     = $request->input('jam_booking');

      $booking = Booking::find($id);
      $booking->nama_konsumen = $nama_konsumen;
      $booking->jenis_kelamin = $jenis_kelamin;
      $booking->nama_motor = $nama_motor;
      $booking->keluhan = $keluhan;
      $booking->no_polisi = $no_polisi;
      $booking->no_hp = $no_hp;
      $booking->alamat = $alamat;
      $booking->email = $email;
      $booking->tanggal_booking = $tanggal_booking;
      $booking->jam_booking = $jam_booking;
      $booking->save();

      $response['success'] = 1;
      $response['message'] = 'Booking successfully updated.';
      return response()->json($response);
    }

    public function destroy(Request $request)
    {
      $response = [];
      $response['success'] = 1;
      $response['message'] = 'Booking successfully deleted.';

      $id = $request->input('id');
      $booking = Booking::find($id);
      $booking->delete();

      return response()->json($response);
    }
}
