package com.example.telegramclone.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.R
import com.example.telegramclone.models.CommonModel
import com.example.telegramclone.ui.screens.base.BaseFragment
import com.example.telegramclone.utilities.APP_ACTIVITY
import com.example.telegramclone.utilities.getPlurals
import com.example.telegramclone.utilities.hideKeyboard
import com.example.telegramclone.utilities.showToast
import kotlinx.android.synthetic.main.fragment_create_group.*

class CreateGroupFragment(private var listContacts: List<CommonModel>) : BaseFragment(R.layout.fragment_create_group) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.create_group)
        hideKeyboard()
        initRecyclerView()
        create_group_btn_done.setOnClickListener { showToast("Click!") }
        create_group_input_name.requestFocus()
        create_group_counts.text = getPlurals(listContacts.size)
    }

    private fun initRecyclerView() {
        mRecyclerView = create_group_recycler_view
        mAdapter = AddContactsAdapter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach { mAdapter.updateListItems(it) }
    }
}