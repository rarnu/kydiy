@file:Suppress("DuplicatedCode")

package com.rarnu.opendiy

enum class CardType { Monster, Magic, Trap }
enum class MonsterType { Normal, Effect, Ritual, Fusion, Synchro, Xyz, Link, Token }
enum class PendulumType { None, Normal, Effect, Ritual, Fusion, Synchro, Xyz }
enum class MagicType { Normal, Continuous, Equip, QuickPlay, Field, Ritual }
enum class TrapType { Normal, Continuous, Counter }
enum class MonsterAttribute { God, Light, Dark, Wind, Fire, Water, Earth }
enum class LicType { lt1, lt2, lt3, lt4, lt5 }
enum class RareType { Black, White, Silver, Gold, Red }
enum class FaceType { None, PR }
enum class LinkPosition { lp1, lp2, lp3, lp4, lp6, lp7, lp8, lp9 }
typealias LinkPositions = MutableSet<LinkPosition>

data class YGOCard(

    // 卡片的 Profile
    var cardProfile: String = "Chinese-Default",

    // 卡片基本类型
    var cardType: CardType = CardType.Monster,

    // 卡片名称
    var cardName: String = "",

    // 所在卡包
    var cardPack: String = "",

    // 卡片版权文字
    var cardCopyright: String = "©高桥和希 スタジオ·ダイス/集英社",

    // 卡片密码
    var cardPassword: String = "",

    // 终端
    var cardTerm: String = "",

    // 卡片效果（描述）
    var cardEffect: String = "",

    // 卡图名称
    var cardImage: String = "",

    // 卡图内容字节
    var imageData: ByteArray = byteArrayOf(),

    // 是否有日语注音
    var isCommented: Boolean = false,

    // 是否卡图自动缩放至合适的大小（不按原比例拉伸）
    var isStretch: Boolean = false,

    // 右下角防伪标记的样式
    var licType: LicType = LicType.lt1,

    // 卡片名称的样式
    var rareType: RareType = RareType.Black,

    // 卡片是否卡图面闪
    var faceType: FaceType = FaceType.None,

    // 魔法卡类型(cardType 为 Magic 时生效)
    var magicType: MagicType = MagicType.Normal,

    // 陷阱卡类型(cardType 为 Trap 时生效)
    var trapType: TrapType = TrapType.Normal,

    // 怪兽卡类型(cardType 为 Monster 时生效)
    var monsterType: MonsterType = MonsterType.Normal,

    // 怪兽属性
    var monsterAttribute: MonsterAttribute = MonsterAttribute.God,

    // 怪兽种族
    var monsterRace: String = "",

    // 怪兽的攻击力
    var attack: String = "",

    // 怪兽的防御力
    var defence: String = "",

    // 怪兽等级
    var level: Int = 0,

    // 怪兽阶级(monsterType 为 xyz 时生效)
    var rank: Int = 0,

    // 怪兽的链接方向(monsterType 为 Link 时生效)
    var linkPositions: LinkPositions = mutableSetOf(),

    // 怪兽的链接值(monsterType 为 Link 时生效)
    var linkValue: Int = 0,

    // 灵摆类型(为 none 时表示非灵摆卡片)
    var pendulumType: PendulumType = PendulumType.None,

    // 怪兽的灵摆效果(pendulumType 不为 none 时生效)
    var pendulumEffect: String = "",

    // 怪兽的左灵摆刻度(pendulumType 不为 none 时生效)
    var pendulumLeft: Int = 0,

    // 怪兽的右灵摆刻度(pendulumType 不为 none 时生效)
    var pendulumRight: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YGOCard

        if (cardType != other.cardType) return false
        if (cardName != other.cardName) return false
        if (cardPack != other.cardPack) return false
        if (cardCopyright != other.cardCopyright) return false
        if (cardPassword != other.cardPassword) return false
        if (cardTerm != other.cardTerm) return false
        if (cardEffect != other.cardEffect) return false
        if (cardImage != other.cardImage) return false
        if (!imageData.contentEquals(other.imageData)) return false
        if (isCommented != other.isCommented) return false
        if (isStretch != other.isStretch) return false
        if (licType != other.licType) return false
        if (rareType != other.rareType) return false
        if (faceType != other.faceType) return false
        if (magicType != other.magicType) return false
        if (trapType != other.trapType) return false
        if (monsterType != other.monsterType) return false
        if (monsterAttribute != other.monsterAttribute) return false
        if (monsterRace != other.monsterRace) return false
        if (attack != other.attack) return false
        if (defence != other.defence) return false
        if (level != other.level) return false
        if (rank != other.rank) return false
        if (linkPositions != other.linkPositions) return false
        if (linkValue != other.linkValue) return false
        if (pendulumType != other.pendulumType) return false
        if (pendulumEffect != other.pendulumEffect) return false
        if (pendulumLeft != other.pendulumLeft) return false
        if (pendulumRight != other.pendulumRight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cardType.hashCode()
        result = 31 * result + cardName.hashCode()
        result = 31 * result + cardPack.hashCode()
        result = 31 * result + cardCopyright.hashCode()
        result = 31 * result + cardPassword.hashCode()
        result = 31 * result + cardTerm.hashCode()
        result = 31 * result + cardEffect.hashCode()
        result = 31 * result + cardImage.hashCode()
        result = 31 * result + imageData.contentHashCode()
        result = 31 * result + isCommented.hashCode()
        result = 31 * result + isStretch.hashCode()
        result = 31 * result + licType.hashCode()
        result = 31 * result + rareType.hashCode()
        result = 31 * result + faceType.hashCode()
        result = 31 * result + magicType.hashCode()
        result = 31 * result + trapType.hashCode()
        result = 31 * result + monsterType.hashCode()
        result = 31 * result + monsterAttribute.hashCode()
        result = 31 * result + monsterRace.hashCode()
        result = 31 * result + attack.hashCode()
        result = 31 * result + defence.hashCode()
        result = 31 * result + level
        result = 31 * result + rank
        result = 31 * result + linkPositions.hashCode()
        result = 31 * result + linkValue
        result = 31 * result + pendulumType.hashCode()
        result = 31 * result + pendulumEffect.hashCode()
        result = 31 * result + pendulumLeft
        result = 31 * result + pendulumRight
        return result
    }

    companion object
}
