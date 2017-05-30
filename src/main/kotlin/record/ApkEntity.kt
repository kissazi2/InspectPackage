package record

data class ApkEntity(val apk_id: String, val apk_name_en: String, val apk_name_cn: String,
                              val apk_url: String, val apk_webpage: String, val apk_icon: String) {
    companion object Factory {
        fun create(source: String): ApkEntity {
            val slice = source.slice(IntRange(1, source.length - 2))
            val items = slice.split('|')
            val apkEntity = ApkEntity(items[0], items[1], items[2], items[3], items[4], items[5])
//            println(apkEntity)
            return apkEntity
        }

        fun getDiskPath(name: String): String {
            val preFix = "/Users/bang/Downloads/apks/"
            val postFix = ".apk"
            return preFix + name + postFix
        }
    }
}