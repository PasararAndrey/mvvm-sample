package com.findyourbook.ads

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.findyourbook.R
import com.findyourbook.utils.findActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

fun showOnFavoriteInterstitialAd(context: Context) {
    InterstitialAd.load(
        context,
        context.getString(R.string.ad_interstitial_on_favorite),
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                ad.show(context.findActivity())
            }
        },
    )
}

@Composable
fun AdFavoriteBanner(modifier: Modifier = Modifier) =
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = context.getString(R.string.ad_banner_favorite)
                loadAd(AdRequest.Builder().build())
            }
        },
    )
