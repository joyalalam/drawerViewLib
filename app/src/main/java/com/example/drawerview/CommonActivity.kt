package com.example.drawerview

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.drawerview.CommonActivity.MenuTypeEnum.*
import com.joy.drawerviewlib.DrawerController
import com.joy.drawerviewlib.DrawerController.*
import java.io.Serializable

abstract class CommonActivity : AppCompatActivity() {

    protected abstract fun getLayout(): Int

    protected abstract fun withDrawer(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        val layoutContainer = findViewById<LinearLayout>(R.id.layout_container)
        View.inflate(this, getLayout(), layoutContainer)
        initDrawer()
    }

    private fun initDrawer() {
        val listOptionsLanding: ArrayList<Option> = ArrayList()

        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login_history",
                TRANSFERS.name,
                isVisible = true,
                onAction = {
                    goTo()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login1_history",
                CARDS.name,
                isVisible = true,
                onAction = {
                    goTo()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login2_history",
                ACCOUNT.name,
                isVisible = true,
                onAction = {
                    goTo()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login3_history",
                CARDS.name,
                category = 1,
                isVisible = false,
                onAction = {
                    goTo()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login4_history",
                MY_POINTS.name,
                isVisible = false,
                category = 1,
                onAction = {
                    recreate()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login5_history",
                MY_POINTS.name,
                isVisible = true,
                onAction = {
                    recreate()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login6_history",
                MY_POINTS.name,
                isVisible = true,
                onAction = {
                    recreate()
                }
            )
        )
        listOptionsLanding.add(
            Option(
                R.drawable.icon_custom_login_history_selected,
                "login7_history",
                MY_POINTS.name,
                isVisible = true,
                onAction = {
                    recreate()
                }
            )
        )

        val drawerController = DrawerController(listOptionsLanding,this)
        if(withDrawer().not())
            drawerController.lockDrawer()

    }

    enum class MenuTypeEnum : Serializable {
        ACCOUNT,
        CARDS,
        TRANSFERS,
        MY_POINTS
    }

    private fun goTo() {
       //recreate()
    }

}