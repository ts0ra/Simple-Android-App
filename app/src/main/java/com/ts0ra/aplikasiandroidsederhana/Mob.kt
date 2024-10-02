package com.ts0ra.aplikasiandroidsederhana

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mob(
    val name: String,
    val description: String,
    val photo: Int,
    val health: String,
    val damage: String,
    val identifier: String,
    val link: String
) : Parcelable
