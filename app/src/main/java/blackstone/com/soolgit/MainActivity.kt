package blackstone.com.soolgit

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import blackstone.com.soolgit.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewpager.adapter = IntroAdapter(supportFragmentManager)
        viewpager.setPageTransformer(false, IntroPageTransformer(viewpager))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var localeLanguage = when (item?.itemId) {
            R.id.locale_ko -> "ko"
            R.id.locale_en -> "en"
            R.id.locale_es -> "es"
            else -> "en"
        }
        Log.d("TAGGG", localeLanguage)
        var localStore = getSharedPreferences("LocalData_student", Context.MODE_PRIVATE)
        localStore.edit().putString("language", localeLanguage).commit().run {
            Log.d("TAGGGGGG", localStore.getString("language",""))
            restartApp(this@MainActivity, MainActivity::class.java)
        }
        return true
    }

    fun restartApp(context: Context?, activity: Class<*>) {
        var restartActivity = Intent(context, activity)
        var pendingIntent = PendingIntent.getActivity(context, 77789, restartActivity, PendingIntent.FLAG_CANCEL_CURRENT)
        var mgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
        ActivityCompat.finishAffinity(context as Activity)
        System.runFinalization()
        System.exit(0)
    }
}