import com.google.classyshark.Shark
import java.io.File
import javax.sound.sampled.Line

fun main(argus: Array<String>) {
    val numbers = listOf(1, 2, 3)
    val apkinfo = "/Users/bang/Downloads/apks/apkinfo.txt"
    var fileLoader = FileLoader(apkinfo)
    fileLoader.listener =  (object : LineReader{
        override fun onLineReaded(source: String){
            val apkEntity1 = ApkEntity.create(source)
            inspectLib(apkEntity1)

        }

    })
    fileLoader.loadFile();
}

private fun inspectLib(apkEntity: ApkEntity) {
    println("\n\n----------------------------- start inspect : " + apkEntity.apk_name_cn + "-----------------------------")
    val diskPath = ApkEntity.getDiskPath(apkEntity.apk_name_en)
    val path = File(diskPath)
    if (!path.exists()) {
        println("file not exits")
        println("------------------------------after inspect : " + apkEntity.apk_name_cn+"-------------------------------")
        return
    }
    var shark = Shark.with(path);
    val allClassNames = shark.getAllClassNames()

    System.out.println("All Classes " + allClassNames.size +
            "\nAll Methods " + shark.getAllMethods().size);
    var hasReact = false;
    var hasWeex = false
    var hasCordova = false;
    var hasKotlin = false;
    for (className in allClassNames) {

        if (!hasReact) {
            if(className.contains("com.facebook.react")){
                System.out.println("contain react");
                hasReact = true
            }
        }
        if (!hasWeex) {
            if (className.contains("com.taobao.weex")){
                System.out.println("contain weex");
                hasWeex =true
            }
        }
        if (!hasCordova) {
            if (className.contains("org.apache.cordova")){
                hasCordova = true
                System.out.println("contain Cordova");
            }
        }
        if (!hasKotlin) {
            if (className.contains("kotlin.")){
                hasKotlin = true
                System.out.println("contain kotlin");
            }
        }
    }
    println("------------------------------after inspect : " + apkEntity.apk_name_cn+"-------------------------------")
}