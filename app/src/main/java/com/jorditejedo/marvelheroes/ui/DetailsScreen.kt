package com.jorditejedo.marvelheroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jorditejedo.marvelheroes.R
import com.jorditejedo.marvelheroes.db.entity.UrlType

import androidx.compose.ui.platform.LocalUriHandler


@Composable
fun DetailsScreen(characterId: Int) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val character = viewModel.getCharacter(characterId)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (
            thumbnail,
            name,
            id,
            modified,
            descriptionTitle,
            description,
            comicsAppearancesTitle,
            comicsAppearances,
            seriesAppearancesTitle,
            seriesAppearances,
            storiesAppearancesTitle,
            storiesAppearances,
            eventsAppearancesTitle,
            eventsAppearances,
            linksOfInterest,
            linksOfInterestTitle
        ) = createRefs()

        Image(
            painter = rememberImagePainter(character.thumbnail.path + "." + character.thumbnail.extension),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .constrainAs(thumbnail) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(end = 10.dp, bottom = 10.dp)
        )

        Text(
            text = character.name,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
        )

        Text(
            text = character.id.toString(),
            modifier = Modifier.constrainAs(id) {
                bottom.linkTo(modified.top)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = character.modified,
            modifier = Modifier.constrainAs(modified) {
                bottom.linkTo(thumbnail.bottom)
                start.linkTo(thumbnail.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = stringResource(R.string.description),
            modifier = Modifier
                .constrainAs(descriptionTitle) {
                    top.linkTo(thumbnail.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Text(
            text = if(character.description.isNotEmpty()) character.description else "--",
            modifier = Modifier.constrainAs(description) {
                top.linkTo(descriptionTitle.bottom)
                start.linkTo(parent.start)
            },
            fontSize = 14.sp
        )

        Text(
            text = stringResource(R.string.comics_appearances),
            modifier = Modifier
                .constrainAs(comicsAppearancesTitle) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.constrainAs(comicsAppearances) {
                top.linkTo(comicsAppearancesTitle.bottom)
                start.linkTo(parent.start)
            }
        ) {
            if (character.comics.items.isNotEmpty()) {
                character.comics.items.forEach { comic ->
                    Text(
                        text = "- " + comic.name,
                        fontSize = 14.sp
                    )
                }
            } else {
                Text(
                    text = "--",
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = stringResource(R.string.series_appearances),
            modifier = Modifier
                .constrainAs(seriesAppearancesTitle) {
                    top.linkTo(comicsAppearances.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.constrainAs(seriesAppearances) {
                top.linkTo(seriesAppearancesTitle.bottom)
                start.linkTo(parent.start)
            }
        ) {
            if (character.series.items.isNotEmpty()) {
                character.series.items.forEach { serie ->
                    Text(
                        text = "- " + serie.name,
                        fontSize = 14.sp
                    )
                }
            } else {
                Text(
                    text = "--",
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = stringResource(R.string.stories_appearances),
            modifier = Modifier
                .constrainAs(storiesAppearancesTitle) {
                    top.linkTo(seriesAppearances.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.constrainAs(storiesAppearances) {
                top.linkTo(storiesAppearancesTitle.bottom)
                start.linkTo(parent.start)
            }
        ) {
            if (character.stories.items.isNotEmpty()) {
                character.stories.items.forEach { story ->
                    Text(
                        text = "- " + story.name,
                        fontSize = 14.sp
                    )
                }
            } else {
                Text(
                    text = "--",
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = stringResource(R.string.events_appearances),
            modifier = Modifier
                .constrainAs(eventsAppearancesTitle) {
                    top.linkTo(storiesAppearances.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.constrainAs(eventsAppearances) {
                top.linkTo(eventsAppearancesTitle.bottom)
                start.linkTo(parent.start)
            }
        ) {
            if (character.events.items.isNotEmpty()) {
                character.events.items.forEach { event ->
                    Text(
                        text = "- " + event.name,
                        fontSize = 14.sp
                    )
                }
            } else {
                Text(
                    text = "--",
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = stringResource(R.string.links_of_interest),
            modifier = Modifier
                .constrainAs(linksOfInterestTitle) {
                    top.linkTo(eventsAppearances.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 10.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.constrainAs(linksOfInterest) {
                top.linkTo(linksOfInterestTitle.bottom)
                start.linkTo(parent.start)
            }
        ) {
            if (character.urls.isNotEmpty()) {
                character.urls.forEach { url ->
                    when (url.type) {
                        UrlType.detail.toString() -> {
                            AnnotatedClickableText(stringResource(R.string.detail), url.url)
                        }
                        UrlType.comiclink.toString() -> {
                            AnnotatedClickableText(stringResource(R.string.comics), url.url)
                        }
                        UrlType.wiki.toString() -> {
                            AnnotatedClickableText(stringResource(R.string.wiki), url.url)
                        }
                    }
                }
            } else {
                Text(
                    text = "--",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun AnnotatedClickableText(tag: String, url: String) {
    val annotatedText = buildAnnotatedString {
        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(
            tag = tag,
            annotation = url
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("- $tag")
        }

        pop()
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText
                .getStringAnnotations(
                    tag = tag,
                    start = offset,
                    end = offset
                )
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}