package com.example.complexapicalling.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BaseAbility(
    var abilities: ArrayList<AbilityModel>,
    var sprites: SpritesModel
    )

data class AbilityModel(var ability: AbilityName)

data class AbilityName(
    var name: String?,
    var url: String?
)

data class SpritesModel(
    @Expose @SerializedName("back_default") var backDefault: String?,
    @Expose @SerializedName("back_female") var backFemale: String?,
    @Expose @SerializedName("back_shiny") var backShiny: String?,
    @Expose @SerializedName("back_shiny_female") var backShinyFemale: String?,
    @Expose @SerializedName("front_default") var frontDefault: String?,
    @Expose @SerializedName("front_female") var frontFemale: String?,
    @Expose @SerializedName("front_shiny") var frontShiny: String?,
    @Expose @SerializedName("front_shiny_female") var frontShinyFemale: String?
)

