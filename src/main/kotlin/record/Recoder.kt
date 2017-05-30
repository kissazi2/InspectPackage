package record

import java.io.FileWriter
import java.io.BufferedWriter
import java.io.File


interface LibProcessor {
    var hasRecord: Boolean
    val libName:String
    fun process(libName: String)
}

class LibRecord :LibProcessor {
    override val libName: String
    internal val apkEntity: ApkEntity

    constructor(libName: String, apkEntity: ApkEntity) {
        this.libName = libName
        this.apkEntity = apkEntity
    }

    override var hasRecord: Boolean = false
    companion object{
        const val REACT: String = "com.facebook.react"
        const val WEEX: String = "com.taobao.weex"
        const val CORDOVA: String = "org.apache.cordova"
        const val KOTLIN: String = "kotlin."
        const val FILE_PREFIX: String = "/Users/bang/Downloads/apks/"

    }
    override fun process(libName: String) {
        if(!this.hasRecord && libName.contains(this.libName)) {
            this.hasRecord = true
            System.out.println("contain : " + this.libName);
            record2File(this.libName)
        }
    }

    private fun record2File(libName: String) {
        //记录到文件中
        val file = File(FILE_PREFIX + libName + getPosFix(libName))
        if (!file.exists()) {
            file.createNewFile()
        }

        val bufferedWriter = BufferedWriter(FileWriter(file, true))
        bufferedWriter.append(this.apkEntity.apk_name_cn)
        bufferedWriter.newLine()
        bufferedWriter.flush();
        bufferedWriter.close()
    }

    private fun getPosFix(libName: String) = if (libName.endsWith('.')) "md" else ".md"
}