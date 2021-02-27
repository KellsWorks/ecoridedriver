package app.ecoride_agent.ui.summary

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.databinding.ActivitySummaryBinding
import app.ecoride_agent.helpers.SharedHelper


@SuppressLint("SetTextI18n")
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
        val slideUp: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_slow)
        summaryBinding.cardLayout.startAnimation(slideUp)
        summaryBinding.cardLayout.visibility = View.VISIBLE

        slideUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                animateTextView(0, 21, summaryBinding.ridesValue)
                animateTextView(0, 2, summaryBinding.canceledValue)
                animateTextView(0, 11, summaryBinding.scheduledValue)
                animateTextViewFloat(0.0f, 1200F, summaryBinding.revenueValue)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    private fun animateTextView(initialValue: Int, finalValue: Int, textView: TextView) {
        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
        valueAnimator.duration = 1500
        valueAnimator.addUpdateListener { _ ->
            if (textView.id == R.id.revenue_value) textView.text = SharedHelper.getKey(
                this@SummaryActivity,
                SharedHelper.CURRENCY
            ) + valueAnimator.animatedValue.toString() else textView.text =
                valueAnimator.animatedValue.toString()
        }
        valueAnimator.start()
    }


    private fun animateTextViewFloat(
        initialValue: Float, finalValue: Float,
        textView: TextView
    ) {
        val valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue)
        valueAnimator.duration = 1500
        valueAnimator.addUpdateListener { _ ->
            if (textView.id == R.id.revenue_value) textView.text = SharedHelper.getKey(
                this@SummaryActivity,
                SharedHelper.CURRENCY
            ) + valueAnimator.animatedValue.toString() else textView.text =
                valueAnimator.animatedValue.toString()
        }
        valueAnimator.start()
    }
}