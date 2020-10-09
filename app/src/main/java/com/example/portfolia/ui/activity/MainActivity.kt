package com.example.portfolia.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.portfolia.R
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.repository.RegisterRepository
import com.example.portfolia.util.AlarmBroadcastReceiver
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.example.restaurants.ui.viewmodel.RegisterViewModelFactory
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel:RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#ffffff")
        }
        super.onCreate(savedInstanceState)
       // loadLocale()
        setContentView(R.layout.activity_main)
        val repository= RegisterRepository(MainDatabase(this))
        val viewModelProviderFactory = RegisterViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RegisterViewModel::class.java)
        startAlarmBroadcastReceiver(this)

      /*  change.setOnClickListener {
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

       */
    }

    private fun setLocalate(lang: String){
        val locale=Locale(lang)
        Locale.setDefault(locale)
        val conf=Configuration()
        conf.locale=locale
        baseContext.resources.updateConfiguration(conf, baseContext.resources.displayMetrics)

        val sharedPreference=getSharedPreferences("setting", Context.MODE_PRIVATE)
        sharedPreference.edit().putString("LANG", lang).apply()
    }

   private fun loadLocale(){
        val sharedPreference=getSharedPreferences("setting", Context.MODE_PRIVATE)
        val lang=sharedPreference.getString("LANG", "")
        if (lang != null) {
            setLocalate(lang)
        }
    }

    private fun startAlarmBroadcastReceiver(context: Context) {
        val _intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, _intent, 0)
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 2
        calendar[Calendar.MINUTE] = 2
        calendar[Calendar.SECOND] = 0
        alarmManager[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent
    }
}