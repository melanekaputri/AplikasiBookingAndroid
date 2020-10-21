<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});


//  192.168.43.166 //

Route::get('/booking','ApiController@index');
Route::get('/booking/show','ApiController@show');
Route::post('/booking/store','ApiController@store');
Route::get('/booking/edit','ApiController@edit');
Route::post('/booking/update','ApiController@update');
Route::get('/booking/destroy','ApiController@destroy');