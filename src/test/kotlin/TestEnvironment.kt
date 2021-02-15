import com.rarnu.opendiy.YGOEnvironment
import org.junit.Test

class TestEnvironment {

    @Test
    fun test() {
        val installed = YGOEnvironment.getFontsInstalled()
        println("all fonts => $installed")
    }

}