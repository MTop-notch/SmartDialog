package com.mt.smartdialog


data class DialogModel(
    var icon: Int? = null,
    var title: String? = null,
    var message: String? = null,
    var positiveText: String? = null,
    var negativeText: String? = null,
    var position: PositionType = PositionType.CENTER
)