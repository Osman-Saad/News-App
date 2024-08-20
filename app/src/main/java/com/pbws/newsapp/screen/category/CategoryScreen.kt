package com.pbws.newsapp.screen.category

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pbws.newsapp.model.CategoryItem
import com.pbws.newsapp.screen.news.navigateToNewsScreen


@Composable
fun CategoryScreen(navController: NavController) {
        CategoryContent(
            onClick = {
                navController.navigateToNewsScreen(it.id)
            }
        )
}


@Composable
fun CategoryContent(
    context:Context = LocalContext.current,
    categoryItemList: List<CategoryItem> = CategoryItem.getCategory(context.applicationContext),
    onClick: (categoryItem: CategoryItem) -> Unit
) {

    Column {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            columns = GridCells.Fixed(2)
        ) {

            items(items = categoryItemList) { category ->
                CategoryItemView(categoryItem = category, onClick)
            }
        }
    }
}

@Composable
fun CategoryItemView(
    categoryItem: CategoryItem,
    onClick: (categoryItem: CategoryItem) -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 16.dp)
            )
            .clickable(
                role = Role.Button,
                onClick = { onClick(categoryItem) },
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(220.dp)
                .background(MaterialTheme.colorScheme.surface)

        ) {
            Image(
                painter = painterResource(id = categoryItem.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(16.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                text = categoryItem.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
            )

        }
    }
}