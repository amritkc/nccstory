package nccstory.ml.androidapp


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_web.*

/**
 * A simple [Fragment] subclass.
 */
class webFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play.setOnClickListener {
            val intent = Intent(activity, music_activity::class.java)
            startActivity(intent)
        }
        songview.setOnClickListener {
            val intent = Intent(activity, nccstory.ml.androidapp.songview::class.java)
            startActivity(intent)
        }

    }
}