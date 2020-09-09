package com.example.portfolia

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)


        change.setOnClickListener {
            val popupmenu= PopupMenu(this, it)
            popupmenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.pop1 -> {
                        setLocalate("eng")
                        recreate()
                        true
                    }
                    R.id.pop2 -> {
                        setLocalate("uz")
                        recreate()
                        true
                    }

                    else -> false
                }
            }
            popupmenu.inflate(R.menu.popup_menu)
            popupmenu.show()
        }
    }

   private fun setLocalate( lang:String){
        val locale=Locale(lang)
        Locale.setDefault(locale)
        val conf=Configuration()
        conf.locale=locale
        baseContext.resources.updateConfiguration(conf,baseContext.resources.displayMetrics)

        val sharedPreference=getSharedPreferences("setting", Context.MODE_PRIVATE)
        sharedPreference.edit().putString("LANG",lang).apply()
    }

   private fun loadLocale(){
        val sharedPreference=getSharedPreferences("setting", Context.MODE_PRIVATE)
        val lang=sharedPreference.getString("LANG","")
        if (lang != null) {
            setLocalate(lang)
        }
    }

}