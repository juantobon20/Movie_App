package com.appinc.movieapp

import Constants.BUILD_NUMBER
import Constants.MAJOR
import Constants.MICRO
import Constants.MINOR
import Constants.PROPERTIES_FILE
import Constants.VERSION_FILE
import java.io.File


object Constants {
    const val MAJOR = "versionMajor"
    const val MINOR = "versionMinor"
    const val MICRO = "versionMicro"
    const val BUILD_NUMBER = "buildNumber"
    const val PROPERTIES_FILE = "gradle.properties"
    const val VERSION_FILE = "version.txt"
}

data class Version(
    val versionMajor: Int,
    val versionMinor: Int,
    val versionMicro: Int,
    val buildNumber: Int
) {
    fun increaseBuildNumber() = this.copy(buildNumber = buildNumber + 1)

    fun getVersionName() = "$versionMajor.$versionMinor.$versionMicro($buildNumber)"
}

object VersionUpdated {

    fun updateVersion(version: Version) {
        println("updating version of the app to $version")
        val props = java.util.Properties()
        val propertiesFile = File(PROPERTIES_FILE)
        props.load(propertiesFile.inputStream())
        props.setProperty(MAJOR, version.versionMajor.toString())
        props.setProperty(MINOR, version.versionMinor.toString())
        props.setProperty(MICRO, version.versionMicro.toString())
        props.setProperty(BUILD_NUMBER, version.buildNumber.toString())
        props.store(propertiesFile.writer(), null)
    }

    fun getCurrentVersion(): Version {
        val props = java.util.Properties()
        val propertiesFile = File(PROPERTIES_FILE)
        props.load(propertiesFile.inputStream())
        return Version(
            versionMajor = props.getProperty(MAJOR).toInt(),
            versionMinor = props.getProperty(MINOR).toInt(),
            versionMicro = props.getProperty(MICRO).toInt(),
            buildNumber = props.getProperty(BUILD_NUMBER).toInt()
        )
    }

    fun createVersionFile(){
        val file = File(VERSION_FILE)
        file.writeText(getCurrentVersion().getVersionName())
    }
}

