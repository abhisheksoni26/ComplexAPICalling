package com.example.complexapicalling.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataModel(
    @Expose @SerializedName("effect_entries") var effectEntries: ArrayList<Effects>
)

data class Effects(
   @Expose @SerializedName("effect") var effect: String?,
   @Expose @SerializedName("language") var language: Language,
   @Expose @SerializedName("short_effect") var shortEffect: String?
)

data class Language(
    var name: String?,
    var url: String?
)

