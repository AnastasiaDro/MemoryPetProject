package com.nestdev.memorypetproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchTenWordsFragment()
    }


    private fun launchTenWordsFragment() {
        supportFragmentManager.beginTransaction().apply {
            setReorderingAllowed(true) //TODO  погуглить про свойство
            replace(R.id.main_activity_container, TenWordsFragment.create())
            commitAllowingStateLoss()
        }
    }
}