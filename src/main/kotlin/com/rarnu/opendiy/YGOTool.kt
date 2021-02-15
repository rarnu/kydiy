package com.rarnu.opendiy

import com.google.gson.Gson
import com.rarnu.opendiy.util.DrawUtil
import java.io.File

fun YGOCard.Companion.loadCard(path: String): YGOCard = loadCard(File(path))
fun YGOCard.Companion.loadCard(file: File): YGOCard = Gson().fromJson(file.readText(), YGOCard::class.java)
fun YGOCard.saveCard(path: String) = saveCard(File(path))
fun YGOCard.saveCard(file: File) = file.writeText(Gson().toJson(this))
fun YGOCard.drawCard(profile: Profile, outputFile: File) = DrawUtil.drawCard(this, profile, outputFile)
fun YGOCard.drawCard(profile: Profile, outputPath: String) = DrawUtil.drawCard(this, profile, outputPath)

fun Profile.Companion.load(path: String): Profile = load(File(path))
fun Profile.Companion.load(file: File): Profile = Gson().fromJson(file.readText(), Profile::class.java)
fun Profile.save(path: String) = save(File(path))
fun Profile.save(file: File) = file.writeText(Gson().toJson(this))