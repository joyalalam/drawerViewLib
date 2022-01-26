package com.example.drawerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : CommonActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun withDrawer(): Boolean {
        return true
    }
}