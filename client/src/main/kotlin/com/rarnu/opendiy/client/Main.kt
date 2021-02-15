package com.rarnu.opendiy.client

import com.rarnu.opendiy.YGOEnvironment
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.UIManager
import javax.swing.plaf.metal.MetalLookAndFeel

fun main(args: Array<String>) {

    UIManager.setLookAndFeel(MetalLookAndFeel())
    JFrame.setDefaultLookAndFeelDecorated(true)
    JDialog.setDefaultLookAndFeelDecorated(true)

    val installed = YGOEnvironment.getFontsInstalled()
    if (!installed) {
        JOptionPane.showMessageDialog(null, "错误", "请先安装所需的字体，然后重新启动本软件.", JOptionPane.ERROR_MESSAGE)
        return
    }

    FormMain()
}