package dog.snow.androidrecruittest

import android.content.Intent
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
            // This method will be executed once the timer is over
            // Start your app main activity

            val reader = JsonReader(InputStreamReader(URL("https://jsonplaceholder.typicode.com/photos?_limit=4").openStream(), "UTF-8"))
            reader.beginArray()
            while(reader.hasNext()) {
                reader.beginObject()
                var raw = RawPhoto(reader.nextInt(),
                    reader.nextInt(),
                    reader.nextString(),
                    reader.nextString(),
                    reader.nextString())
                reader.endObject()
                Log.v("testy",raw.title)
            }


            progressbar.hide()
            startActivity(Intent(this,MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
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