package com.mt.smartdialog

import android.app.*
import android.graphics.*
import android.util.*
import android.view.*
import android.view.View.*
import android.view.animation.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.smart_dialog.*


// ダイアログリスト
var dialogList = ArrayList<AlertDialog>()

class SmartDialog {

    companion object {

        private lateinit var layoutInflater: LayoutInflater

        /**
         * ダイアログ生成
         * @param activity: Activity
         * @return
         */
        fun build(activity: Activity): AlertDialog {
            layoutInflater = LayoutInflater.from(activity)
            val alertDialog =
                AlertDialog.Builder(activity, R.style.full_screen_dialog).setView(R.layout.smart_dialog)
            val alert: AlertDialog = alertDialog.create()
            // 既に表示しているダイアログがなければ表示
            if (dialogList.isEmpty()) alert.show()
            // ダイアログリストに追加
            dialogList.add(alert)
            // ダイアログを閉じた際の処理を追加
            alert.setOnDismissListener {
                // 閉じたダイアログをリストから削除
                dialogList.removeFirstOrNull()
                // 未表示のダイアログがある場合、表示
                dialogList.firstOrNull()?.show()
            }
            return alert
        }
    }

}

/**
 * くるくる開始
 * @param title: String? = null
 */
fun AppCompatActivity.startProgress(title: String? = null) {
    SmartDialog.build(this).apply {
        title?.let {
            title(it)
        }
        progress()
        setPosition(PositionType.CENTER)
    }
}

/**
 * くるくる終了
 */
fun AppCompatActivity.endProgress() {
    // くるくる終了時、画面操作を有効
    dialogList.firstOrNull()?.dismiss()
    window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

/**
 * ダイアログ表示
 * @param dialogModel: DialogModel
 */
fun AppCompatActivity.openDialog(dialogModel: DialogModel) {
    SmartDialog.build(this).apply {
        dialogModel.icon?.let { icon(it, true) }
        dialogModel.title?.let { title(it) }
        dialogModel.message?.let { body(it) }
        dialogModel.positiveText?.let { onPositive(it) }
        dialogModel.negativeText?.let { onNegative(it) }
        setPosition(dialogModel.position)
    }
}

/**
 * くるくる設定
 * @return AlertDialog
 */
fun AlertDialog.progress(): AlertDialog {
    // くるくる開始時、画面操作を無効
    this.progressBar.show()
    // 幅を変更
    val progressBarWidth = this.progressBar.layoutParams.width.toFloat()
    val titleWidth = this.title.layoutParams.width.toFloat()
    Log.d("動作確認", "progressBarWidth: $progressBarWidth")
    Log.d("動作確認", "titleWidth: $titleWidth")
    val newWidth = when (progressBarWidth < titleWidth) {
        true -> titleWidth
        else -> progressBarWidth
    }
    val layoutParams = this.mainLayout.layoutParams
    layoutParams.width = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, newWidth, context.resources.displayMetrics
    ).toInt()
    layoutParams.height = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, newWidth, context.resources.displayMetrics
    ).toInt()
    this.mainLayout.layoutParams = layoutParams
    window?.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
    return this
}

/**
 * アイコン設定
 * @param icon: Int
 * @param animateIcon: Boolean = false
 * @return AlertDialog
 */
fun AlertDialog.icon(icon: Int, animateIcon: Boolean = false): AlertDialog {
    this.image.setImageResource(icon)
    this.image.show()
    if (animateIcon) {
        val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        image.startAnimation(pulseAnimation)
    }
    return this
}

/**
 * タイトル設定
 * @param title: String
 * @param fontStyle: Typeface? = null
 * @param titleColor: Int = 0
 * @return
 */
fun AlertDialog.title(title: String, fontStyle: Typeface? = null, titleColor: Int = 0): AlertDialog {
    this.title.text = title.trim()
    fontStyle?.let {
        this.title.typeface = it
    }
    if (titleColor != 0) {
        this.title.setTextColor(titleColor)
    }
    this.title.show()
    return this
}

/**
 * メッセージ設定
 * @param body: String
 * @param fontStyle: Typeface? = null
 * @param color: Int = 0
 * @return AlertDialog
 */
fun AlertDialog.body(body: String, fontStyle: Typeface? = null, color: Int = 0): AlertDialog {
    this.subHeading.text = body.trim()
    this.subHeading.show()
    fontStyle?.let {
        this.subHeading.typeface = it
    }
    if (color != 0) {
        this.subHeading.setTextColor(color)
    }
    return this
}

/**
 * ポジティブボタン設定
 * @param text: String
 * @param bgColor: Int? = null
 * @param textColor: Int? = null
 * @param action: (() -> Unit)? = null
 * @return AlertDialog
 */
fun AlertDialog.onPositive(
    text: String, bgColor: Int? = null, textColor: Int? = null, action: (() -> Unit)? = null
): AlertDialog {
    this.positiveLineView.show()
    this.positiveBtn.show()
    bgColor?.let {
        this.positiveBtn.setBackgroundResource(it)
    }
    textColor?.let {
        this.positiveBtn.setTextColor(it)
    }
    this.positiveBtn.text = text.trim()
    this.positiveBtn.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/**
 * ネガティブボタン設定
 * @param text: String
 * @param bgColor: Int? = null
 * @param textColor: Int? = null
 * @param action: (() -> Unit)? = null
 * @return AlertDialog
 */
fun AlertDialog.onNegative(
    text: String, bgColor: Int? = null, textColor: Int? = null, action: (() -> Unit)? = null
): AlertDialog {
    this.negativeLineView.show()
    this.negativeBtn.show()
    bgColor?.let {
        this.negativeBtn.setBackgroundResource(it)
    }
    textColor?.let {
        this.negativeBtn.setTextColor(it)
    }
    this.negativeBtn.text = text.trim()
    this.negativeBtn.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/**
 * 背景設定
 * @param dialogBackgroundColor: Int? = null
 * @return AlertDialog
 */
fun AlertDialog.setBackground(dialogBackgroundColor: Int? = null): AlertDialog {
    dialogBackgroundColor?.let {
        this.mainLayout.setBackgroundResource(it)
    }
    return this
}

/**
 * 表示位置指定
 * @param position: PositionType = PositionType.BOTTOM
 * @return AlertDialog
 */
fun AlertDialog.setPosition(position: PositionType = PositionType.CENTER): AlertDialog {
    val layoutParams = mainLayout.layoutParams as? RelativeLayout.LayoutParams
    when (position) {
        PositionType.CENTER -> layoutParams?.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        PositionType.BOTTOM -> layoutParams?.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    }
    mainLayout?.layoutParams = layoutParams
    return this
}

private fun View.show() {
    this.visibility = VISIBLE
}