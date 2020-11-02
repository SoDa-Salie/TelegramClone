package com.example.telegramclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.telegramclone.activities.RegisterActivity
import com.example.telegramclone.databinding.ActivityMainBinding
import com.example.telegramclone.ui.fragments.ChatsFragment
import com.example.telegramclone.ui.objects.AppDrawer
import com.example.telegramclone.utilities.replaceActivity
import com.example.telegramclone.utilities.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
        //Методы
    }

    private fun initFunc() {
        if (false) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())
        } else {
            replaceActivity(RegisterActivity())


        }

    }

    private fun initFields() {
        // Метод инициализации полей
        mToolbar = mBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}