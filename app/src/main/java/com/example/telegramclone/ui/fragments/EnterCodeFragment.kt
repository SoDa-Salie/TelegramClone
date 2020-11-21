package com.example.telegramclone.ui.fragments

import androidx.fragment.app.Fragment
import com.example.telegramclone.MainActivity
import com.example.telegramclone.R
import com.example.telegramclone.activities.RegisterActivity
import com.example.telegramclone.utilities.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val mPhoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {


    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = AUTH.currentUser?.uid.toString()
                    var dataMap = mutableMapOf<String, Any>()
                    dataMap[CHILD_ID] = uid
                    dataMap[CHILD_PHONE] = mPhoneNumber
                    dataMap[CHILD_USERNAME] = uid

                    REF_DATABASE_ROOT
                        .child(NODE_PHONES)
                        .child(mPhoneNumber)
                        .setValue(uid)
                        .addOnFailureListener { showToast(it.message.toString()) }
                        .addOnSuccessListener {
                            REF_DATABASE_ROOT
                                .child(NODE_USERS)
                                .child(uid)
                                .updateChildren(dataMap)
                                .addOnSuccessListener {
                                    showToast("Добро пожаловать")
                                    (activity as RegisterActivity).replaceActivity(MainActivity())
                                }
                                .addOnFailureListener { showToast(it.message.toString()) }
                        }


                } else showToast(task.exception?.message.toString())
            }
    }
}