package com.findyourbook

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import com.findyourbook.ui.app.App
import com.findyourbook.ui.theme.ComposeEducationTheme
import com.findyourbook.utils.LocalSemantics
import com.findyourbook.utils.SemanticsStrings
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ExampleMainViewModel by viewModels()
    private val backgroundScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()
        backgroundScope.launch {
            MobileAds.initialize(this@MainActivity) {}
        }
        setContent {
            CompositionLocalProvider(LocalSemantics provides SemanticsStrings()) {
                ComposeEducationTheme {
                    App()
                }
            }
        }
    }

    private fun setupSplashScreen() {
        val toAlpha = 0.0f
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isSplashShouldBeRemoved.value
            }
            setOnExitAnimationListener { splashView: SplashScreenViewProvider ->
                ObjectAnimator.ofFloat(
                    splashView.iconView,
                    View.ALPHA,
                    splashView.iconView.alpha,
                    toAlpha,
                ).apply {
                    duration = SPLASH_EXIT_ANIMATION_DURATION
                    doOnEnd {
                        splashView.remove()
                    }
                }.also { animator ->
                    animator.start()
                }
            }
        }
    }

    companion object {
        private const val SPLASH_EXIT_ANIMATION_DURATION = 1000L
    }
}
