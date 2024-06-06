package com.example.mvvmsample

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.SemanticsMatcher
import com.example.mvvmsample.utils.ImageVectorSemantic

fun hasImageVector(vector: ImageVector): SemanticsMatcher = SemanticsMatcher.expectValue(ImageVectorSemantic, vector)
