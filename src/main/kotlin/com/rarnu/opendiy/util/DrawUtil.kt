@file:Suppress("DuplicatedCode")

package com.rarnu.opendiy.util

import com.rarnu.opendiy.*
import com.rarnu.opendiy.image.ImageResource.board
import com.rarnu.opendiy.image.ImageResource.cardBackground
import com.rarnu.opendiy.image.ImageResource.levelImage
import com.rarnu.opendiy.image.ImageResource.licImage
import com.rarnu.opendiy.image.ImageResource.linkTypeImage
import com.rarnu.opendiy.image.ImageResource.magicTypeImage
import com.rarnu.opendiy.image.ImageResource.monsterAttributeImage
import com.rarnu.opendiy.image.ImageResource.monsterTypeImage
import com.rarnu.opendiy.image.ImageResource.parallelRare
import com.rarnu.opendiy.image.ImageResource.pendulumParallelRare
import com.rarnu.opendiy.image.ImageResource.pendulumTypeImage
import com.rarnu.opendiy.image.ImageResource.rankImage
import com.rarnu.opendiy.image.ImageResource.trapTypeImage
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import sun.font.FontDesignMetrics
import java.awt.*
import java.io.File
import java.io.FileOutputStream
import javax.imageio.ImageIO
import kotlin.math.ceil

object DrawUtil {

    fun drawCard(c: YGOCard, profile: Profile, output: String) = drawCard(c, profile, File(output))

    fun drawCard(c: YGOCard, profile: Profile, output: File) {

        val bg = ImageIO.read(board)

        val g = bg.createGraphics()

        // 绘制叠加图层
        g.composite = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f)

        val img = ImageIO.read(ByteInputStream(c.imageData, c.imageData.size))
        if (c.pendulumType == PendulumType.None) {
            // 不是灵摆卡，卡图大小
            g.drawImage(img, 265, 620, 2107 - 265, 2452 - 620, null)
            if (c.faceType == FaceType.PR) {
                val maskImg = ImageIO.read(parallelRare)
                g.drawImage(maskImg, 0, 0, maskImg.width, maskImg.height, null)
            }
        } else {
            // 是灵摆卡，卡图大小
            g.drawImage(img, 142, 604, 2236 - 142, 2160 - 604, null)
            if (c.faceType == FaceType.PR) {
                val maskImg = ImageIO.read(pendulumParallelRare)
                g.drawImage(maskImg, 0, 0, maskImg.width, maskImg.height, null)
            }
        }

        when (c.cardType) {
            CardType.Monster -> {
                // 怪兽背景，按是否灵摆区分
                val mbg = if (c.pendulumType == PendulumType.None) ImageIO.read(monsterTypeImage[c.monsterType]) else ImageIO.read(pendulumTypeImage[c.pendulumType])
                g.drawImage(mbg, 0, 0, mbg.width, mbg.height, null)
                val attr = ImageIO.read(monsterAttributeImage[c.monsterAttribute])
                g.drawImage(attr, 0, 0, attr.width, attr.height, null)
                when (c.monsterType) {
                    MonsterType.Link -> {
                        // link 怪兽没有等级，但是需要绘制链接箭头
                        c.linkPositions.forEach { arr ->
                            val lmImg = ImageIO.read(linkTypeImage[arr])
                            g.drawImage(lmImg, 0, 0, lmImg.width, lmImg.height, null)
                        }
                    }
                    MonsterType.Xyz -> {
                        val rank = ImageIO.read(rankImage[c.rank])
                        g.drawImage(rank, 0, 0, rank.width, rank.height, null)
                    }
                    else -> {
                        val level = ImageIO.read(levelImage[c.level])
                        g.drawImage(level, 0, 0, level.width, level.height, null)
                    }
                }
            }
            CardType.Magic -> {
                val mbg = ImageIO.read(cardBackground[CardType.Magic])
                g.drawImage(mbg, 0, 0, mbg.width, mbg.height, null)
                val magicTypeImg = ImageIO.read(magicTypeImage[c.magicType])
                g.drawImage(magicTypeImg, 0, 0, magicTypeImg.width, magicTypeImg.height, null)
            }
            CardType.Trap -> {
                val mbg = ImageIO.read(cardBackground[CardType.Trap])
                g.drawImage(mbg, 0, 0, mbg.width, mbg.height, null)
                val trapTypeImg = ImageIO.read(trapTypeImage[c.trapType])
                g.drawImage(trapTypeImg, 0, 0, trapTypeImg.width, trapTypeImg.height, null)
            }
        }

        val licImg = ImageIO.read(licImage[c.licType])
        g.drawImage(licImg, 0, 0, licImg.width, licImg.height, null)

        g.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER)

        // 绘制卡片名称
        g.color = when (c.rareType) {
            RareType.Black -> Color.BLACK
            RareType.White -> Color.WHITE
            RareType.Silver -> Color(0xC0, 0xC0, 0xC0)
            RareType.Gold -> Color(0xFF, 0xD7, 0x00)
            RareType.Red -> Color.RED
        }
        g.font = Font(profile.name.name, Font.PLAIN, profile.name.size)
        val metrics = FontDesignMetrics.getMetrics(g.font)
        g.drawString(c.cardName, profile.name.x, profile.name.y + metrics.ascent)

        // 绘制卡片效果
        g.color = Color.BLACK

        if (c.cardType == CardType.Magic || c.cardType == CardType.Trap) {
            g.font = Font(profile.mtEffect.name, Font.PLAIN, profile.mtEffect.size)
            drawEffectText(g, c.cardEffect, profile.mtEffect.x, profile.mtEffect.y, profile.mtEffect.width, profile.mtEffect.lineHeight)
        } else {
            // 绘制种族
            g.font = Font(profile.race.name, Font.PLAIN, profile.race.size)
            drawText(g, c.monsterRace, profile.race.x, profile.race.y)
            // 绘制怪兽效果
            g.font = Font(profile.effect.name, Font.PLAIN, profile.effect.size)
            drawEffectText(g, c.cardEffect, profile.effect.x, profile.effect.y, profile.effect.width, profile.effect.lineHeight)

            if (c.pendulumType != PendulumType.None) {
                // 是灵摆怪兽
                g.font = Font(profile.pEffect.name, Font.PLAIN, profile.pEffect.size)
                drawEffectText(g, c.pendulumEffect, profile.pEffect.x, profile.pEffect.y, profile.pEffect.width, profile.pEffect.lineHeight)
            }
        }

        // 绘制攻击力守备力
        g.font = Font(profile.attack.name, Font.PLAIN, profile.attack.size)
        drawText(g, c.attack, profile.attack.x, profile.attack.y)

        if (c.monsterType == MonsterType.Link) {
            // 是 link 怪兽
            g.font = Font(profile.link.name, Font.PLAIN, profile.link.size)
            drawText(g, c.linkValue.toString(), profile.link.x, profile.link.y)
        } else {
            // 其他怪兽
            g.font = Font(profile.defense.name, Font.PLAIN, profile.defense.size)
            drawText(g, c.defence, profile.defense.x, profile.defense.y)
        }

        // 绘制密码，终端，卡包，版权
        g.font = Font(profile.copyright.name, Font.PLAIN, profile.copyright.size)
        drawTextRight(g, c.cardCopyright, profile.copyright.y, profile.copyright.right)
        g.font = Font(profile.password.name, Font.PLAIN, profile.password.size)
        drawText(g, c.cardPassword, profile.password.x, profile.password.y)

        if (c.pendulumType == PendulumType.None && c.monsterType != MonsterType.Link) {
            // 灵摆和链接怪兽不绘制普通卡包和终端信息
            g.font = Font(profile.pack.name, Font.PLAIN, profile.pack.size)
            drawTextRight(g, c.cardPack, profile.pack.y, profile.pack.right)
            g.font = Font(profile.term.name, Font.PLAIN, profile.term.size)
            drawText(g, c.cardTerm, profile.term.x, profile.term.y)
        }
        if (c.monsterType == MonsterType.Link) {
            // 链接怪兽绘制链接专用的卡包信息
            g.font = Font(profile.lPack.name, Font.PLAIN, profile.lPack.size)
            drawTextRight(g, c.cardPack, profile.lPack.y, profile.lPack.right)
        }
        if (c.pendulumType != PendulumType.None) {
            // 灵摆怪兽绘制灵摆专用的卡包信息
            g.font = Font(profile.pPack.name, Font.PLAIN, profile.pPack.size)
            drawText(g, c. cardPack, profile.pPack.x, profile.pPack.y)
            // 灵摆怪兽绘制左右刻度
            g.font = Font(profile.scaleLeft.name, Font.PLAIN, profile.scaleLeft.size)
            var scaleLeftX = profile.scaleLeft.x
            if (c.pendulumLeft.toString().length == 2) scaleLeftX -= profile.scaleLeft.size / 4
            drawText(g, c.pendulumLeft.toString(), scaleLeftX, profile.scaleLeft.y)
            g.font = Font(profile.scaleRight.name, Font.PLAIN, profile.scaleRight.size)
            var scaleRightX = profile.scaleRight.x
            if (c.pendulumRight.toString().length == 2) scaleRightX -= profile.scaleRight.size / 4
            drawText(g, c.pendulumRight.toString(), scaleRightX, profile.scaleRight.y)
        }

        g.dispose()

        ImageIO.write(bg, "png", FileOutputStream(output))
    }

    private fun drawText(g: Graphics2D, text: String, x: Int, y: Int) {
        val metrics = FontDesignMetrics.getMetrics(g.font)
        g.drawString(text, x, y + metrics.ascent)
    }

    private fun drawTextRight(g: Graphics2D, text: String, y: Int, right: Int) {
        // 绘制右侧对齐的文字
        val frc = g.fontRenderContext
        val stringBounds = g.font.getStringBounds(text, frc)
        val fontWidth = stringBounds.width
        val metrics = FontDesignMetrics.getMetrics(g.font)
        g.drawString(text, (right - fontWidth).toInt(), y + metrics.ascent)
    }

    private fun drawEffectText(g: Graphics2D, text: String, x: Int, y: Int, width: Int, lineHeight: Int) {
        val textLines = text.split("\n")
        val lineList = mutableListOf<String>()
        val frc = g.fontRenderContext
        textLines.forEach { lineText ->
            val stringBounds = g.font.getStringBounds(lineText, frc)
            val fontWidth = stringBounds.width
            val w = width - x
            if (fontWidth <= w) {
                lineList.add(lineText)
            } else {
                val bs = fontWidth / w
                val lineCharCount = ceil(lineText.length / bs).toInt()
                var beginIndex = 0
                while (beginIndex < lineText.length) {
                    var endIndex = beginIndex + lineCharCount
                    if (endIndex >= lineText.length) {
                        endIndex = lineText.length
                    }
                    var lineStr = lineText.substring(beginIndex, endIndex)
                    var tempBounds = g.font.getStringBounds(lineStr, frc)
                    var exTime = 0
                    val exChar = 1
                    while (tempBounds.width > w) {
                        lineStr = lineStr.substring(0, lineStr.length - exChar)
                        tempBounds = g.font.getStringBounds(lineStr, frc)
                        exTime++
                    }
                    lineList.add(lineStr)
                    beginIndex += lineStr.length
                }
            }
        }

        val metrics = FontDesignMetrics.getMetrics(g.font)
        for (i in lineList.indices) {
            g.drawString(lineList[i], x, y + metrics.ascent + (lineHeight * i))
        }
    }
}