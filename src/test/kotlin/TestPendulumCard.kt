import com.rarnu.opendiy.*
import com.rarnu.opendiy.util.DrawUtil
import org.junit.Test
import java.io.File

class TestPendulumCard {

    @Test
    fun testReadNormalPendulumCard() {
        val card = YGOCard.loadCard("./card_normal_pendulum.json")
        DrawUtil.drawCard(card, YPChinese, "test_normal_pendulum.png")
    }

    @Test
    fun testWriteNormalPendulumCard() {
        YGOCard().apply {
            cardType = CardType.Monster
            monsterType = MonsterType.Normal
            pendulumType = PendulumType.Normal
            monsterAttribute = MonsterAttribute.Dark
            level = 3
            attack = "3000"
            defence = "2500"
            cardName = "测试怪兽名称"
            monsterRace = "【天使族】"
            rareType = RareType.Black
            cardPack = "LB-01"
            cardPassword = "98761234"
            cardTerm = "TERMINAL"
            pendulumLeft = 2
            pendulumRight = 10
            cardEffect = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !\n测试换行"
            pendulumEffect = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !\n" +
                    "测试换行"
            imageData = File("./img.png").readBytes()
            saveCard(File("./card_normal_pendulum.json"))
        }
    }

}