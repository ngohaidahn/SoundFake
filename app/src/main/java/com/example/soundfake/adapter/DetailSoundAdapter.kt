package com.example.soundfake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soundfake.R
import com.example.soundfake.Sound
import com.example.soundfake.databinding.ItemHorizontalSoundBinding

class DetailSoundAdapter(private var mList: ArrayList<Sound>, private var listener: OnItemClicked) : RecyclerView.Adapter<DetailSoundAdapter.DetailSoundViewHolder>(){

    private var indexSelected = -1

    class DetailSoundViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemHorizontalSoundBinding.bind(itemView)

        val icon: ImageView = itemView.findViewById(R.id.icon)
//        val apply: TextView = itemView.findViewById(R.id.tvApply)
    }
    interface OnItemClicked {

        fun onClickItemIcon(sound: Sound, position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_sound, parent, false)
        return DetailSoundViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DetailSoundViewHolder, position: Int) {
        var currentDetailSound = mList[position]
        holder.icon.setImageResource(currentDetailSound.icon)

        if (currentDetailSound.isSelected) {
            holder.binding.root.setBackgroundResource(R.drawable.bg_detail_horizontal_recyclerview_blue)
        } else {
           holder.binding.root.setBackgroundResource(R.drawable.bg_detail_horizontal_recyclerview)
        }

        holder.binding.root.setOnClickListener {
          //duyet lai list cho mau trang
            setSelected(currentDetailSound)
            listener.onClickItemIcon(sound = currentDetailSound, position)

        }

    }
    fun setSelected(sound: Sound) {
        for (s in mList) {
            s.isSelected = s.sound == sound.sound
        }
        notifyDataSetChanged()
    }
    fun setItems(items: List<Sound>) {
        this.mList.clear()
        this.mList.addAll(items)
        notifyDataSetChanged()
    }


}