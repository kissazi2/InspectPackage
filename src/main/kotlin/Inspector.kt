import com.google.classyshark.Shark
import java.io.File

fun main(args: Array<String>) {
    var path = File("/Users/bang/Downloads/apks/com.qiyi.video.apk")
    var shark = Shark.with(path);
    System.out.println("\n\n\n\nAll Classes " + shark.getAllClassNames().size +
            "\nAll Methods " + shark.getAllMethods().size);
}