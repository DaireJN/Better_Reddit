package com.daire.betterreddit.presentation

import android.widget.Toast

/*
 * describes common ui elements which are shown from an Activity
 * such as dialogs and progress bars
 */
interface UIController {
    fun displayProgressSpinner(shouldBeVisible: Boolean)
    fun displayToast(message: String, length: Int? = Toast.LENGTH_SHORT)
}