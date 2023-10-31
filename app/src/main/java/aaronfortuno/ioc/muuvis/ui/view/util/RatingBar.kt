package aaronfortuno.ioc.muuvis.ui.view.util

import aaronfortuno.ioc.muuvis.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    rating: Double = 0.0,
    stars: Int = 5,
    modifier: Modifier = Modifier,
) {

    val filledStars = floor(rating).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    val unfilledStars = (stars - ceil(rating)).toInt()

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(painterResource(R.drawable.ic_star), contentDescription = null)
        }
        if (halfStar) {
            Icon(painterResource(R.drawable.ic_star_half), contentDescription = null)
        }
        repeat(unfilledStars) {
            Icon(painterResource(R.drawable.ic_star_outline), contentDescription = null)
        }

    }
}