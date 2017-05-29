package record

interface LibProcessor {
    var hasRecord: Boolean
    val libName:String
    fun process(libName: String)
}

open class LibRecord(override val libName: String) :LibProcessor {
    override var hasRecord: Boolean = false
    companion object{
        const val REACT: String = "com.facebook.react"
        const val WEEX: String = "com.taobao.weex"
        const val CORDOVA: String = "org.apache.cordova"
        const val KOTLIN: String = "kotlin."
    }
    override fun process(libName: String) {
        if(!this.hasRecord && libName.contains(this.libName)) {
            this.hasRecord = true
            System.out.println("contain : " + this.libName);
        }
        //记录到文件中
    }
}

class KotlinRecord(libName: String) : LibRecord(libName){
    var allClassName : List<String>? = null

    constructor(libName: String,allClassName : List<String>):this(libName){
        this.allClassName = allClassName
    }

    override fun process(libName: String) {
        super.process(libName)
    }

}


