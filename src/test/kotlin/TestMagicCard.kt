import com.rarnu.opendiy.*
import com.rarnu.opendiy.util.DrawUtil
import org.junit.Test
import java.io.File

class TestMagicCard {

    @Test
    fun testReadMagicCard() {
        val card = YGOCard.loadCard("./card_magic.json")
        DrawUtil.drawCard(card, YPJapanese, "test_magic.png")
    }

    @Test
    fun testWriteMagicCard() {
        YGOCard().apply {
            cardType = CardType.Magic
            magicType = MagicType.Ritual
            cardName = "幻竜効果"
            rareType = RareType.Gold
            cardEffect = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !\n测试换行"
            imageData = File("./img.png").readBytes()
            saveCard(File("./card_magic.json"))
        }
    }

}