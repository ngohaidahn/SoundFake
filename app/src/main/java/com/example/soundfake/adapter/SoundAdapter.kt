package com.example.soundfake.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintsChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.soundfake.R
import com.example.soundfake.Sound
import com.example.soundfake.databinding.ActivityMainBinding
import com.example.soundfake.databinding.ItemSoundBinding

class SoundAdapter(private var mList: ArrayList<Sound>, private var listener: OnItemClicked) :
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {


//    private var _binding : ItemSoundBinding? = null
//    private val binding get() = _binding!!


    class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSoundBinding.bind(itemView)

        val icon: ImageView = itemView.findViewById(R.id.icon)
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val play: ImageView = itemView.findViewById(R.id.bt_play)
        val cardView: ConstraintLayout = itemView.findViewById(R.id.cardView)
        val flash: ImageView = itemView.findViewById(R.id.tv_flash)
        val vibration: ImageView = itemView.findViewById(R.id.tv_vibration)
        val volume: ImageView = itemView.findViewById(R.id.tv_volume)
        val seekBarVolume: TextView = itemView.findViewById(R.id.seekbar_volume)


    }

    interface OnItemClicked {
        fun onClickItemIcon(sound: Sound, position: Int)
        fun onItemPlay(sound: Sound, position: Int)


    }

    fun updateData(newData: MutableList<Sound>) {
//        update ds
        mList = newData as ArrayList<Sound>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        var currentSound = mList[position]// vi tri
        holder.icon.setImageResource(currentSound.icon)
        holder.name.setText(currentSound.name)
        holder.play.setImageResource(R.drawable.ic_play)
//        holder.seekBarVolume.setText(currentSound.volumn)
        holder.seekBarVolume.text = currentSound.volumn.toString()
        ///////////////////


        if (currentSound.isSelected) {
            holder.cardView.setBackgroundResource(R.drawable.bg_sound_frament_recyclerview_blue)
            holder.play.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)

//            if (currentSound.onSound) holder.vibration.setColorFilter(Color.YELLOW)
//            if (currentSound.onVibration) holder.vibration.setColorFilter(Color.YELLOW)
//            if (currentSound.onSound) holder.volume.setColorFilter(Color.YELLOW)
            if (currentSound.onFlash) {
                holder.flash.setColorFilter(Color.BLUE)

            } else {
                holder.flash.setColorFilter(Color.WHITE)

            }
            if (currentSound.onVibration) {
                holder.vibration.setColorFilter(Color.BLUE)

            } else {
                holder.vibration.setColorFilter(Color.WHITE)

            }
            if (currentSound.onSound) {
                holder.volume.setColorFilter(Color.BLUE)
            } else {
                holder.volume.setColorFilter(Color.WHITE)
            }

        } else {
            holder.cardView.setBackgroundResource(R.drawable.bg_sound_frament_recyclerview)
            holder.play.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP)
            holder.flash.setColorFilter(Color.GRAY)
            holder.vibration.setColorFilter(Color.GRAY)
            holder.volume.setColorFilter(Color.GRAY)

//            if (currentSound.onFlash) holder.flash.setColorFilter(Color.BLUE)
//            if (currentSound.onVibration) holder.vibration.setColorFilter(Color.BLUE)
//            if (currentSound.onSound) holder.volume.setColorFilter(Color.BLUE)

        }
        holder.binding.root.setOnClickListener() {
            listener.onClickItemIcon(sound = currentSound, position)

        }
        holder.binding.btPlay.setOnClickListener {
            // play sound
            listener.onItemPlay(sound = currentSound, position)
        }
//
//        holder.cardView.setOnClickListener() {
//            listener.onClickItemIcon(sound = currentSound, position)
//        }
    }


//    fun setSelected(sound: Sound) {
//        for (s in mList) {
//            s.isSelected = s.sound == sound.sound
//        }
//
//        notifyDataSetChanged()
//    }

}