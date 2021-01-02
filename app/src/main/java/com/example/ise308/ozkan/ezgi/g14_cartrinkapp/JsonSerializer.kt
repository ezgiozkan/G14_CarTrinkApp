package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.*


class JsonSerializer(

        private val filename : String,
        private val context: Context
) {

  @Throws(IOException::class,JSONException::class)
  fun save(cars: List<Car>)
  {

      val jArray = JSONArray()

      for (c in cars)
          jArray.put(c.convertToJson())

      var writer: Writer? = null
      try {
          val out = context.openFileOutput(filename, Context.MODE_PRIVATE)

          writer = OutputStreamWriter(out)
          writer.write(jArray.toString())
      }
      finally {
          if (writer != null) {
              writer.close()
          }
      }


  }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Car> {

        val carList = ArrayList<Car>()
        var reader: BufferedReader? = null


        try {

            val `in` = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(`in`))
            val jsonString = StringBuilder()

            for (line in reader.readLines()) {
                jsonString.append(line)
            }

            val jArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for (i in 0 until jArray.length()) {
                carList.add(Car(jArray.getJSONObject(i)))

            }


        } catch (e: FileNotFoundException) {

        } finally {
            reader!!.close()

        }

        return carList

    }


}