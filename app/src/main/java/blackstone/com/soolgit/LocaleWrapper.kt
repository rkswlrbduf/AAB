package blackstone.com.soolgit

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import java.util.*

class LocaleWrapper constructor(base: Context?): ContextWrapper(base) {

    companion object {
        fun wrap(context: Context?): ContextWrapper {
            context?.let { context ->
                var localStore = context.getSharedPreferences("LocalData_student", Context.MODE_PRIVATE)
                var localeLanguage = localStore.getString("language", null)
                if (localeLanguage == null) {
                    var locale = checkISOCode(context)
                    localStore.edit().putString("language", locale?.languageCode).apply()
                    localStore.edit().putString("locale", locale?.locale).apply()
                    localStore.edit().putString("locale_display_name", locale?.displayName).apply()
                    localStore.edit().putString("locale_using_english", locale?.usingLanguage).apply()
                    localeLanguage = locale?.languageCode
                }
                var locale = Locale(localeLanguage)
                Locale.setDefault(locale)
                var configuration = context.resources.configuration
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    configuration.setLocale(locale)
                    return ContextWrapper(context.createConfigurationContext(configuration))
                } else {
                    configuration.locale = locale
                    context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
                }
            }
            return ContextWrapper(context)
        }
    }


}