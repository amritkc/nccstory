package nccstory.ml.androidapp


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_activity_handbook.*

/**
 * A simple [Fragment] subclass.
 */
class activityhandbook : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_handbook, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        armypfaf1.setOnClickListener {
            val intent = Intent(activity, armypdf::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(activity, navypdf::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(activity, airforcepdf::class.java)
            startActivity(intent)
        }
        imgarmy.setOnClickListener {
            val intent = Intent(activity, armypdf::class.java)
            startActivity(intent)
        }
        imgnavy.setOnClickListener {
            val intent = Intent(activity, navypdf::class.java)
            startActivity(intent)
        }
        imgairforce.setOnClickListener {
            val intent = Intent(activity, airforcepdf::class.java)
            startActivity(intent)
        }

    }
}
