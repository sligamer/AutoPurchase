package com.a3.lab3.sligamer.autopurchase

/**
 * Created by Justin Freres on 2/21/2018.
 * Data Model for LoanSummary/Purchase Activity
 * Lab 3-3 Auto Purchase Application
 * Plugin Support with kotlin_version = '1.2.21'
 */
class Auto {

    // DECLARE STATIC VARIABLES
    companion object {
        const val STATE_TAX = 20
        const val INTEREST_TAX = .09
    }

    // DECLARE PRIVATE VARIABLES
    private var mPrice: Double? = null
    private var mDownPayment: Double = 0.0
    private var mLoanTerm: Int? = null

    // SET PRICE METHOD
    fun setPrice(price: Double?) {
        mPrice = price
    }

    // GET PRICE METHOD
    fun getPrice(): Double? {
        return mPrice
    }

    // SET DOWN PAYMENT METHOD
    fun setDownPayment(down: Double) {
        mDownPayment = down
    }

    // GET DOWN PAYMENT METHOD
    fun getDownPayment(): Double? {
        return mDownPayment
    }

    // SET LOAN TERMS METHOD
    fun setLoanTerm(term: String) {

        mLoanTerm = when {
            term.contains("2") -> 2
            term.contains("3") -> 3
            else -> 4
        }
    }

    // GET LOAN TERMS METHOD
    fun getLoanTerm(): Int? {
        return mLoanTerm
    }

    // CALC TAX AMOUNT METHOD
    fun taxAmount(): Double {
        return mPrice!! * STATE_TAX
    }

    // CALC TOTAL COST
    fun totalCost(): Double?{
        return mPrice!! + taxAmount()
    }


    // CALC BORROWED AMOUNT
    fun borrowedAmount(): Double?{
        return totalCost()!! - mDownPayment
    }


    // CALC INTEREST AMOUNT
    fun interestAmount(): Double?{
        return borrowedAmount()!! * INTEREST_TAX
    }


    // CALC MONThLY PAYMENT
    fun montlyPayment(): Double?{
        return borrowedAmount()!! / (mLoanTerm!! * 12)
    }

}







