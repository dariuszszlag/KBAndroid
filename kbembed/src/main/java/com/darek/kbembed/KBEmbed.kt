package com.darek.kbembed

import com.darek.kbembed.navigation.Navigator

interface KBEmbed {

    fun getNavigator(): Navigator

    fun login(password: String)

    fun logout()

}
