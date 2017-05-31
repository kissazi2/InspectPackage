package record

import org.jf.util.TextUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
import java.io.BufferedWriter
import java.io.FileWriter
import javax.xml.soap.Text


/***
 * 过滤重复的文件行
 */
class FileFilter(fileName : String){
    var fileName = fileName
    fun filter(): Unit {
        var file = File(this.fileName)
        BufferedReader(FileReader(file)).use { br ->
            var count = 1
            var line = br.readLine()
            val linkedList = LinkedList<String>()
            linkedList.add(line)
            while (line != null) {
                // process the line.
                line = br.readLine()
                if (!line.isNullOrEmpty() && !linkedList.contains(line)){
                    linkedList.add(line)
                }
                count++
            }
            file.delete()
            file.createNewFile()
            val fstream = FileWriter(fileName, true)
            val out = BufferedWriter(fstream)
            for (c in linkedList) {
                if (!c.isNullOrEmpty()) {
                    out.write(c)
                    out.newLine();
                    out.flush()
                }
            }
            //Close the output stream
            out.close()

            // line is not visible here.
            print("--------------------has read $count -------------------------")

        }
    }
}
