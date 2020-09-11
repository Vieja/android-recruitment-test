package dog.snow.androidrecruittest

import android.R.id
import android.os.Bundle
import android.util.JsonReader
import android.util.JsonToken
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dog.snow.androidrecruittest.repository.model.RawPhoto
import java.io.InputStreamReader
import java.net.URL


class MainActivity : AppCompatActivity(R.layout.main_activity){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    fun parseXML() {
        val reader = JsonReader(InputStreamReader(URL("https://jsonplaceholder.typicode.com/photos?_limit=4").openStream(), "UTF-8"))
        reader.beginArray()
        while(reader.hasNext()) {
            reader.beginObject()
            var photo = RawPhoto()
            while (reader.hasNext()) {
                val name = reader.nextName()
                if (name == "id") {
                    photo.id=reader.nextInt()
                } else if (name == "albumId") {
                    photo.albumId=reader.nextInt()
                } else if (name == "title" ) {
                    photo.title=reader.nextString()
                } else if (name == "url") {
                    photo.url=reader.nextString()
                } else  if (name == "thumbnailUrl") {
                    photo.thumbnailUrl=reader.nextString()
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject()
            Log.v("testy",photo.title)
        }
    }
}