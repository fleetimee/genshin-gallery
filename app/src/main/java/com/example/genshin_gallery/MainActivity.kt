package com.example.genshin_gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.genshin_gallery.ui.theme.GenshingalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenshingalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GenshinGalleryLayout()
                }
            }
        }
    }
}



@Composable
fun GenshinGalleryLayout() {

    var archonName by remember { mutableStateOf(1)}
    val pageCount by remember { mutableStateOf(1)}

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        when (archonName) {
            1 -> {
                PhotoAndDescription(
                    imageResource = R.drawable.furina,
                    title = R.string.genshin_title_one,
                    description = R.string.genshin_description_name
                )
            }
            2 -> {
                PhotoAndDescription(
                    imageResource = R.drawable.zhongli,
                    title = R.string.genshin_title_two,
                    description = R.string.genshin_description_name_two
                )
            }
            3 -> {
                PhotoAndDescription(
                    imageResource = R.drawable.venti,
                    title = R.string.genshin_title_three,
                    description = R.string.genshin_description_name_three
                )
            }
            4 -> {
                PhotoAndDescription(
                    imageResource = R.drawable.raiden,
                    title = R.string.genshin_title_four,
                    description = R.string.genshin_description_name_four
                )
            }
            5 -> {
                PhotoAndDescription(
                    imageResource = R.drawable.nahida,
                    title = R.string.genshin_title_five,
                    description = R.string.genshin_description_name_five
                )
            }
        }

        PhotoActionButton(
            next = {
                if (archonName < 5) {
                    archonName++
                } else {
                    archonName = 1
                }
            },
            previous = {
                if (archonName > 1) {
                    archonName--
                } else {
                    archonName = 5
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        )

    }
}

@Composable
fun PhotoAndDescription(
    @DrawableRes imageResource: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        shadowElevation = 4.dp,

        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
    ) {
        PhotoContainer(
            imageResource = imageResource,
            modifier = Modifier
                .padding(16.dp)
                .shadow(4.dp)
        )
    }


    PhotoDescription(
        title = title,
        description = description
    )

}

@Composable
fun PhotoContainer(
   @DrawableRes imageResource: Int,
   modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,

        )
    }
}

@Composable
fun PhotoDescription(
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.extraSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.headlineLarge,

        )

        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )




    }
}

@Composable
fun PhotoActionButton(
    next: () -> Unit,
    previous: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        PhotoButtons(
            label = R.string.previous_button,
            onClick = previous,
            modifier = Modifier.weight(2f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        PhotoButtons(
            label = R.string.next_button,
            modifier = Modifier.weight(2f),
            onClick = next
        )
    }
}


@Composable
fun PhotoButtons(
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(text = stringResource(id = label))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GenshingalleryTheme {
        PhotoDescription(title = R.string.genshin_title_one, description = R.string.genshin_description_name)
    }
}


