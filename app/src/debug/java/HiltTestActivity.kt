package com.daire.betterreddit

import androidx.appcompat.app.AppCompatActivity
import com.daire.betterreddit.presentation.UIController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(), UIController {
    override fun displayProgressSpinner(shouldBeVisible: Boolean) {

    }

    override fun displayToast(message: String, length: Int?) {

    }
}