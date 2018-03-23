package com.a3.lab3.sligamer.autopurchase

import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.content.res.Resources
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.*
import kotlin.system.exitProcess
import android.view.Gravity
import android.widget.Toast



/**
 * Created by Justin Freres on 2/21/2018.
 * Model for Purchase Activity
 * Lab 3-3 Auto Purchase Application
 * Plugin Support with kotlin_version = '1.2.21'
 */
class PurchaseActivity  : AppCompatActivity(){

    // THE AUTO OBJECT CONTAINS THE INFORMATION
    // ABOUT THE VEHICLE BEING PURCHASED
    private lateinit var mAuto: Auto

    // THE DATA TO BE PASSED TO THE LOAN ACTIVITY
    private lateinit var loanReportLeft: String
    private lateinit var loanReportRight: String
    private lateinit var loanReportFooter: String
    private lateinit var monthlyPayment: String

    // LAYOUT INPUT REFERENCES
    private lateinit var carPriceET: EditText
    private lateinit var downPayET: EditText
    private lateinit var loantermRG: RadioGroup
    private lateinit var loantermID: RadioButton
    private lateinit var btnEntryValidate: Button

    // Need to research why Kotlin has not integer.valueOf
    // https://stackoverflow.com/questions/47309451/string-to-double-on-android-kotlin
    private inline fun String.toDouble(): Double = java.lang.Double.parseDouble(this)


    // OVERRIDE OF ONCREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_layout)

        // TASK: ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
        carPriceET = findViewById(R.id.editText1)
        downPayET = findViewById(R.id.editText2)
        loantermRG = findViewById(R.id.radioGroup1)
        btnEntryValidate = findViewById(R.id.LoanBtn)

        mAuto = Auto()

        btnEntryValidate.setOnClickListener {

            //read value from EditText1 to a String variable
            val editText1Msg: String = carPriceET.text.toString()
            //check if the EditText1 have values or not
            if (editText1Msg.trim().isEmpty()) {
                val toast = Toast.makeText(this, "Please enter car price! ", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

            //read value from EditText2 to a String variable
            val editText2Msg: String = downPayET.text.toString()

            //check if the EditText1 have values or not
            if (editText2Msg.trim().isEmpty()) {
                val toast = Toast.makeText(this, "Please enter down payment! ", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 50)
                toast.show()
            }
            // GOOD VALIDATION NOT EMPTY
            if (editText1Msg.trim().isNotEmpty() && editText2Msg.trim().isNotEmpty()) {
                activateLoanSummary()
            }
        }
    }
    // COLLECT AUTO INPUT DATA
    fun collectAutoInputData(){

        // TASK 1: SET THE CAR PRICE
        mAuto.setPrice(carPriceET.text.toString().toDouble())

        // TASK 2: SET THE DOWN PAYMENT
        mAuto.setDownPayment(downPayET.text.toString().toDouble())

        // TASK 3: SET THE LOAN TERM
        val radioId = loantermRG.checkedRadioButtonId
        loantermID = findViewById<RadioButton>(radioId)
        mAuto.setLoanTerm(loantermID.text.toString())

    }

    fun buildLoanReport(){

        // Get Resources
        val res = baseContext.resources

        // TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        monthlyPayment = res.getString(R.string.report_line1).toString() +
                String.format("%.02f", mAuto.montlyPayment())


        // TASK 2: CONSTRUCT THE LOAN REPORT
        loanReportLeft = res.getString(R.string.report_line6).toString()
        loanReportRight = "$ " + String.format("%-10.02f", mAuto.getPrice())

        loanReportLeft += res.getString(R.string.report_line7).toString()
        loanReportRight += "\n" +"$ " + String.format("%-10.02f", mAuto.getDownPayment())

        loanReportLeft += res.getString(R.string.report_line9).toString()
        loanReportRight += "\n" +"$ " + String.format("%-10.02f", mAuto.taxAmount())

        loanReportLeft += res.getString(R.string.report_line10).toString()
        loanReportRight += "\n" +"$ " + String.format("%-10.02f", mAuto.totalCost())

        loanReportLeft += res.getString(R.string.report_line11).toString()
        loanReportRight += "\n" +"$ " + String.format("%-10.02f", mAuto.borrowedAmount())

        loanReportLeft += res.getString(R.string.report_line12).toString()
        loanReportRight += "\n" +"$ " + String.format("%-10.02f", mAuto.interestAmount())

        loanReportFooter = "\n" + res.getString(R.string.report_line8).toString() +
                " " + mAuto.getLoanTerm() + " years."

        loanReportFooter += "\n" + res.getString(R.string.report_line2).toString()

        loanReportFooter +=  res.getString(R.string.report_line3).toString()

        loanReportFooter +=  res.getString(R.string.report_line4).toString()

        loanReportFooter +=  res.getString(R.string.report_line5).toString()

    }

    fun activateLoanSummary(){
        // TASK 1: BUILD A LOAN REPORT FROM THE INPUT DATA
        collectAutoInputData()
        buildLoanReport()

        // TASK 2: CREATE AN INTENT TO DISPLAY THE LOAN SUMMARY ACTIVITY
        // PASS DATA
        val launchReport = Intent(this, LoanSummaryActivity::class.java)

        // TASK 3: PASS THE LOAN SUMMARY ACTIVITY TWO PIECES OF DATA
        //       THE LOAN REPORT CONTAINING LOAN DETAILS
        //       THE MONTHLY PAYMENT
        launchReport.putExtra("LoanReportLeft", loanReportLeft)
        launchReport.putExtra("LoanReportRight", loanReportRight)
        launchReport.putExtra("LoanReportFooter", loanReportFooter)
        launchReport.putExtra("MonthlyPayment", monthlyPayment)

        // TASK 4: START THE LOAN ACTIVITY
        startActivity(launchReport)

    }
}