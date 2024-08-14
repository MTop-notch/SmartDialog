package com.mt.smartdialog

import android.os.*
import android.util.*
import androidx.appcompat.app.*
import androidx.core.content.*
import com.mt.smartdialog.databinding.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = "ダイアログ表示"
        val body = "こちらのデザインで本当によろしいですか？"
        val ok = "OK"
        val cancel = "Cancel"
        binding.first.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
        }
        binding.second.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
                .icon(R.drawable.ic_congrts)
        }
        binding.third.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
                .icon(R.drawable.ic_congrts)
                .onPositive(ok) {
                    Log.d("TAG", "positive ")
                }
        }
        binding.fourth.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
                .icon(R.drawable.ic_congrts)
                .onPositive(ok) {
                    Log.d("TAG", "positive ")
                }
                .onNegative(cancel) {
                    Log.d("TAG", "negative ")
                }
        }
        binding.fifth.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
                .onPositive(ok) {
                    Log.d("TAG", "positive ")
                }
        }
        binding.sixth.setOnClickListener {
            SmartDialog.build(this)
                .title(title)
                .body(body)
                .onPositive(ok) {
                    Log.d("TAG", "positive ")
                }
                .onNegative(cancel) {
                    Log.d("TAG", "negative ")
                }
        }
        binding.seventh.setOnClickListener {
            SmartDialog.build(this)
                .title(
                    title,
                    titleColor = ContextCompat.getColor(this, android.R.color.white)
                )
                .body(
                    body,
                    color = ContextCompat.getColor(this, android.R.color.white)
                )
                .setBackground(R.drawable.layout_rounded_navy)
                .onPositive(
                    ok,
                    textColor = ContextCompat.getColor(this, android.R.color.white)
                ) {
                    Log.d("TAG", "positive ")
                }
                .onNegative(
                    cancel,
                    textColor = ContextCompat.getColor(this, android.R.color.white)
                ) {
                    Log.d("TAG", "negative ")
                }
        }
        binding.eighth.setOnClickListener {
            SmartDialog.build(this)
                .title(
                    title,
                    titleColor = ContextCompat.getColor(this, android.R.color.white)
                )
                .body(
                    body,
                    color = ContextCompat.getColor(this, android.R.color.white)
                )
                .icon(R.drawable.ic_congrts)
                .setBackground(R.drawable.layout_rounded_navy)
                .onPositive(
                    ok,
                    textColor = ContextCompat.getColor(this, android.R.color.white)
                ) {
                    Log.d("TAG", "positive ")
                }
        }
        binding.nine.setOnClickListener {
            SmartDialog.build(this)
                .title(
                    title,
                    titleColor = ContextCompat.getColor(this, android.R.color.white)
                )
                .body(
                    body,
                    color = ContextCompat.getColor(this, android.R.color.white)
                )
                .icon(R.drawable.ic_congrts)
                .setBackground(R.drawable.layout_rounded_navy)
                .setPosition(PositionType.CENTER)
                .onPositive(
                    ok,
                    textColor = ContextCompat.getColor(this, android.R.color.white)
                ) {
                    Log.d("TAG", "positive ")
                }
        }
        binding.ten.setOnClickListener {
            startProgress()
            Handler(Looper.getMainLooper()).postDelayed({
                endProgress()
            }, 2000)
        }
        binding.eleven.setOnClickListener {
            startProgress("Loading…")
            Handler(Looper.getMainLooper()).postDelayed({
                endProgress()
            }, 2000)
        }
    }

}