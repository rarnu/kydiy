import com.rarnu.opendiy.*
import com.rarnu.opendiy.util.DrawUtil
import org.junit.Test
import java.io.File

class TestMonsterCard {

    @Test
    fun testReadNormalMonsterCard() {
        val card = YGOCard.loadCard("./card_normal_monster.json")
        DrawUtil.drawCard(card, YPJapanese, "test_normal_monster.png")
    }

    @Test
    fun testWriteNormalMonsterCard() {
        YGOCard().apply {
            cardType = CardType.Monster
            monsterType = MonsterType.Normal
            monsterAttribute = MonsterAttribute.Dark
            level = 3
            attack = "3000"
            defence = "?"
            cardName = "测试怪兽名称"
            monsterRace = "【天使族】"
            rareType = RareType.Red
            cardPack = "LB-01"
            cardPassword = "98761234"
            cardTerm = "TERMINAL"
            cardEffect = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !\n测试换行"
            imageData = File("./img.png").readBytes()
            saveCard(File("./card_normal_monster.json"))
        }
    }

}