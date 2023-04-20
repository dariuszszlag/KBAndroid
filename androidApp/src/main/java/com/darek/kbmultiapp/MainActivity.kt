package com.darek.kbmultiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.darek.kbcore.kbCoreBuilder
import com.darek.kbembed.kbEmbedBuilder
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kbCore = kbCoreBuilder().coroutineDispatcher(Dispatchers.IO).build()

        kbEmbedBuilder()
            .setActivity(this)
            .setKBCore(kbCore)
            .build()

    }
}
