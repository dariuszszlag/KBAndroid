package com.darek.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.darek.kbcore.KBCore
import com.darek.kbembed.navigation.MainNavHost
import com.darek.kbembed.navigation.Navigator
import com.darek.kbembed.ui.theme.MyApplicationTheme

internal class KBEmbedBuilderImpl(
    private val navigator: Navigator
) : KBEmbedBuilder {

    private lateinit var _activity: ComponentActivity

    private lateinit var _kbCore: KBCore

    override fun setActivity(activity: ComponentActivity): KBEmbedBuilder {
        _activity = activity
        return this
    }

    override fun setKBCore(kbCore: KBCore): KBEmbedBuilder {
        _kbCore = kbCore
        return this
    }

    override fun build(): KBEmbed {
        if (!::_activity.isInitialized) {
            throw IllegalArgumentException("Activity not initialized")
        } else if (!::_kbCore.isInitialized) {
            throw IllegalArgumentException("KBCore not initialized")
        } else {
            _activity.setContent {
                MyApplicationTheme {
                    MainNavHost(_kbCore, navigator)
                }
            }
        }
        return KBEmbedImpl(_kbCore, navigator)
    }

}