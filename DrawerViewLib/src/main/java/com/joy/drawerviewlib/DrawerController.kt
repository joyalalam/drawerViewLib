package com.joy.drawerviewlib

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Keep
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable

open class DrawerController(listOptionsLanding: ArrayList<Option>, private var act: Activity) {

    init {
        val menuListView = RecyclerView(act)
        getLayoutList().addView(menuListView)
        val llm = LinearLayoutManager(act)
        menuListView.layoutManager = llm
        val drawerMenuAdapter = OptionsAdapter(act,listOptionsLanding)
        menuListView.adapter = drawerMenuAdapter
        getBackButton().setOnClickListener{openDrawer()}
    }

    private fun getLayoutList(): LinearLayout {
        return act.findViewById(R.id.layout_list)
    }

    private fun getBackButton(): ImageButton {
        return act.findViewById(R.id.btn_back)
    }

    private fun getDrawerLayout(): DrawerLayout {
        return act.findViewById(R.id.drawer_layout)
    }

    private fun openDrawer(){
        if(getDrawerLayout().getDrawerLockMode(GravityCompat.START) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            getDrawerLayout().openDrawer(GravityCompat.START)
    }

    fun lockDrawer(){
        getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun closeDrawer(){
        getDrawerLayout().closeDrawer(GravityCompat.START)
    }

    class OptionsAdapter(var act: Activity,listOptions: ArrayList<Option>) :
        RecyclerView.Adapter<OptionsAdapter.DrawerViewHolder>() {

        private var list = listOptions.filter { it.isVisible }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
            val holder = DrawerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_options, parent, false)
            )
            holder.itemView.setOnClickListener {
                act.findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawers()
                list[holder.adapterPosition].onAction( list[holder.adapterPosition].menuTypeEnum)
            }
            return holder
        }

        override fun getItemCount(): Int {
            if(list.isNullOrEmpty())
                return 0
            return list.size
        }

        override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
            holder.bind(list[position])
        }

        inner class DrawerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(option: Option) {
                itemView.findViewById<TextView>(R.id.title_menu).text = option.name
                Glide.with(itemView.context)
                    .load(list[adapterPosition].iconDrawable)
                    .into(itemView.findViewById(R.id.img_menu))
                if(adapterPosition > 0 && adapterPosition < list.size.minus(1) && list[adapterPosition-1].category != list[adapterPosition].category){
                    itemView.findViewById<View>(R.id.divider).visibility = View.VISIBLE
                }else{
                    itemView.findViewById<View>(R.id.divider).visibility = View.GONE
                }
            }
        }
    }

    @Keep
    class Option(
        var iconDrawable: Int,
        var name: String,
        var menuTypeEnum: String,
        var category: Int = 0,
        var isVisible: Boolean = true,
        val onAction: (menuTypeEnum: String) -> Unit = {}
    ) : Serializable


}
