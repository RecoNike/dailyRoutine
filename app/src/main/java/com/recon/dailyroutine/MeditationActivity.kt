package com.recon.dailyroutine

import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.ScrollView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MeditationActivity : AppCompatActivity() {
    private var mediaController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        val videoView = findViewById<VideoView>(R.id.videoView)
        mediaController = MediaController(this)
        mediaController?.setAnchorView(this.mediaController)
        videoView.setMediaController(mediaController)

        val horizontalScrollView: ScrollView = findViewById(R.id.scrollView)
        horizontalScrollView.smoothScrollTo(0, 0)


        val energyBtn: Button = findViewById(R.id.energyBtn)
        val relaxBt: Button = findViewById(R.id.relaxBtn)

        energyBtn.setOnClickListener{
            videoView.setVideoPath("https://rr5---sn-a5msen7l.googlevideo.com/videoplayback?expire=1703559381&ei=dOyJZbXROoyusfIPjOy6iA8&ip=191.101.61.179&id=o-ACxGxH56SsXyH3TGoq8xWc4Ig-06CcZd-G4RTzQcH8sG&itag=22&source=youtube&requiressl=yes&xpc=EgVo2aDSNQ%3D%3D&mh=n-&mm=31%2C26&mn=sn-a5msen7l%2Csn-n4v7snse&ms=au%2Conr&mv=m&mvi=5&pl=24&initcwndbps=2020000&spc=UWF9fx2v4tdVR6reoLzJX9gdFb19Qms&vprv=1&svpuc=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=640.661&lmt=1630650199080423&mt=1703537339&fvip=5&fexp=24007246&c=ANDROID&txp=5532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cxpc%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AJfQdSswRAIgIqvHxe5IutiuQf-3RG7dp1BwgbZlsZU4enTDqAQtWlsCIBy22wPBDjAQC63offz0EBpfQXzSOjTV8McoT4z8atTE&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AAO5W4owRQIhAJcCHpTwghHK14nv78DtggxdrRBefPZNdL9eHrLTURYeAiAHZt9moCsixQzAWol1rSWjYL80z-hzLTcOrATFvZl-rg%3D%3D&title=Boost%20Your%20Energy%2010%20Minute%20Guided%20Meditation")
            videoView.start()
            horizontalScrollView.smoothScrollTo(0, 300)
        }
        relaxBt.setOnClickListener{
            videoView.setVideoPath("https://rr4---sn-n4v7sney.googlevideo.com/videoplayback?expire=1703561003&ei=y_KJZZjJJb3XsfIP1bGduA8&ip=172.98.87.246&id=o-AImyoK6U1gD9oW37m2dN9e6DIqBlIwlEL4XXZkeDSuAF&itag=22&source=youtube&requiressl=yes&xpc=EgVo2aDSNQ%3D%3D&mh=pT&mm=31%2C29&mn=sn-n4v7sney%2Csn-a5msener&ms=au%2Crdu&mv=m&mvi=4&pl=24&initcwndbps=1313750&spc=UWF9f-1TRrLxJt6etEr7xIq1tCmhSeM&vprv=1&svpuc=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=2157.110&lmt=1696910225818114&mt=1703539017&fvip=2&fexp=24007246&c=ANDROID&txp=5432434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cxpc%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AJfQdSswRQIhALNc6n54movvUSruNIDAxYLxwsoCgOQQUu2yrUrUP_J2AiBTcmuhiza6-87rKPDJHQazs8MuEPJKILKSPwGWocZErA%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AAO5W4owRgIhALpTTTf1oylTlQR0hyof2RkR-MOU5gYoQwDJ-kQ7hd5ZAiEAk4m5YHxxp7D00rFBQ9lJC0PD3AchazF7EPxeuPTCvCs%3D&title=A%20Deep%20Relaxation%20Meditation%20That%20Will%20Take%20You%20to%20a%20New%20Reality")
            videoView.start()
            horizontalScrollView.smoothScrollTo(0, 300)
        }

//        val videoView = findViewById<VideoView>(R.id.videoView)
//        videoView.setVideoPath("https://youtu.be/kp_4OjtvH2k")
//        videoView.start()
    }
}