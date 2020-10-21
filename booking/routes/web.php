<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

// Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

// Route::get('/booking', 'ApiController@index');
// Route::post('/booking/store', 'ApiController@store');
// Route::get('booking/{id}', 'ApiController@show');
// Route::put('/booking/update/{id}', 'ApiController@update');
// Route::get('/booking/delete/{id}', 'ApiController@delete');
// Route::get('/booking/search/{nama_konsumen}','ApiController@search');

