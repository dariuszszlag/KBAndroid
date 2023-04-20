package com.darek.kbembed

import com.darek.kbcore.KBCore
import com.darek.kbembed.navigation.Navigator

internal class KBEmbedImpl(
    private val kbCore: KBCore,
    private val navigator: Navigator
) : KBEmbed {

    override fun getNavigator(): Navigator = navigator

    override fun login(password: String) {
        kbCore.getDataForUser(password)
        navigator.navigateHome()
    }

    override fun logout() {
        kbCore.logout()
        navigator.navigateToLogin()
    }

}