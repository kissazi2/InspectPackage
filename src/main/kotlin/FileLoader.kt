import java.io.BufferedReader
import java.io.File
import java.io.FileReader


interface LineReader{
    fun onLineReaded(source :String)
}
class FileLoader(filePath : String){
    var filePath = filePath;
    var listener : LineReader? = null ;
    fun addLineReadListener(listen :LineReader){
        this.listener = listen;
    }

    var allFile : List<File>? = null
        get() {
            val file = File(this.filePath)
            return file.listFiles(fun(file, fileName) = fileName.contains(".apk")).asList()
        }

    fun loadFile(): Unit {
        var file = File(this.filePath)
        BufferedReader(FileReader(file)).use { br ->
            var count = 1
            var line = br.readLine()
            while (line != null) {
                // process the line.
//                println(line)
                listener?.onLineReaded(line)
                line = br.readLine()
                   count++
            }
            // line is not visible here.
            print("--------------------has read $count -------------------------")

        }
    }

}