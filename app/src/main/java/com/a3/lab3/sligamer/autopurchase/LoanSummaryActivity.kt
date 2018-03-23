package com.a3.lab3.sligamer.autopurchase


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

/**
 * Created by Justin Freres on 2/21/2018.
 * Model for LoanSummary Activity
 * Lab 3-3 Auto Purchase Application
 * Plugin Support with kotlin_version = '1.2.21'
 */

class LoanSummaryActivity  : AppCompatActivity(){

    lateinit var monthlyPayET: TextView
    lateinit var loanReportETLeft: TextView
    lateinit var loanReportETRight: TextView
    lateinit var loanReportETFooter: TextView
    lateinit var goDataEntryBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loansummary_layout)

        monthlyPayET = findViewById(R.id.textView2)
        loanReportETLeft = findViewById(R.id.textView3)
        loanReportETRight = findViewById(R.id.textView6)
        loanReportETFooter = findViewById(R.id.textView7)
        goDataEntryBtn = findViewById(R.id.button)

        // READ PASSING DATA
        val monthlyPay:String = intent.getStringExtra("MonthlyPayment")
        val reportLeft:String = intent.getStringExtra("LoanReportLeft")
        val reportRight:String = intent.getStringExtra("LoanReportRight")
        val reportFooter:String = intent.getStringExtra("LoanReportFooter")

        monthlyPayET.text = monthlyPay
        loanReportETLeft.text = reportLeft
        loanReportETRight.text = reportRight
        loanReportETFooter.text = reportFooter

        goDataEntryBtn.setOnClickListener {
            finish()
        }
    }
}

