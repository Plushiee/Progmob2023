package com.nikolaus.progmob2023.network

import com.nikolaus.progmob2023.adapter.PetaniAPIAdapter
import com.nikolaus.progmob2023.model.ResponsePetani
import com.nikolaus.progmob2023.model.ResponsePetaniItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

class NetworkConfigPetani {
    // set interceptor
    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        return okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://10.0.2.2/progmob2023/public/")
            .client(getInterceptor()).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getService() = getRetrofit().create(UsersPetani::class.java)
}

interface UsersPetani {
    @GET("petani/")
    fun getPetani(): Call <ResponsePetani>

    @FormUrlEncoded
    @POST("petani/")
    fun postPetani(
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("kabupaten") kabupaten: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kelurahan") kelurahan: String,
        @Field("provinsi") provinsi: String,
        @Field("nama_istri") namaIstri: String,
        @Field("jumlah_lahan") jumlahLahan: Int,
        @Field("foto") foto: String
    ): Call <ResponsePetaniItem>? = null

    @DELETE("petani/{id}")
    fun deletePetani(@Path("id") id: String? = null): Call <Void>

    @FormUrlEncoded
    @PUT("petani/{id}")
    fun updatePetani(
        @Path("id") id: String? = null,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("kabupaten") kabupaten: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kelurahan") kelurahan: String,
        @Field("provinsi") provinsi: String,
        @Field("nama_istri") namaIstri: String,
        @Field("jumlah_lahan") jumlahLahan: Int,
        @Field("foto") foto: String
    ): Call <Void>
}

