package nccstory.ml.androidapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.music_activity.*

class music_activity: AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_activity)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView5.loadAd(adRequest)


        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.adsunit)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }




        mp = MediaPlayer.create(this, R.raw.ncc)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )



        Thread(Runnable {
            while (mp!= null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }
    @SuppressLint("HandlerLeak")
    var handler = object :android.os.Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var currentPosition = msg.what
            // Update positionBar
            positionBar.progress = currentPosition
            // Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"
        }

    }
    fun createTimeLabel(time:Int):String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60
        timeLabel = "$min"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }


    fun playBtnClick(v: View) {

        if (mp.isPlaying) {
            // Stop
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.ic_play)

        } else {
            // Start
            mp.start()
            playBtn.setBackgroundResource(R.drawable.ic_pause)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        if (mp.isPlaying) {
            // Stop
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.ic_play)

        } else {
            // Start
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.ic_pause)
        }
    }
}
