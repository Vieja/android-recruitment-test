package dog.snow.androidrecruittest

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.JsonReader
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.repository.model.RawPhoto
import kotlinx.android.synthetic.main.layout_progressbar.*
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.URL


class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    private val SPLASH_TIME_OUT:Long = 6000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        progressbar.show()

        Handler().postDelayed({

            mTask().execute()

        }, SPLASH_TIME_OUT)
    }

    private inner class mTask : AsyncTask<Void?, Void?, Void?>() {
        var mA = MainActivity()


        override fun doInBackground(vararg arg0: Void?): Void? {
            mA.parseXML()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
            return null
        }

        protected fun onPostExecute(vararg params: Void?) {

        }


    }

    fun parse(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
}