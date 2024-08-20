package com.pbws.newsapp.model

import android.content.Context
import com.pbws.newsapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

data class CategoryItem(
    var id:String = "",
    var name:String= "",
    var image:Int = 0
){

    @Inject
    constructor(@ApplicationContext context: Context) : this()
    companion object{
        fun getCategory(context: Context):List<CategoryItem> {
            return listOf(
                CategoryItem("business", context.getString(R.string.business),R.drawable.bussines_ic),
            CategoryItem("general", context.getString(R.string.general),R.drawable.politics_ic),
            CategoryItem("health", context.getString(R.string.health),R.drawable.health_ic),
            CategoryItem("science", context.getString(R.string.science),R.drawable.science_ic),
            CategoryItem("sports", context.getString(R.string.sports),R.drawable.sports_ic),
            CategoryItem("technology",
                context.getString(R.string.technology), R.drawable.technology_ic),
            )
        }
    }
}
