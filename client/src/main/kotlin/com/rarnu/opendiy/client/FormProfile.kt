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
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.JPanel

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

    class FontBasePanel(itemName: String) : JPanel(FlowLayout(FlowLayout.LEFT)) {

        private val pName = itemName
        private lateinit var tpFontName: KTextField
        private lateinit var tpFontSize: KTextField
        private lateinit var tpX: KTextField
        private lateinit var tpY: KTextField
        private lateinit var tpWidth: KTextField
        private lateinit var tpRight: KTextField
        private lateinit var tpLineHeight: KTextField

        var fontBase: FontBase = FontBase("", 0, 0, 0, 0, 0, 0)
            get() {
                field.name = tpFontName.text
                field.size = try { tpFontSize.text.toInt() } catch (th: Throwable) { 0 }
                field.x = try { tpX.text.toInt() } catch (th: Throwable) { 0 }
                field.y = try { tpY.text.toInt() } catch (th: Throwable) { 0 }
                field.width = try { tpWidth.text.toInt() } catch (th: Throwable) { 0 }
                field.right = try { tpRight.text.toInt() } catch (th: Throwable) { 0 }
                field.lineHeight = try { tpLineHeight.text.toInt() } catch (th: Throwable) { 0 }
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

            add(label(title = pName) {
                font = Font(font.family, Font.BOLD, font.size + 2)
                preferredSize = Dimension(90, 30)
            })

            add(label(title = "字体名称") { preferredSize = Dimension(50, 30) })
            add(input { tpFontName =this; preferredSize = Dimension(100, 30) })
            add(label(title = "字体大小") { preferredSize = Dimension(50, 30) })
            add(input { tpFontSize = this; preferredSize = Dimension(50, 30) })
            add(label(title = "X坐标") { preferredSize = Dimension(35, 30) })
            add(input { tpX = this; preferredSize = Dimension(50, 30) })
            add(label(title = "Y坐标") { preferredSize = Dimension(35, 30) })
            add(input { tpY = this; preferredSize = Dimension(50, 30) })
            add(label(title = "宽度") { preferredSize = Dimension(30, 30) })
            add(input { tpWidth = this; preferredSize = Dimension(50, 30) })
            add(label(title = "右极坐标") { preferredSize = Dimension(50, 30) })
            add(input { tpRight = this; preferredSize = Dimension(50, 30) })
            add(label(title = "行高") { preferredSize = Dimension(30, 30) })
            add(input { tpLineHeight = this; preferredSize = Dimension(50, 30) })

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
                    add(FontBasePanel("卡片名称").apply { pfName = this; fontBase = profile.name })
                    add(FontBasePanel("怪兽种族").apply { pfRace = this; fontBase = profile.race })
                    add(FontBasePanel("攻击力").apply { pfAtk = this; fontBase = profile.attack })
                    add(FontBasePanel("守备力").apply { pfDef = this; fontBase = profile.defense })
                    add(FontBasePanel("基础效果").apply { pfEffect = this; fontBase = profile.effect })
                    add(FontBasePanel("魔陷效果").apply { pfMTEffect = this; fontBase = profile.mtEffect })
                    add(FontBasePanel("灵摆效果").apply { pfPEffect = this; fontBase = profile.pEffect })
                    add(FontBasePanel("基础卡包").apply { pfPack = this; fontBase = profile.pack })
                    add(FontBasePanel("灵摆卡包").apply { pfPPack = this; fontBase = profile.pPack })
                    add(FontBasePanel("链接卡包").apply { pfLPack = this; fontBase = profile.lPack })
                    add(FontBasePanel("卡片终端").apply { pfTerm = this; fontBase = profile.term })
                    add(FontBasePanel("链接值").apply { pfLink = this; fontBase = profile.link })
                    add(FontBasePanel("左刻度").apply { pfScaleL = this; fontBase = profile.scaleLeft })
                    add(FontBasePanel("右刻度").apply { pfScaleR = this; fontBase = profile.scaleRight })
                    add(FontBasePanel("版权").apply { pfCopyright = this; fontBase = profile.copyright })
                    add(FontBasePanel("密码").apply { pfPassword = this; fontBase = profile.password })
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
        profile = when(idx) {
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