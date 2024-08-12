package com.findyourbook.ui.screen.books.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.findyourbook.R
import com.findyourbook.databinding.BookListNativeBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

@Composable
fun NativeAdBookElement(modifier: Modifier) {
    val context = LocalContext.current
    AndroidViewBinding(
        modifier = modifier,
        factory = BookListNativeBinding::inflate,
        update = {
            val adLoader = AdLoader.Builder(context, context.getString(R.string.ad_native_books_list))
                .forNativeAd { ad: NativeAd ->
                    headline.text = ad.headline
                    body.text = ad.body
                    image.setImageDrawable(ad.images.firstOrNull()?.drawable)
                    root.callToActionView = card
                    root.setNativeAd(ad)
                }
                .withAdListener(
                    object : AdListener() {},
                )
                .withNativeAdOptions(NativeAdOptions.Builder().build())
                .build()
            adLoader.loadAd(AdRequest.Builder().build())
        },
    )
}
