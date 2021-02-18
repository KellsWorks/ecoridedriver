package app.ecoride_agent.customs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView
import androidx.cardview.widget.CardView
import app.ecoride_agent.R


class CustomProgressDialog {

    lateinit var dialog: CustomDialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog, null)
        val mTitle = view.findViewById<TextView>(R.id.cp_title)
        if (title != null) {
            mTitle.text = title
        }
        val mCardView = view.findViewById<CardView>(R.id.cp_cardview)
        mCardView.setCardBackgroundColor(Color.parseColor("#70000000"))

        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    @Suppress("DEPRECATION")
    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {

            window?.decorView?.rootView?.setBackgroundResource(R.color.dialogBackground)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                    insets.consumeSystemWindowInsets()
                }
            }
        }
    }
}