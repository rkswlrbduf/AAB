package blackstone.com.soolgit

enum class LOCALE (
    var displayName : String,
    var englishName : String,
    var localName : String,
    var languageCode: String, // 서버 X-Service-Locale 보내는 값
    var locale: String, // 서버 X-Service-Locale 보내는 값
    var usingLanguage: String, //나라에서 쓰는 랭귀지
    var localeNumber: String
) {
    KOREAN("한국어","Korean","한국어","ko","ko_KR", "ko", "+82"),
    JAPAN("일본어","Japanese","日本語/にほんご","ja","ja_JP", "ja", "+81"),
    ENGLISH_STANDARD("영어","English","English","en","en_US", "en", "+82"),
    VIETNAM("베트남어", "Vietnamese", "Tiếng việt", "vi", "vi_VN", "vi", "+84"),
    INDONESIA("인도네시아어", "Indonesian", "Bahasa Indonesia", "id", "id_ID", "id", "+62");

    companion object {
        fun getLocaleChoiceList(): List<LOCALE> {
            return values().toList()
        }
    }
}

class LocaleLog(
    var iso_code : String,
    var language : String
)

