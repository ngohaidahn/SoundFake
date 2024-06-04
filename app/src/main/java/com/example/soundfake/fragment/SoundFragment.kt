package com.example.soundfake.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.soundfake.Constants
import com.example.soundfake.MainActivity
import com.example.soundfake.R
import com.example.soundfake.Sound
import com.example.soundfake.adapter.SoundAdapter
import com.example.soundfake.databinding.FragmentDetailSoundBinding
import com.example.soundfake.databinding.FragmentSoundBinding
import com.example.soundfake.fragment.DetailSoundFragment.Companion.TAG

/**
 * A simple [Fragment] subclass.
 * Use the [SoundFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SoundFragment : Fragment() {
    private var indexSelected = -1

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sound, container, false)
    }

    companion object {

//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SoundFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SoundFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }

    private var _binding: FragmentSoundBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentAllBinding.bind(view)
        _binding = FragmentSoundBinding.bind(view)

        // getting the soundlist
        val soundList = Constants.getSoundData()

        // Assign employeelist to ItemAdapter
        val itemAdapter = SoundAdapter(soundList, object : SoundAdapter.OnItemClicked {
            override fun onClickItemIcon(sound: Sound, position: Int) {
                indexSelected = position
                (activity as MainActivity).goToDetailFragment(sound)
            }

            override fun onItemPlay(sound: Sound, position: Int) {

            }
        })

        (activity as? MainActivity)?.imgVM?.observe(activity as LifecycleOwner) {
            Log.i(TAG, "observe PictureItem Livedata $it")
            for (s in soundList) {
                s.isSelected = false
            }

            if (indexSelected != -1) {
                soundList[indexSelected] = it
                //
                soundList[indexSelected].isSelected = true
                itemAdapter.updateData(soundList)
                binding.img.setImageResource(soundList[indexSelected].icon)

            }

        }
        // Set the LayoutManager that
        // this RecyclerView will use.
        val recyclerView: RecyclerView = view.findViewById(R.id.rcv_cfg_sound)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // adapter instance is set to the
        // recyclerview to inflate the items.
        recyclerView.adapter = itemAdapter
    }




}