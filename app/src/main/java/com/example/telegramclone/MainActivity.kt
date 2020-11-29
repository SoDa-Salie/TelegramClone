package com.example.telegramclone

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.telegramclone.database.AUTH
import com.example.telegramclone.database.initFirebase
import com.example.telegramclone.database.initUser
import com.example.telegramclone.databinding.ActivityMainBinding
import com.example.telegramclone.ui.fragments.MainFragment
import com.example.telegramclone.ui.fragments.register.EnterPhoneNumberFragment
import com.example.telegramclone.ui.objects.AppDrawer
import com.example.telegramclone.utilities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }
    }


    private fun initFunc() {
        setSupportActionBar(mToolbar)
        if (AUTH.currentUser != null) {
            mAppDrawer.create()
            replaceFragment(MainFragment(), false)
        } else {
            replaceFragment(EnterPhoneNumberFragment(), false)
        }

    }


    private fun initFields() {
        mToolbar = mBinding./*main_toolbar*/mainToolbar
        mAppDrawer = AppDrawer()
    }


    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }


    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            initContacts()
        }
    }

}