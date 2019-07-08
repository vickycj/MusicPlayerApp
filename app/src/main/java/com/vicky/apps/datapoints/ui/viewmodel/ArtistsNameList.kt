package com.vicky.apps.datapoints.ui.viewmodel


import com.google.gson.annotations.SerializedName

data class ArtistsNameList(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("next")
    var next: String?,
    @SerializedName("total")
    var total: Int?
) {
    data class Data(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("link")
        var link: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("nb_album")
        var nbAlbum: Int?,
        @SerializedName("nb_fan")
        var nbFan: Int?,
        @SerializedName("picture")
        var picture: String?,
        @SerializedName("picture_big")
        var pictureBig: String?,
        @SerializedName("picture_medium")
        var pictureMedium: String?,
        @SerializedName("picture_small")
        var pictureSmall: String?,
        @SerializedName("picture_xl")
        var pictureXl: String?,
        @SerializedName("radio")
        var radio: Boolean?,
        @SerializedName("tracklist")
        var tracklist: String?,
        @SerializedName("type")
        var type: String?
    )
}