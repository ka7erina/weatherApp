package com.example.weatherappkaterina.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressBarComponent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBarComponentPreview() {
    ProgressBarComponent()
}