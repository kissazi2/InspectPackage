package record

interface LibProcessor {
    var hasRecord: Boolean
    val libName:String
    fun process(libName: String)
}

open class LibRecord(override val libName: String) :LibProcessor {
    override var hasRecord: Boolean = false
    companion object{
        val REACT: String
            get() {
                return "com.facebook.react"
            }
        val WEEX:String
            get() {
                return "com.taobao.weex"
            }
        val CORDOVA:String
            get() {
                return "org.apache.cordova"
            }
        val KOTLIN:String
            get() {
                return "kotlin."
            }

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

}


