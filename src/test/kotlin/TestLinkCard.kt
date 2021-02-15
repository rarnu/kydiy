import com.rarnu.opendiy.*
import com.rarnu.opendiy.util.DrawUtil
import org.junit.Test
import java.io.File

class TestLinkCard {

    @Test
    fun testReadLinkCard() {
        val card = YGOCard.loadCard("./card_link.json")
        card.drawCard(YPChinese, "test_link.png")
    }

    @Test
    fun testWriteLinkCard() {
        YGOCard().apply {
            cardType = CardType.Monster
            monsterType = MonsterType.Link
            monsterAttribute = MonsterAttribute.Dark
            attack = "3000"
            linkValue = 3
            linkPositions = mutableSetOf(LinkPosition.lp1, LinkPosition.lp2, LinkPosition.lp3)
            cardName = "测试怪兽名称"
            monsterRace = "【天使族】"
            rareType = RareType.Red
            faceType = FaceType.PR
            cardPack = "LB-01"
            cardPassword = "98761234"
            cardTerm = "TERMINAL"
            cardEffect = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !\n测试换行"
            imageData = File("./img.png").readBytes()
            saveCard(File("./card_link.json"))
        }
    }
}