package com.example.soundfake

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.soundfake.fragment.DetailSoundFragment
import com.example.soundfake.fragment.SoundFragment

class MainActivity : AppCompatActivity() {
    val imgVM = MutableLiveData<Sound>()
    val tvPicVM = MutableLiveData<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        replaceFragment(SoundFragment())


    }

    fun replaceFragment(fragment: Fragment) {
        // Get a reference to the FragmentManager
        val fragmentManager = supportFragmentManager

        // Start a new FragmentTransaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.frame_layout, fragment)

        // Commit the FragmentTransaction
        fragmentTransaction.commit()
    }

    fun goToDetailFragment(sound: Sound) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val detailSoundFragment = DetailSoundFragment()
        val bundle = Bundle()
//        bundle.putSerializable("object_image", sound)
        bundle.putInt("sound", sound.sound)//nhac
        bundle.putInt("icon", sound.icon)//anh
        bundle.putInt("name", sound.name)//ten
        bundle.putBoolean("isSelected", sound.isSelected)//den
        bundle.putBoolean("onFlash", sound.onFlash)//den
        bundle.putBoolean("onVibrate", sound.onVibration)//rung
        bundle.putBoolean("onSound", sound.onSound)//
        bundle.putInt("volume", sound.volumn)//nhac


//        bundle.putSerializable("object_image", image)
        detailSoundFragment.arguments = bundle



        //chuyen data
        fragmentTransaction.add(R.id.frame_layout, detailSoundFragment)
        fragmentTransaction.addToBackStack(DetailSoundFragment.TAG)
        fragmentTransaction.commit()
    }
}