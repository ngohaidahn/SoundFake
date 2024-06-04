package com.example.soundfake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soundfake.Constants
import com.example.soundfake.MainActivity
import com.example.soundfake.R
import com.example.soundfake.Sound
import com.example.soundfake.adapter.DetailSoundAdapter
import com.example.soundfake.databinding.FragmentDetailSoundBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailSoundFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailSoundFragment : Fragment() {
    private lateinit var soundSelected: Sound

    companion object {
        const val TAG = "DetailSoundFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Detail_Sound.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailSoundFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentDetailSoundBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_sound, container, false)
//        return inflater.inflate(R.layout.fragment_detail_sound, container, false)

        _binding = FragmentDetailSoundBinding.inflate(inflater, container, false)
        val view = binding.root
        var sound = arguments?.getInt("sound") ?: 0
        var icon = arguments?.getInt("icon") ?: 0
        var name = arguments?.getInt("name") ?: 0
        var isSelected = arguments?.getBoolean("isSelected") ?: false
        var flash = arguments?.getBoolean("onFlash") ?: false
        var vibration = arguments?.getBoolean("onVibrate") ?: false
        var onSound = arguments?.getBoolean("onSound") ?: false
        var volume = arguments?.getInt("sound") ?: 0


        soundSelected = Sound(sound, icon, name, isSelected, flash, vibration, onSound, volume)
        binding.swFlash.setOnCheckedChangeListener { _, isChecked ->
            soundSelected.onFlash = isChecked
        }

        binding.swVibration.setOnCheckedChangeListener { _, isChecked ->
            soundSelected.onVibration = isChecked
        }

        binding.swSound.setOnCheckedChangeListener { _, isChecked ->
            soundSelected.onSound = isChecked
        }

        binding.tvSoundName.setText(name)
        binding.icon.setImageResource(icon)
        binding.play.setImageResource(R.drawable.ic_play)



        binding.seekbarDefault.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                soundSelected.volumn = progress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


//apdung
        binding.tvApply.setOnClickListener {

            (activity as MainActivity).imgVM.value = soundSelected



            parentFragmentManager.popBackStack()
        }

//        binding.tvApply.setOnClickListener {
//            (activity as MainActivity).soundLiveData.value = soundSelected
//            parentFragmentManager.popBackStack()
//        }


        binding.imageView1.setOnClickListener {
            activity?.finish()
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // getting the soundlist
        val detailSoundList = Constants.getSoundData()

        // Assign employeelist to ItemAdapter

        val itemAdapter =
            DetailSoundAdapter(detailSoundList, object : DetailSoundAdapter.OnItemClicked {
                override fun onClickItemIcon(sound: Sound, position: Int) {
//                         binding.icon.setImageResource(position)
                    soundSelected.sound = sound.sound
                    soundSelected.icon = sound.icon
                    soundSelected.name = sound.name

                    binding.tvSoundName.text = getString(sound.name)
                    binding.icon.setImageResource(sound.icon)
                }
            })
        // Set the LayoutManager that
        // this RecyclerView will use.
        val recyclerView: RecyclerView = view.findViewById(R.id.rcv_cfg_sound)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // adapter instance is set to the
        // recyclerview to inflate the items.
        recyclerView.adapter = itemAdapter
    }


}