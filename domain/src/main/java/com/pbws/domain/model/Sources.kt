package com.pbws.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Sources(
    val sources: List<SourcesItem?>? = null,

    val status: String? = null
)



@Parcelize
data class SourcesItem(
    val id: String,
    val name: String? = null,
    val category: String? = null,
) : Parcelable