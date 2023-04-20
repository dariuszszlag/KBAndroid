package com.darek.kbembed

import androidx.activity.ComponentActivity
import com.darek.kbcore.KBCore
import com.darek.kbembed.navigation.Navigator

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity): KBEmbedBuilder

    fun setKBCore(kbCore: KBCore): KBEmbedBuilder

    fun build(): KBEmbed

}

fun kbEmbedBuilder(): KBEmbedBuilder = KBEmbedBuilderImpl(Navigator())