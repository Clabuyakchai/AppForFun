package com.kuki.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuki.presentation.R

@Composable
fun Toolbar(
    text: String,
    modifier: Modifier = Modifier,
    onBackButtonClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val mutableInteractionSource = remember { MutableInteractionSource() }

        onBackButtonClick?.let {
            Box(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
                    .clickable(
                        interactionSource = mutableInteractionSource,
                        indication = ripple(
                            radius = 30.dp
                        ),
                        onClick = onBackButtonClick
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 7.5.dp)
                )
            }
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            maxLines = 1,
            style = TextStyle(
                fontSize = 30.sp, // TODO след шаг зайти через настройку темы,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
@Preview
fun ToolbarPreview() {
    Surface(
        color = Color.White
    ) {
        Toolbar(text = "Conatcts", onBackButtonClick = {})
    }
}