package app.ecoride_agent.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import app.ecoride_agent.R
import app.ecoride_agent.adapters.PagerAdapter
import app.ecoride_agent.databinding.ActivityOnBoardingBinding
import app.ecoride_agent.helpers.SharedHelper
import app.ecoride_agent.ui.auth.AuthenticationActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var onBoardingBinding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBoardingBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)

        setupViewPager()

        if (SharedHelper.getKey(this, "isFirstTime") == "yes"){
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            SharedHelper.putKey(this, "isFirstTime", "yes")
        }



    }

    private fun setupViewPager() {

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstSlider() , " One ")
        adapter.addFragment(SecondSlider() , " Two ")
        adapter.addFragment(ThirdSlider() , " Three ")


        onBoardingBinding.onBoardPager.adapter = adapter

        onBoardingBinding.dotsIndicator.setViewPager(onBoardingBinding.onBoardPager)
        onBoardingBinding.onBoardPager.setCurrentItem(0, true)

        if (onBoardingBinding.onBoardPager.currentItem == 0){
            onBoardingBinding.previousSlider.visibility = View.GONE
            onBoardingBinding.nextSlider.visibility = View.VISIBLE
            onBoardingBinding.nextSlider.setOnClickListener {
                onBoardingBinding.onBoardPager.setCurrentItem(1, true)
            }
        }

        onBoardingBinding.onBoardPager.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        onBoardingBinding.nextSlider.setImageResource(R.drawable.ic_baseline_arrow_forward)
                        onBoardingBinding.previousSlider.visibility = View.GONE
                        onBoardingBinding.nextSlider.visibility = View.VISIBLE
                        onBoardingBinding.nextSlider.setOnClickListener {
                            onBoardingBinding.onBoardPager.setCurrentItem(1, true)
                        }
                    }
                    1->{
                        onBoardingBinding.nextSlider.setImageResource(R.drawable.ic_baseline_arrow_forward)
                        onBoardingBinding.previousSlider.visibility = View.VISIBLE
                        onBoardingBinding.nextSlider.visibility = View.VISIBLE
                        onBoardingBinding.nextSlider.setOnClickListener {
                            onBoardingBinding.onBoardPager.setCurrentItem(2, true)
                        }
                        onBoardingBinding.previousSlider.setOnClickListener {
                            onBoardingBinding.onBoardPager.setCurrentItem(0, true)
                        }
                    }
                    2->{
                        onBoardingBinding.nextSlider.setImageResource(R.drawable.ic_baseline_login_24)
                        onBoardingBinding.previousSlider.setOnClickListener {
                            onBoardingBinding.onBoardPager.setCurrentItem(1, true)
                        }
                        onBoardingBinding.nextSlider.setOnClickListener {
                            startActivity(
                                Intent(
                                    this@OnBoardingActivity, AuthenticationActivity::class.java
                                )
                            )
                        }
                    }
                }
            }
        })


    }
}