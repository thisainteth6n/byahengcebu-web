package com.byahengcebu.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.byahengcebu.mobile.navigation.AppNavigation
import com.byahengcebu.mobile.ui.theme.ByahengCebuMobileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            ByahengCebuMobileTheme {

                AppNavigation()

            }

        }

    }

}