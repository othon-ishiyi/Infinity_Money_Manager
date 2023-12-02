package com.example.infinitymoneymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.infinitymoneymanager.ui.theme.InfinityMoneyManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            InfinityMoneyManagerTheme {
                InfinityApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfinityPreview(){
    InfinityApp()
}
