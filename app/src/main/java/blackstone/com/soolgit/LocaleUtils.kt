package blackstone.com.soolgit

import android.content.Context
import android.telephony.TelephonyManager


fun getISOCode(context: Context?): String {
    val tm = context?.applicationContext?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
    return tm?.simCountryIso?:""
}

// 핸드폰 설정 언어가 한국 , 일본이 아닌 다른 언어를 사용하는 나라의 유심 체크 -> 콴다에서 지원해주는 나라는 자동으로 로컬에 저장, 그게 아니면 다이얼로그

fun checkISOCode(context: Context): LOCALE? {
    var code = getISOCode(context).toUpperCase()
    return when (code) {
        "KR" -> LOCALE.KOREAN
        "JP" -> LOCALE.JAPAN
        "US" -> LOCALE.ENGLISH_STANDARD
        "VN" -> LOCALE.VIETNAM
        "ID" -> LOCALE.INDONESIA
        else -> LOCALE.ENGLISH_STANDARD
    }
}