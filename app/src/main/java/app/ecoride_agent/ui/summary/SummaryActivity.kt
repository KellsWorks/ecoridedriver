package app.ecoride_agent.ui.summary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var summaryBinding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        summaryBinding = DataBindingUtil.setContentView(this, R.layout.activity_summary)

    }

    override fun onStart() {
        super.onStart()

        summaryBinding.summaryBack.setOnClickListener {
            this@SummaryActivity.onBackPressed()
        }

    }
}