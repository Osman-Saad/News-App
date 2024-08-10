package com.pbws.newsapp.model

import com.pbws.newsapp.R

data class CategoryItem(
    var id:String = "",
    var name:String= "",
    var image:Int = 0
){
    companion object{
        val categories = listOf(
            CategoryItem("business","Business",R.drawable.bussines_ic),
            CategoryItem("general","General",R.drawable.politics_ic),
            CategoryItem("health","Health",R.drawable.health_ic),
            CategoryItem("science","Science",R.drawable.science_ic),
            CategoryItem("sports","Sports",R.drawable.sports_ic),
            CategoryItem("technology","Technology", R.drawable.technology_ic),
        )
    }
}
