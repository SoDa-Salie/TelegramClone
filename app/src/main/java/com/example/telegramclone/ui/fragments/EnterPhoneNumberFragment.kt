package com.example.telegramclone.ui.fragments

import androidx.fragment.app.Fragment
import com.example.telegramclone.R
import com.example.telegramclone.utilities.replaceFragment
import com.example.telegramclone.utilities.showToast
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.regester_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
}