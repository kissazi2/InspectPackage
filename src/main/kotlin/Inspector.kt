import com.google.classyshark.Shark
import record.ApkEntity
import record.LibRecord
import java.io.File

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

    var processList = ArrayList<LibRecord>()
    processList.add(LibRecord(LibRecord.REACT, apkEntity))
    processList.add(LibRecord(LibRecord.WEEX, apkEntity))
    processList.add(LibRecord(LibRecord.CORDOVA, apkEntity))
    processList.add(LibRecord(LibRecord.KOTLIN, apkEntity))
    for (className in allClassNames) {
        for (libRecord in processList) {
            libRecord.process(className)
        }
    }
    println("------------------------------after inspect : " + apkEntity.apk_name_cn+"-------------------------------")
}