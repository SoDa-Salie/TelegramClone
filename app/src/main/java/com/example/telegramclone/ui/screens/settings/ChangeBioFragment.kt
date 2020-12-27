package com.example.telegramclone.ui.screens.settings

import com.example.telegramclone.R
import com.example.telegramclone.database.USER
import com.example.telegramclone.database.setBioToDatabase
import com.example.telegramclone.ui.screens.base.BaseChangeFragment
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
}