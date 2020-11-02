package com.example.telegramclone.ui.fragments

import androidx.fragment.app.Fragment
import com.example.telegramclone.R
import com.example.telegramclone.utilities.AppTextWatcher
import com.example.telegramclone.utilities.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                verifyCode()
            }
        })
    }

    private fun verifyCode() {
        showToast("Ok")
    }


}