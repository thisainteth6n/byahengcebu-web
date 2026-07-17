package com.byahengcebu.mobile.shared.session

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("BYAHENG_SESSION", Context.MODE_PRIVATE)

    fun saveUser(
        fullname: String,
        email: String,
        role: String
    ) {
        prefs.edit()
            .putString("fullname", fullname)
            .putString("email", email)
            .putString("role", role)
            .putBoolean("loggedIn", true)
            .apply()
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("loggedIn", false)
    }

    fun getFullname(): String {
        return prefs.getString("fullname", "") ?: ""
    }

    fun getEmail(): String {
        return prefs.getString("email", "") ?: ""
    }

    fun getRole(): String {
        return prefs.getString("role", "") ?: ""
    }
}