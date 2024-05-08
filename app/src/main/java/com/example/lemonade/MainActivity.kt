package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableIntStateOf(0) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 28.sp
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                0 -> {
                    WelcomeScreen(name = "DEVKUMAR",
                   onStartClicked = {
                       currentStep = 1
                   } )
                }
                1 -> {
                    LemonadeElements(
                        textLabelResourceId = R.string.tree,
                        drawableResourceId = R.drawable.lemon_tree,
                        contentDescriptionResourceId = R.string.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..5).random()

                        }
                    )
                }

                2 -> {
                    LemonadeElements(
                        textLabelResourceId = R.string.squeeze,
                        drawableResourceId = R.drawable.lemon_squeeze,
                        contentDescriptionResourceId = R.string.lemon,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonadeElements(
                        textLabelResourceId = R.string.drink,
                        drawableResourceId = R.drawable.lemon_drink,
                        contentDescriptionResourceId = R.string.Glass_of_lemonade,
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }

                4 -> {
                    LemonadeElements(
                        textLabelResourceId = R.string.restart,
                        drawableResourceId = R.drawable.lemon_restart,
                        contentDescriptionResourceId = R.string.Empty_glass,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(name: String,
                  onStartClicked: () -> Unit
            ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Welcome $name!",
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.start_padding_vertical)))
        Button(
            onClick = onStartClicked,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.start_button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.outlineVariant)
        ) {
            Text("Start",
                style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun LemonadeElements(
        textLabelResourceId: Int,
        drawableResourceId: Int,
        contentDescriptionResourceId: Int,
        onImageClick: () -> Unit,
        modifier: Modifier = Modifier
){
    Box(modifier=modifier){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(painter = painterResource(id = drawableResourceId),
                      contentDescription = stringResource(id = contentDescriptionResourceId),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.button_image_width))
                        .height(dimensionResource(id = R.dimen.button_image_height))
                        .padding(dimensionResource(id = R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_vertical)))
            Text(
                text = stringResource(id = textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun LemonPreview(){
        LemonadeTheme {
            LemonadeApp()
    }
}