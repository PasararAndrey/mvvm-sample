package com.findyourbook

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.SemanticsMatcher
import com.findyourbook.utils.ImageVectorSemantic

fun hasImageVector(vector: ImageVector): SemanticsMatcher = SemanticsMatcher.expectValue(ImageVectorSemantic, vector)
