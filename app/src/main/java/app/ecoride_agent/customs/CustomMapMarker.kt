package app.ecoride_agent.customs

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import app.ecoride_agent.R

@SuppressLint("ViewConstructor")
class CustomMapMarker(root: ViewGroup,
                      text: String?,
                      image: Int?,
                      isSelected: Boolean) : FrameLayout(root.context) {
    private var mImage: ImageView
    private var mTitle: TextView

    init {
        View.inflate(context, R.layout.marker_layout, this)
        mImage = findViewById(R.id.marker_image)
        mTitle = findViewById(R.id.marker_title)
        measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        mTitle.text = text
        if (isSelected) {
            if (image != null) {
                mImage.setImageResource(image)
            }
        } else {
            if (image != null) {
                mImage.setImageResource(image)
            }
        }
    }
}