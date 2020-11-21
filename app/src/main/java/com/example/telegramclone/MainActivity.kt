package com.example.telegramclone


import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.telegramclone.activities.RegisterActivity
import com.example.telegramclone.databinding.ActivityMainBinding
import com.example.telegramclone.ui.fragments.ChatsFragment
import com.example.telegramclone.ui.objects.AppDrawer
import com.example.telegramclone.utilities.*

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
            initContacts()
            initFields()
            initFunc()
        }
    }



    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }

    }


    private fun initFields() {
        mToolbar = mBinding.mainToolBar
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