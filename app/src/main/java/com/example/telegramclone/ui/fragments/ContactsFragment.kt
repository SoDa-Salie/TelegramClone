package com.example.telegramclone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telegramclone.R
import com.example.telegramclone.utilities.APP_ACTIVITY

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"

    }
}