package polestar.lapuente.crypto

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import okhttp3.*
import java.io.IOException


class SettingsActivity : Activity() {

    private val client = OkHttpClient()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_settings)

        val handler = Handler()
        val delay = 10000 // 1000 milliseconds == 1 second

val activity = this;
        handler.postDelayed(object : Runnable {
            override fun run() {
                println("myHandler: here!") // Do your work here
                //https://api.blockchain.com/v3/#/unauthenticated/getTickerBySymbol
                run("https://api.blockchain.com/v3/exchange/tickers/BTC-USD", activity)

                handler.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())


        Toast.makeText(this, R.string.hello, Toast.LENGTH_LONG).show()
        //finish()

    }


    fun run(url: String, activity: Activity) {

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response)
            {
                println(response.body()?.string());

                val result = response.body()?.string()

                //val answer = JSONObject(result)

                activity.runOnUiThread {
                    Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
                }

            }

        })


    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

     fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}