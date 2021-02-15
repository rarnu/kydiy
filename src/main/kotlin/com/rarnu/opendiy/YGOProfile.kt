package com.rarnu.opendiy

data class FontBase(var name: String, var size: Int, var x: Int = 0, var y: Int = 0, var width: Int = 0, var right: Int = 0, var lineHeight: Int = 0)

object YGOProfile {

    const val FONT_CHINESE_BASE = "YGODIY-Chinese"
    const val FONT_JAPANESE_BASE = "YGODIY-JP"
    const val FONT_ATK_DEF = "MatrixBoldSmallCaps"
    const val FONT_LINK_VALUE = "EurostileCandyW01-Semibold"
    const val FONT_CARD_INFO = "ITC Stone Serif"
    const val FONT_TERM_VALUE = "BankGothic Md BT"
    const val FONT_COPYRIGHT_VALUE = "Yugioh Copyright"

    object Chinese {
        var cardName = FontBase(FONT_CHINESE_BASE, 160, 160, 186)
        var magicTrapEffect = FontBase(FONT_CHINESE_BASE, 64, 154, 2622, width = 2210, lineHeight = 64)
        var effect = FontBase(FONT_CHINESE_BASE, 64, 154, 2702, width = 2210, lineHeight = 64)
        var pendulumEffect = FontBase(FONT_CHINESE_BASE, 58, 350, 2200, width = 2020, lineHeight = 58)
        var race = FontBase(FONT_CHINESE_BASE, 76, 114, 2622)
    }

    object Japanese {
        var cardName = FontBase(FONT_JAPANESE_BASE, 160, 160, 186)
        var magicTrapEffect = FontBase(FONT_JAPANESE_BASE, 64, 154, 2622, width = 2210, lineHeight = 64)
        var effect = FontBase(FONT_JAPANESE_BASE, 64, 154, 2702, width = 2210, lineHeight = 64)
        var pendulumEffect = FontBase(FONT_JAPANESE_BASE, 58, 350, 2200, width = 2020, lineHeight = 58)
        var race = FontBase(FONT_JAPANESE_BASE, 76, 142, 2622)
    }

    var attack = FontBase(FONT_ATK_DEF, 95, 1490, 3182)
    var defense = FontBase(FONT_ATK_DEF, 95, 1980, 3182)
    var scaleLeft = FontBase(FONT_ATK_DEF, 150, 192, 2390)
    var scaleRight = FontBase(FONT_ATK_DEF, 150, 2110, 2390)
    var linkValue = FontBase(FONT_LINK_VALUE, 92, 2111, 3170)

    var password = FontBase(FONT_CARD_INFO, 72, 90, 3326)
    var pendulumPack = FontBase(FONT_CARD_INFO, 65, 190, 3195)
    var term = FontBase(FONT_TERM_VALUE, 80, 240, 2490)
    var pack = FontBase(FONT_CARD_INFO, 65, y = 2504, right = 2130)
    var copyright = FontBase(FONT_COPYRIGHT_VALUE, 54, y = 3323, right = 2170)
    var linkPack = FontBase(FONT_CARD_INFO, 65, y = 2504, right = 1950)
}

data class Profile(
    /* 卡片名称 */
    var name: FontBase = YGOProfile.Chinese.cardName,
    /* 魔法陷阱的效果 */
    var mtEffect: FontBase = YGOProfile.Chinese.magicTrapEffect,
    /* 卡片主效果 */
    var effect: FontBase = YGOProfile.Chinese.effect,
    /* 灵摆效果 */
    var pEffect: FontBase = YGOProfile.Chinese.pendulumEffect,
    /* 怪兽种族 */
    var race: FontBase = YGOProfile.Chinese.race,
    /* 攻击力 */
    var attack: FontBase = YGOProfile.attack,
    /* 守备力 */
    var defense: FontBase = YGOProfile.defense,
    /* 链接值 */
    var link: FontBase = YGOProfile.linkValue,
    /* 左刻度 */
    var scaleLeft: FontBase = YGOProfile.scaleLeft,
    /* 右刻度 */
    var scaleRight: FontBase = YGOProfile.scaleRight,

    /* 密码 */
    var password: FontBase = YGOProfile.password,
    /* 终端 */
    var term: FontBase = YGOProfile.term,
    /* 版权 */
    var copyright: FontBase = YGOProfile.copyright,

    /* 卡包 */
    var pack: FontBase = YGOProfile.pack,
    /* 灵摆卡包 */
    var pPack: FontBase = YGOProfile.pendulumPack,
    /* 链接卡包 */
    var lPack: FontBase = YGOProfile.linkPack
) {
    companion object
}

/* 中文的 profile */
val YPChinese = Profile()

/* 日文的 profile */
val YPJapanese = Profile(
    name = YGOProfile.Japanese.cardName,
    mtEffect = YGOProfile.Japanese.magicTrapEffect,
    effect = YGOProfile.Japanese.effect,
    pEffect = YGOProfile.Japanese.pendulumEffect,
    race = YGOProfile.Japanese.race
)