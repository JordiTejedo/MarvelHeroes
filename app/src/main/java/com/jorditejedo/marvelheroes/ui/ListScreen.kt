package com.jorditejedo.marvelheroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jorditejedo.marvelheroes.R
import com.jorditejedo.marvelheroes.db.entity.Character

@Composable
fun ListScreen(navController: NavController) {
    val viewModel: ListViewModel = hiltViewModel()

    val characters by viewModel.characters.observeAsState()

    Column(Modifier.fillMaxSize()) {
        if (!characters.isNullOrEmpty()) {
            TextField(
                value = stringResource(R.string.search),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn() {
                items(
                    items = characters!!,
                    itemContent = {
                        CharacterItem(it, navController)
                    }
                )
            }
        } else {
            Text(stringResource(R.string.no_heroes_found))
        }
    }
}

@Composable
fun CharacterItem(character: Character, navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate(
                    Destination.DetailsScreen.route.replace(
                        "{characterId}",
                        character.id.toString()
                    )
                )
            }
    ) {
        val (thumbnail, name, id, modified) = createRefs()

        Image(
            painter = rememberImagePainter(character.thumbnail.path + "." + character.thumbnail.extension),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .constrainAs(thumbnail) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(end = 10.dp)
        )

        Text(
            text = character.name,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = character.id.toString(),
            modifier = Modifier.constrainAs(id) {
                bottom.linkTo(modified.top)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = character.modified,
            modifier = Modifier.constrainAs(modified) {
                bottom.linkTo(parent.bottom)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}