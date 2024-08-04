package com.support.aitourism.features.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.support.aitourism.R
import kotlinx.coroutines.launch

@Composable
fun BoardingScreen(
    onLastPage: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val boardingImages =
        listOf(
            R.drawable.boarding_1,
            R.drawable.boarding_2,
            R.drawable.boarding_3
        )
    val boardingCaption = listOf(
        "Coming to visit one of the Egyptian Landmarks ?",
        "Need information about specific artifact ?",
        "Don’t worry all you need is to scan it with your phone",
    )
    HorizontalPager(state = pagerState) { page ->
        // Our page content
        Image(
            painter = painterResource(id = boardingImages[pagerState.currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }

// scroll to page
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(
                text = boardingCaption[pagerState.currentPage],
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Button(onClick = {
                coroutineScope.launch {
                    // Call scroll to on pagerState
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                    if (pagerState.currentPage == 2) {
                        onLastPage()
                    }
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Next")
            }
        }
    }
}


@Preview
@Composable
fun BoardingScreenPreview() {
    BoardingScreen()
}
