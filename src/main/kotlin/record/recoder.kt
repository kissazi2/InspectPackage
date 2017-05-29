package record

interface LibProcessor {
    var hasRecord: Boolean
    val libName:String

    fun process(libName: String)
}

class ReactRecord(override val libName: String) :LibProcessor {
    override var hasRecord: Boolean = false
        get() = field //To change initializer of created properties use File | Settings | File Templates.
        set(value) {
            field = value
        }

    override fun process(libName: String) {
        if(!this.hasRecord && libName.contains(this.libName)) {
            this.hasRecord = true
            System.out.println("contain" + this.libName);
        }
        //记录到文件中
    }
}

