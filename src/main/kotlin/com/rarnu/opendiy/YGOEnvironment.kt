package com.rarnu.opendiy

import com.rarnu.opendiy.YGOProfile.FONT_ATK_DEF
import com.rarnu.opendiy.YGOProfile.FONT_CARD_INFO
import com.rarnu.opendiy.YGOProfile.FONT_CHINESE_BASE
import com.rarnu.opendiy.YGOProfile.FONT_COPYRIGHT_VALUE
import com.rarnu.opendiy.YGOProfile.FONT_JAPANESE_BASE
import com.rarnu.opendiy.YGOProfile.FONT_LINK_VALUE
import com.rarnu.opendiy.YGOProfile.FONT_TERM_VALUE
import java.awt.GraphicsEnvironment

object YGOEnvironment {

    private val fontsList = arrayOf(
        FONT_CHINESE_BASE,
        FONT_JAPANESE_BASE,
        FONT_ATK_DEF,
        FONT_LINK_VALUE,
        FONT_CARD_INFO,
        FONT_TERM_VALUE,
        FONT_COPYRIGHT_VALUE
    )

    /* 是否所有的字体都安装了 */
    fun getFontsInstalled(): Boolean {
        val installedFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames.toSet()
        return (installedFonts + fontsList).toSet().size == installedFonts.size
    }
}