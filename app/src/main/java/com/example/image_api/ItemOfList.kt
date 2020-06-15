package com.example.image_api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemOfList(
    val urlstring:String
) : Parcelable
