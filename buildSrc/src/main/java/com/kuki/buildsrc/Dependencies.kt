package com.kuki.buildsrc

object Modules {
    const val APP = ":app"
    const val DATA = ":data"
    private val CORE = ":core"
    val CORE_PRESENTATION = "$CORE:presentation"
    val CORE_DATA = "$CORE:data"
    val CORE_DOMAIN = "$CORE:domain"
    private val FEATURES = ":features"
    val FEATURE_1 = "$FEATURES:feature1"
    val CONTACTS = "$FEATURES:contacts"
    val CONTACTS_DETAIL = "$FEATURES:contactdetail"
}

object SDK {
    const val COMPILE_SDK = 35
    const val TARGET_SDK = 35
    const val MIN_SDK = 24
}