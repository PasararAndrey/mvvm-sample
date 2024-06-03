package com.example.mvvmsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.example.mvvmsample.ui.app.App
import com.example.mvvmsample.ui.theme.ComposeEducationTheme
import com.example.mvvmsample.utils.LocalSemanticsStrings
import com.example.mvvmsample.utils.SemanticsStrings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides SemanticsStrings()) {
                ComposeEducationTheme {
                    App()
                }
            }
        }
    }
}
