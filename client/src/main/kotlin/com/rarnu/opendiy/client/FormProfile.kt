@file:Suppress("DuplicatedCode")

package com.rarnu.opendiy.client

import com.isyscore.kotlin.swing.component.KTextField
import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.errorMessageBox
import com.isyscore.kotlin.swing.runOnMainThread
import com.isyscore.kotlin.swing.showInputDialog
import com.rarnu.opendiy.*
import java.awt.*
import java.io.File
import javax.swing.*

class FormProfile(frame: JFrame) : JDialog(frame, "界面参数设置", true) {

    private val profilePath = File(System.getProperty("user.dir"), "profile").apply { if (!exists()) mkdirs() }
    private val profileList = mutableListOf<String>()
    private lateinit var listProfile: JList<String>
    private lateinit var pfName: FontBasePanel
    private lateinit var pfRace: FontBasePanel
    private lateinit var pfAtk: FontBasePanel
    private lateinit var pfDef: FontBasePanel
    private lateinit var pfEffect: FontBasePanel
    private lateinit var pfMTEffect: FontBasePanel
    private lateinit var pfPEffect: FontBasePanel
    private lateinit var pfPack: FontBasePanel
    private lateinit var pfPPack: FontBasePanel
    private lateinit var pfLPack: FontBasePanel
    private lateinit var pfTerm: FontBasePanel
    private lateinit var pfLink: FontBasePanel
    private lateinit var pfScaleL: FontBasePanel
    private lateinit var pfScaleR: FontBasePanel
    private lateinit var pfCopyright: FontBasePanel
    private lateinit var pfPassword: FontBasePanel

    private var profile = Profile()

    class FontBasePanel(itemName: String?) : JPanel(FlowLayout(FlowLayout.LEFT)) {
        private val tpFontName: KTextField
        private val tpFontSize: KTextField
        private val tpX: KTextField
        private val tpY: KTextField
        private val tpWidth: KTextField
        private val tpRight: KTextField
        private val tpLineHeight: KTextField

        var fontBase: FontBase = FontBase("", 0, 0, 0, 0, 0, 0)
            get() {
                field.name = tpFontName.text
                field.size = try {
                    tpFontSize.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                field.x = try {
                    tpX.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                field.y = try {
                    tpY.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                field.width = try {
                    tpWidth.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                field.right = try {
                    tpRight.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                field.lineHeight = try {
                    tpLineHeight.text.toInt()
                } catch (th: Throwable) {
                    0
                }
                return field
            }
            set(value) {
                field = value
                tpFontName.text = field.name
                tpFontSize.text = field.size.toString()
                tpX.text = field.x.toString()
                tpY.text = field.y.toString()
                tpWidth.text = field.width.toString()
                tpRight.text = field.right.toString()
                tpLineHeight.text = field.lineHeight.toString()
            }

        init {
            background = Color.WHITE
            preferredSize = Dimension(0, 30)

            label(title = itemName) {
                font = Font(font.family, Font.BOLD, font.size + 2)
                preferredSize = Dimension(90, 30)
            }
            label(title = "字体名称") { preferredSize = Dimension(50, 30) }
            tpFontName = input { preferredSize = Dimension(100, 30) }
            label(title = "字体大小") { preferredSize = Dimension(50, 30) }
            tpFontSize = input { preferredSize = Dimension(50, 30) }
            label(title = "X坐标") { preferredSize = Dimension(35, 30) }
            tpX = input { preferredSize = Dimension(50, 30) }
            label(title = "Y坐标") { preferredSize = Dimension(35, 30) }
            tpY = input { preferredSize = Dimension(50, 30) }
            label(title = "宽度") { preferredSize = Dimension(30, 30) }
            tpWidth = input { preferredSize = Dimension(50, 30) }
            label(title = "右极坐标") { preferredSize = Dimension(50, 30) }
            tpRight = input { preferredSize = Dimension(50, 30) }
            label(title = "行高") { preferredSize = Dimension(30, 30) }
            tpLineHeight = input { preferredSize = Dimension(50, 30) }

        }
    }

    init {
        contentPane = rootBorderPanel {
            borderPanel(position = BorderLayout.WEST) {
                preferredSize = Dimension(200, 0)
                scroller(position = BorderLayout.CENTER) {
                    listProfile = list {
                        addListSelectionListener { e -> if (!e.valueIsAdjusting) loadProfile() }
                    }
                }
                toolbar(position = BorderLayout.SOUTH) {
                    preferredSize = Dimension(200, 30)
                    background = Color.WHITE
                    this.isFloatable = false
                    button(title = "+") {
                        preferredSize = Dimension(30, 30)
                        margin = Insets(0, 4, 0, 4)
                        addActionListener { addProfile() }
                    }
                    button(title = "-") {
                        preferredSize = Dimension(30, 30)
                        margin = Insets(0, 4, 0, 4)
                        addActionListener { removeProfile() }
                    }
                }
            }
            scroller(position = BorderLayout.CENTER) {
                vertPanel {
                    pfName = custom(position = null, "卡片名称") { fontBase = profile.name }
                    pfRace = custom(position = null, "怪兽种族") { fontBase = profile.race }
                    pfAtk = custom(position = null, "攻击力") { fontBase = profile.attack }
                    pfDef = custom(position = null, "守备力") { fontBase = profile.defense }
                    pfEffect = custom(position = null, "基础效果") { fontBase = profile.effect }
                    pfMTEffect = custom(position = null, "魔陷效果") { fontBase = profile.mtEffect }
                    pfPEffect = custom(position = null, "灵摆效果") { fontBase = profile.pEffect }
                    pfPack = custom(position = null, "基础卡包") { fontBase = profile.pack }
                    pfPPack = custom(position = null, "灵摆卡包") { fontBase = profile.pPack }
                    pfLPack = custom(position = null, "链接卡包") { fontBase = profile.lPack }
                    pfTerm = custom(position = null, "卡片终端") { fontBase = profile.term }
                    pfLink = custom(position = null, "链接值") { fontBase = profile.link }
                    pfScaleL = custom(position = null, "左刻度") { fontBase = profile.scaleLeft }
                    pfScaleR = custom(position = null, "右刻度") { fontBase = profile.scaleRight }
                    pfCopyright = custom(position = null, "版权") { fontBase = profile.copyright }
                    pfPassword = custom(position = null, "密码") { fontBase = profile.password }

                    clearPanel {
                        preferredSize = Dimension(0, 40)
                        button(title = "保存") {
                            setBounds(90, 8, 200, 30)
                            addActionListener { saveProfile() }
                        }
                    }
                }
            }
        }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(1080, 650)
        isResizable = false
        setLocationRelativeTo(null)
        refreshProfileList()
        isVisible = true

    }

    private fun refreshProfileList() {
        profileList.clear()
        profileList.add("Default-Chinese")
        profileList.add("Default-Japanese")
        profileList.addAll(profilePath.list()?.filter { !it.startsWith(".") } ?: listOf())
        listProfile.setListData(profileList.toTypedArray())
        runOnMainThread { listProfile.updateUI() }
    }


    private fun addProfile() {
        showInputDialog("输入配置文件名称", "") { n ->
            if (profileList.contains(n)) {
                errorMessageBox("错误", "配置文件名称已存在")
                return@showInputDialog
            }
            YPChinese.save(File(profilePath, n))
            refreshProfileList()
            listProfile.selectedIndex = profileList.indexOf(n)
        }
    }

    private fun removeProfile() {
        if (listProfile.selectedIndex == -1) return
        if (listProfile.selectedIndex < 2) {
            errorMessageBox("错误", "不能删除默认的配置文件")
            return
        }

        val n = profileList[listProfile.selectedIndex]
        File(profilePath, n).delete()
        refreshProfileList()
    }

    private fun loadProfile() {
        val idx = listProfile.selectedIndex
        profile = when (idx) {
            -1 -> Profile()
            0 -> YPChinese
            1 -> YPJapanese
            else -> Profile.load(File(profilePath, profileList[idx]))
        }
        pfName.fontBase = profile.name
        pfRace.fontBase = profile.race
        pfAtk.fontBase = profile.attack
        pfDef.fontBase = profile.defense
        pfEffect.fontBase = profile.effect
        pfMTEffect.fontBase = profile.mtEffect
        pfPEffect.fontBase = profile.pEffect
        pfPack.fontBase = profile.pack
        pfPPack.fontBase = profile.pPack
        pfLPack.fontBase = profile.lPack
        pfTerm.fontBase = profile.term
        pfLink.fontBase = profile.link
        pfScaleL.fontBase = profile.scaleLeft
        pfScaleR.fontBase = profile.scaleRight
        pfCopyright.fontBase = profile.copyright
        pfPassword.fontBase = profile.password

    }

    private fun saveProfile() {
        val idx = listProfile.selectedIndex
        if (idx == -1) return
        if (idx < 2) {
            errorMessageBox("错误", "不能覆盖系统默认的配置文件")
            return
        }
        val f = File(profilePath, profileList[idx])
        profile.name = pfName.fontBase
        profile.race = pfRace.fontBase
        profile.attack = pfAtk.fontBase
        profile.defense = pfDef.fontBase
        profile.effect = pfEffect.fontBase
        profile.mtEffect = pfMTEffect.fontBase
        profile.pEffect = pfPEffect.fontBase
        profile.pack = pfPack.fontBase
        profile.pPack = pfPPack.fontBase
        profile.lPack = pfLPack.fontBase
        profile.term = pfTerm.fontBase
        profile.link = pfLink.fontBase
        profile.scaleLeft = pfScaleL.fontBase
        profile.scaleRight = pfScaleR.fontBase
        profile.copyright = pfCopyright.fontBase
        profile.password = pfPassword.fontBase
        profile.save(f)
    }
}