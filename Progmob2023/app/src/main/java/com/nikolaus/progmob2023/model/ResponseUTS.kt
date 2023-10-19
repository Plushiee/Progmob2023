package com.nikolaus.progmob2023.model

import com.google.gson.annotations.SerializedName

data class ResponseUTS(

	@field:SerializedName("ResponseUTS")
	val responseUTS: List<ResponseUTSItem>? = null
)

data class ResponseUTSItem(

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
