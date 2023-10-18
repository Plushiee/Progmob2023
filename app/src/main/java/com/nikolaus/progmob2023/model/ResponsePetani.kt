package com.nikolaus.progmob2023.model

import com.google.gson.annotations.SerializedName

data class ResponsePetani(

	@field:SerializedName("data")
	val data: List<ResponsePetaniItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResponsePetaniItem(

	@field:SerializedName("provinsi")
	val provinsi: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("kecamatan")
	val kecamatan: String? = null,

	@field:SerializedName("nama_istri")
	val namaIstri: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jumlah_lahan")
	val jumlahLahan: String? = null,

	@field:SerializedName("kabupaten")
	val kabupaten: String? = null,

	@field:SerializedName("kelurahan")
	val kelurahan: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
