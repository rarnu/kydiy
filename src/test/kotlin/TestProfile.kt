
import com.rarnu.opendiy.Profile
import com.rarnu.opendiy.YPChinese
import com.rarnu.opendiy.load
import com.rarnu.opendiy.save
import org.junit.Test

class TestProfile {

    @Test
    fun testWrite() {
        YPChinese.save("./profile_chinese.json")
    }

    @Test
    fun testRead() {
        val profile = Profile.load("./profile_chinese.json")
        println(profile)
    }
}