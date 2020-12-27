package com.example.telegramclone.ui.screens.settings

import com.example.telegramclone.R
import com.example.telegramclone.database.*
import com.example.telegramclone.ui.screens.base.BaseChangeFragment
import com.example.telegramclone.utilities.AppValueEventListener
import com.example.telegramclone.utilities.showToast
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    lateinit var mNewUsername: String


    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)
    }


    override fun change() {
        mNewUsername = settings_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT
                .child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Такой пользователь уже существует")
                    } else {
                        changeUsername()
                    }
                })
        }
    }


    private fun changeUsername() {
        REF_DATABASE_ROOT
            .child(NODE_USERNAMES)
            .child(mNewUsername)
            .setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(mNewUsername)
                }
            }
    }


}