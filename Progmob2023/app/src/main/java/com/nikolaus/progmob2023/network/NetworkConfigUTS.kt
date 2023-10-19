package com.nikolaus.progmob2023.network

import com.nikolaus.progmob2023.model.ResponseUTS
import com.nikolaus.progmob2023.model.ResponseUTSItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

class NetworkConfigUTS {
    // set interceptor
    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        return okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://kpsi.fti.ukdw.ac.id/")
            .client(getInterceptor()).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getService() = getRetrofit().create(UsersUTS::class.java)
}

interface UsersUTS {
    @GET("api/progmob/mhs/72210456") //Isi
    fun getUTS(): Call <List<ResponseUTSItem>>

    @FormUrlEncoded
    @POST("api/progmob/mhs/create") //isi
    fun postUTS(
        @Field("nama") nama: String,
        @Field("nim") nim: String,
        @Field("alamat") alamat: String,
        @Field("email") email: String,
        @Field("foto") foto: String,
        @Field("nim_progmob") nim_progmob: String,
    ): Call <Void>

    @DELETE("api/progmob/mhs/72210456/{id}") //isi
    fun deleteUTS(@Path("id") id: Int? = null): Call <Void>

    @FormUrlEncoded
    @PUT("api/progmob/mhs/72210456/{id}") //isi
    fun updateUTS( //ganti
        @Field("nama") nama: String,
        @Field("nim") nim: String,
        @Field("alamat") alamat: String,
        @Field("email") kabupaten: String,
        @Field("foto") kecamatan: String
    ): Call <Void>
}

