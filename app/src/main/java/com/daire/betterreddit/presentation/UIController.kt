package com.daire.betterreddit.presentation

/*
 * describes common ui elements which are shown from an Activity
 * such as dialogs and progress bars
 */
interface UIController {
    fun displayProgressSpinner(shouldBeVisible: Boolean)
}