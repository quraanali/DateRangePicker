package com.quraanali.mydaterangepickerexample


import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.quraanali.daterangepicker.customviews.CalendarListener
import com.quraanali.daterangepicker.customviews.DateRangeCalendarView
import com.quraanali.daterangepicker.customviews.printDate
import java.util.*

class MainActivity : Activity() {
    private var calendar: DateRangeCalendarView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar = findViewById(R.id.cdrvCalendar)
        val typeface = Typeface.createFromAsset(assets, "JosefinSans-Regular.ttf")
        calendar?.setFonts(typeface)
        calendar?.setCalendarListener(calendarListener)
        findViewById<View>(R.id.btnReset).setOnClickListener { v: View? -> calendar?.resetAllSelectedViews() }

//        calendar.setNavLeftImage(ContextCompat.getDrawable(this,R.drawable.ic_left));
//        calendar.setNavRightImage(ContextCompat.getDrawable(this,R.drawable.ic_right));
        val startMonth = Calendar.getInstance()
        startMonth[2019, Calendar.DECEMBER] = 20
        val endMonth = startMonth.clone() as Calendar
        endMonth.add(Calendar.MONTH, 5)
        Log.d(
            TAG,
            "Start month: " + startMonth.time.toString() + " :: End month: " + endMonth.time.toString()
        )
        calendar?.setVisibleMonthRange(startMonth, endMonth)
        val startDateSelectable = startMonth.clone() as Calendar
        startDateSelectable.add(Calendar.DATE, 20)
        val endDateSelectable = endMonth.clone() as Calendar
        endDateSelectable.add(Calendar.DATE, -20)
        Log.d(
            TAG,
            "startDateSelectable: " + startDateSelectable.time.toString() + " :: endDateSelectable: " + endDateSelectable.time.toString()
        )
        calendar?.setSelectableDateRange(startDateSelectable, endDateSelectable)
        val startSelectedDate = startDateSelectable.clone() as Calendar
        startSelectedDate.add(Calendar.DATE, 10)
        val endSelectedDate = endDateSelectable.clone() as Calendar
        endSelectedDate.add(Calendar.DATE, -10)
        Log.d(
            TAG,
            "startSelectedDate: " + startSelectedDate.time.toString() + " :: endSelectedDate: " + endSelectedDate.time.toString()
        )
        calendar?.setSelectedDateRange(startSelectedDate, endSelectedDate)
        val current = startMonth.clone() as Calendar
        current.add(Calendar.MONTH, 1)
        calendar?.setCurrentMonth(current)
        //        calendar.setFixedDaysSelection(2);
    }

    private val calendarListener: CalendarListener = object : CalendarListener {
        override fun onFirstDateSelected(startDate: Calendar) {
            Toast.makeText(
                this@MainActivity,
                "Start Date: " + startDate.time.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d(
                TAG, "Selected dates: Start: " + printDate(
                    calendar!!.startDate
                ) +
                        " End:" + printDate(calendar!!.endDate)
            )
        }

        override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
            Toast.makeText(
                this@MainActivity,
                "Start Date: " + startDate.time.toString() + " End date: " + endDate.time.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d(
                TAG, "Selected dates: Start: " + printDate(
                    calendar!!.startDate
                ) +
                        " End:" + printDate(calendar!!.endDate)
            )
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}