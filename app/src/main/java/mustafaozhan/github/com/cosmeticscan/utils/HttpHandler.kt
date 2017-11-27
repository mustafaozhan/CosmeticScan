package mustafaozhan.github.com.cosmeticscan.utils


import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

/**
* Created by Mustafa Özhan on 6/29/17 at 9:10 AM on Arch Linux.
*/
class HttpHandler {

    fun makeServiceCall(reqUrl: String): String {
        var response: String? = null
        try {
            val url = URL(reqUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            // read the response
            val `in` = BufferedInputStream(conn.inputStream)
            response = convertStreamToString(`in`)
        } catch (e: MalformedURLException) {
            Log.e(TAG, "MalformedURLException: " + e.message)
        } catch (e: ProtocolException) {
            Log.e(TAG, "ProtocolException: " + e.message)
        } catch (e: IOException) {
            Log.e(TAG, "IOException: " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception: " + e.message)
        }

        return response!!
    }

    private fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()


        try {
            var line = reader.readLine()
            if (line != null)
                do {
                    sb.append(line)
                    line = reader.readLine()
                } while (line != null)


        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return sb.toString()
    }

    companion object {

        private val TAG = HttpHandler::class.java.simpleName
    }
}