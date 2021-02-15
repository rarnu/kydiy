package com.rarnu.opendiy.client

import com.isyscore.kotlin.swing.component.KTextArea
import com.isyscore.kotlin.swing.component.KTextField
import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.messageBox
import com.isyscore.kotlin.swing.runOnMainThread
import com.isyscore.kotlin.swing.showFileDialog
import com.rarnu.opendiy.*
import java.awt.*
import java.awt.event.ItemEvent
import java.io.File
import javax.swing.*
import kotlin.concurrent.thread

class FormMain : JFrame("OpenDIY") {

    private val profilePath = File(System.getProperty("user.dir"), "profile").apply { if (!exists()) mkdirs() }

    private lateinit var cardImage: JLabel

    private lateinit var cbProfile: JComboBox<String>
    private lateinit var txtCardName: KTextField
    private lateinit var cbCardType: JComboBox<String>
    private lateinit var txtPack: KTextField
    private lateinit var txtCopyright: KTextField
    private lateinit var txtPassword: KTextField
    private lateinit var txtTerm: KTextField
    private lateinit var txtEffect: KTextArea
    private lateinit var cbLicenseMark: JComboBox<String>
    private lateinit var cbRare: JComboBox<String>
    private lateinit var cbFace: JComboBox<String>
    private lateinit var btnCardImage: JButton
    private lateinit var pnlMonster: JPanel
    private lateinit var pnlMagic: JPanel
    private lateinit var pnlTrap: JPanel
    private lateinit var cbMagicType: JComboBox<String>
    private lateinit var cbTrapType: JComboBox<String>

    private lateinit var chkPendulum: JCheckBox

    private lateinit var cbAttribute: JComboBox<String>
    private lateinit var txtMonsterRace: KTextField
    private lateinit var txtAttack: KTextField
    private lateinit var txtDefense: KTextField

    private lateinit var pnlMonsterType: JPanel
    private lateinit var pnlPendulumType: JPanel
    private lateinit var cbMonsterType: JComboBox<String>
    private lateinit var cbPendulumType: JComboBox<String>
    private lateinit var cbLevel: JComboBox<Int>

    private lateinit var pnlPendulumInfo: JPanel
    private lateinit var pnlLinkInfo: JPanel

    private lateinit var cbScaleLeft: JComboBox<Int>
    private lateinit var cbScaleRight: JComboBox<Int>
    private lateinit var txtPendulumEffect: KTextArea

    private lateinit var txtLinkValue: KTextField
    private lateinit var tgLink1: JToggleButton
    private lateinit var tgLink2: JToggleButton
    private lateinit var tgLink3: JToggleButton
    private lateinit var tgLink4: JToggleButton
    private lateinit var tgLink6: JToggleButton
    private lateinit var tgLink7: JToggleButton
    private lateinit var tgLink8: JToggleButton
    private lateinit var tgLink9: JToggleButton

    private var card = YGOCard()

    init {
        contentPane = rootBorderPanel {
            toolbar(position = BorderLayout.NORTH) {
                preferredSize = Dimension(0, 30)
                background = Color.WHITE
                this.isFloatable = false
                button(title = "新建") { addActionListener { newCard() } }
                button(title = "打开") { addActionListener { openCard() } }
                button(title = "保存") { addActionListener { saveCard() } }
                addSeparator()
                button(title = "刷新预览") { addActionListener { previewCard() } }
                button(title = "导出卡图") { addActionListener { exportCardImage() } }
                addSeparator()
                button(title = "界面参数设置") {
                    addActionListener {
                        FormProfile(this@FormMain)
                        runOnMainThread {
                            cbProfile.model = DefaultComboBoxModel(getProfileList().toTypedArray())
                            cbProfile.selectedItem = card.cardProfile
                        }
                    }
                }
                borderPanel {
                    button(title = "关于", position = BorderLayout.EAST) {
                        addActionListener {
                            this@FormMain.messageBox("关于", "OpenDIY\nhttp://github.com/rarnu/kydiy")
                        }
                    }
                }
            }
            borderPanel {
                clearPanel(position = BorderLayout.WEST) {
                    preferredSize = Dimension(600, 0)
                    cardImage = label {
                        setBounds(0, 0, 595, 867)
                    }
                }
                scroller(position = BorderLayout.CENTER) {
                    vertPanel {
                        borderPanel {
                            label(title = "卡片配置", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            cbProfile = combobox(array = getProfileList().toTypedArray()) {
                                background = Color.WHITE
                                preferredSize = Dimension(0, 30)
                            }
                        }
                        borderPanel {
                            label(title = "卡片名称", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            txtCardName = input { preferredSize = Dimension(0, 30) }
                        }
                        borderPanel {
                            label(title = "卡片类型", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            cbCardType = combobox(array = CardType.values().map { "$it" }.toTypedArray()) {
                                background = Color.WHITE
                                preferredSize = Dimension(0, 30)
                                addItemListener { e ->
                                    if (e.stateChange == ItemEvent.SELECTED) {
                                        val ct = CardType.valueOf(e.item as String)
                                        pnlMonster.isVisible = ct == CardType.Monster
                                        pnlMagic.isVisible = ct == CardType.Magic
                                        pnlTrap.isVisible = ct == CardType.Trap
                                    }
                                }
                            }
                        }

                        borderPanel {
                            label(title = "所在卡包", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            txtPack = input { preferredSize = Dimension(0, 30) }
                        }
                        borderPanel {
                            label(title = "卡片版权", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            txtCopyright = input(text = "©高桥和希 スタジオ·ダイス/集英社") { preferredSize = Dimension(0, 30) }
                        }
                        borderPanel {
                            label(title = "卡片密码", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            txtPassword = input { preferredSize = Dimension(0, 30) }
                        }
                        borderPanel {
                            label(title = "卡片终端", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            txtTerm = input { preferredSize = Dimension(0, 30) }
                        }
                        borderPanel {
                            label(title = "防伪标签", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            cbLicenseMark = combobox(array = LicType.values().map { "$it" }.toTypedArray()) {
                                background = Color.WHITE
                                preferredSize = Dimension(0, 30)
                            }
                        }
                        borderPanel {
                            label(title = "卡名颜色", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            cbRare = combobox(array = RareType.values().map { "$it" }.toTypedArray()) {
                                background = Color.WHITE
                                preferredSize = Dimension(0, 30)
                            }
                        }
                        borderPanel {
                            label(title = "卡面爆闪", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            cbFace = combobox(array = FaceType.values().map { "$it" }.toTypedArray()) {
                                background = Color.WHITE
                                preferredSize = Dimension(0, 30)
                            }
                        }
                        borderPanel {
                            borderPanel(position = BorderLayout.WEST) {
                                preferredSize = Dimension(80, 30)
                                label(title = "卡片效果", position = BorderLayout.NORTH) { preferredSize = Dimension(80, 30) }
                            }
                            scroller {
                                preferredSize = Dimension(0, 100)
                                txtEffect = textArea { }
                            }
                        }
                        borderPanel {
                            label(title = "卡片图案", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                            btnCardImage = button(title = "选择图片", position = BorderLayout.CENTER) {
                                addActionListener {
                                    showFileDialog(arrayOf()) { f ->
                                        card.imageData = f.readBytes()
                                        previewCard()
                                    }
                                }
                            }
                        }

                        pnlMonster = vertPanel {
                            isVisible = true
                            // 怪兽卡，这个最复杂了
                            borderPanel {
                                label(title = "是否灵摆", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                chkPendulum = checkbox {
                                    background = Color.WHITE
                                    Dimension(0, 30)
                                    addActionListener {
                                        pnlMonsterType.isVisible = !chkPendulum.isSelected
                                        pnlPendulumType.isVisible = chkPendulum.isSelected
                                        pnlPendulumInfo.isVisible = chkPendulum.isSelected
                                        if (chkPendulum.isSelected) pnlLinkInfo.isVisible = false
                                        if (!chkPendulum.isSelected) {
                                            val mt = MonsterType.valueOf(cbMonsterType.selectedItem as String)
                                            pnlLinkInfo.isVisible = mt == MonsterType.Link
                                        }
                                    }
                                }
                            }
                            borderPanel {
                                label(title = "怪兽属性", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbAttribute = combobox(array = MonsterAttribute.values().map { "$it" }.toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                }
                            }
                            borderPanel {
                                label(title = "怪兽种族", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                txtMonsterRace = input { preferredSize = Dimension(0, 30) }
                            }
                            borderPanel {
                                label(title = "攻击力", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                txtAttack = input { preferredSize = Dimension(0, 30) }
                            }
                            borderPanel {
                                label(title = "守备力", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                txtDefense = input { preferredSize = Dimension(0, 30) }
                            }
                            borderPanel {
                                label(title = "等级阶级", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbLevel = combobox(array = (0..12).toList().toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                }
                            }
                            pnlMonsterType = borderPanel {
                                label(title = "怪兽类型", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbMonsterType = combobox(array = MonsterType.values().map { "$it" }.toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                    addItemListener { e ->
                                        if (e.stateChange == ItemEvent.SELECTED) {
                                            val mt = MonsterType.valueOf(e.item as String)
                                            pnlLinkInfo.isVisible = mt == MonsterType.Link
                                        }
                                    }
                                }
                            }
                            pnlPendulumType = borderPanel {
                                isVisible = false
                                label(title = "灵摆类型", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbPendulumType = combobox(array = PendulumType.values().map { "$it" }.toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                }
                            }

                            pnlPendulumInfo = vertPanel {
                                isVisible = false
                                borderPanel {
                                    label(title = "左侧刻度", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                    cbScaleLeft = combobox(array = (0..13).toList().toTypedArray()) {
                                        background = Color.WHITE
                                        preferredSize = Dimension(0, 30)
                                    }
                                }
                                borderPanel {
                                    label(title = "右侧刻度", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                    cbScaleRight = combobox(array = (0..13).toList().toTypedArray()) {
                                        background = Color.WHITE
                                        preferredSize = Dimension(0, 30)
                                    }
                                }
                                borderPanel {
                                    borderPanel(position = BorderLayout.WEST) {
                                        preferredSize = Dimension(80, 30)
                                        label(title = "灵摆效果", position = BorderLayout.NORTH) { preferredSize = Dimension(80, 30) }
                                    }
                                    scroller {
                                        preferredSize = Dimension(0, 100)
                                        txtPendulumEffect = textArea { }
                                    }
                                }
                            }

                            pnlLinkInfo = vertPanel {
                                isVisible = false
                                borderPanel {
                                    label(title = "链接值", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                    txtLinkValue = input { preferredSize = Dimension(0, 30) }
                                }
                                borderPanel {
                                    borderPanel(position = BorderLayout.WEST) {
                                        preferredSize = Dimension(80, 30)
                                        label(title = "链接方向", position = BorderLayout.NORTH) { preferredSize = Dimension(80, 30) }
                                    }
                                    clearPanel {
                                        preferredSize = Dimension(36 * 3, 36 * 3)
                                        tgLink7 = toggle(title = "7") {
                                            setBounds(0, 0, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink8 = toggle(title = "8") {
                                            setBounds(36, 0, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink9 = toggle(title = "9") {
                                            setBounds(72, 0, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink4 = toggle(title = "4") {
                                            setBounds(0, 36, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink6 = toggle(title = "6") {
                                            setBounds(72, 36, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink1 = toggle(title = "1") {
                                            setBounds(0, 72, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink2 = toggle(title = "2") {
                                            setBounds(36, 72, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                        tgLink3 = toggle(title = "3") {
                                            setBounds(72, 72, 36, 36)
                                            margin = Insets(0, 0, 0, 0)
                                        }
                                    }
                                }
                            }
                        }

                        pnlMagic = vertPanel {
                            isVisible = false
                            borderPanel {
                                label(title = "魔法类型", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbMagicType = combobox(array = MagicType.values().map { "$it" }.toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                }
                            }
                        }

                        pnlTrap = vertPanel {
                            isVisible = false
                            borderPanel {
                                label(title = "陷阱类型", position = BorderLayout.WEST) { preferredSize = Dimension(80, 30) }
                                cbTrapType = combobox(array = MagicType.values().map { "$it" }.toTypedArray()) {
                                    background = Color.WHITE
                                    preferredSize = Dimension(0, 30)
                                }
                            }
                        }

                    }
                }
            }

        }

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1190, 930)
        isResizable = false
        setLocationRelativeTo(null)
        isVisible = true

        previewCard()
    }

    private fun newCard() {
        card = YGOCard()
        card.cardProfile = "Default-Chinese"
        syncCardToUI()
    }

    private fun openCard() {
        showFileDialog(arrayOf(), isSave = false) { f ->
            card = YGOCard.loadCard(f)
            syncCardToUI()
        }
    }

    private fun syncUIToCard() {
        card.cardProfile = cbProfile.selectedItem as String
        card.cardName = txtCardName.text
        card.cardType = CardType.valueOf(cbCardType.selectedItem as String)
        card.cardPack = txtPack.text
        card.cardCopyright = txtCopyright.text
        card.cardPassword = txtPassword.text
        card.cardTerm = txtTerm.text
        card.cardEffect = txtEffect.text
        card.licType = LicType.valueOf(cbLicenseMark.selectedItem as String)
        card.rareType = RareType.valueOf(cbRare.selectedItem as String)
        card.faceType = FaceType.valueOf(cbFace.selectedItem as String)
        card.magicType = MagicType.valueOf(cbMagicType.selectedItem as String)
        card.trapType = TrapType.valueOf(cbTrapType.selectedItem as String)

        card.monsterAttribute = MonsterAttribute.valueOf(cbAttribute.selectedItem as String)
        var race = txtMonsterRace.text
        if (!race.startsWith("【")) race = "【$race"
        if (!race.endsWith("】")) race = "$race】"
        race = race.replace("/", "／")
        card.monsterRace = race
        card.attack = txtAttack.text
        card.defence = txtDefense.text

        card.monsterType = MonsterType.valueOf(cbMonsterType.selectedItem as String)
        card.pendulumType = PendulumType.valueOf(cbPendulumType.selectedItem as String)
        card.level = cbLevel.selectedIndex
        card.rank = cbLevel.selectedIndex
        card.pendulumLeft = cbScaleLeft.selectedIndex
        card.pendulumRight = cbScaleRight.selectedIndex
        card.pendulumEffect = txtPendulumEffect.text
        card.linkValue = try {
            txtLinkValue.text.toInt()
        } catch (th: Throwable) {
            0
        }
        card.linkPositions.clear()
        if (tgLink1.isSelected) card.linkPositions.add(LinkPosition.lp1)
        if (tgLink2.isSelected) card.linkPositions.add(LinkPosition.lp2)
        if (tgLink3.isSelected) card.linkPositions.add(LinkPosition.lp3)
        if (tgLink4.isSelected) card.linkPositions.add(LinkPosition.lp4)
        if (tgLink6.isSelected) card.linkPositions.add(LinkPosition.lp6)
        if (tgLink7.isSelected) card.linkPositions.add(LinkPosition.lp7)
        if (tgLink8.isSelected) card.linkPositions.add(LinkPosition.lp8)
        if (tgLink9.isSelected) card.linkPositions.add(LinkPosition.lp9)

    }

    private fun syncCardToUI() {
        cbProfile.selectedItem = card.cardProfile
        txtCardName.text = card.cardName
        cbCardType.selectedItem = "${card.cardType}"
        txtPack.text = card.cardPack
        txtCopyright.text = card.cardCopyright
        txtPassword.text = card.cardPassword
        txtTerm.text = card.cardTerm
        txtEffect.text = card.cardEffect
        cbLicenseMark.selectedItem = "${card.licType}"
        cbRare.selectedItem = "${card.rareType}"
        cbFace.selectedItem = "${card.faceType}"
        cbMagicType.selectedItem = "${card.magicType}"
        cbTrapType.selectedItem = "${card.trapType}"
        chkPendulum.isSelected = card.pendulumType != PendulumType.None
        cbAttribute.selectedItem = "${card.monsterAttribute}"
        txtMonsterRace.text = card.monsterRace
        txtAttack.text = card.attack
        txtDefense.text = card.defence
        cbMonsterType.selectedItem = "${card.monsterType}"
        cbPendulumType.selectedItem = "${card.pendulumType}"
        cbLevel.selectedIndex = card.level
        cbScaleLeft.selectedIndex = card.pendulumLeft
        cbScaleRight.selectedIndex = card.pendulumRight
        txtPendulumEffect.text = card.pendulumEffect
        txtLinkValue.text = card.linkValue.toString()

        tgLink1.isSelected = card.linkPositions.contains(LinkPosition.lp1)
        tgLink2.isSelected = card.linkPositions.contains(LinkPosition.lp2)
        tgLink3.isSelected = card.linkPositions.contains(LinkPosition.lp3)
        tgLink4.isSelected = card.linkPositions.contains(LinkPosition.lp4)
        tgLink6.isSelected = card.linkPositions.contains(LinkPosition.lp6)
        tgLink7.isSelected = card.linkPositions.contains(LinkPosition.lp7)
        tgLink8.isSelected = card.linkPositions.contains(LinkPosition.lp8)
        tgLink9.isSelected = card.linkPositions.contains(LinkPosition.lp9)

        previewCard()
    }

    private fun saveCard() {
        showFileDialog(arrayOf(), isSave = true) { f ->
            syncUIToCard()
            card.saveCard(f)
        }
    }

    private fun previewCard() {
        syncUIToCard()
        thread {
            val fPreview = File("./preview.png")
            val profile = when (cbProfile.selectedIndex) {
                0 -> YPChinese
                1 -> YPJapanese
                else -> Profile.load(File(profilePath, cbProfile.selectedItem as String))
            }
            card.drawCard(profile, fPreview)
            runOnMainThread {
                cardImage.icon = ImageIcon(ImageIcon(fPreview.toURI().toURL()).image.getScaledInstance(595, 867, Image.SCALE_DEFAULT))
            }
        }
    }

    private fun exportCardImage() {
        showFileDialog(arrayOf(), isSave = true) { f ->
            val profile = when (cbProfile.selectedIndex) {
                0 -> YPChinese
                1 -> YPJapanese
                else -> Profile.load(File(profilePath, cbProfile.selectedItem as String))
            }
            card.drawCard(profile, f)
        }
    }

    private fun getProfileList(): List<String> = mutableListOf("Default-Chinese", "Default-Japanese").apply {
        addAll(profilePath.list()?.filter { !it.startsWith(".") } ?: listOf())
    }
}