package com.example.soundfake

object Constants {

    fun getSoundData(): ArrayList<Sound> {
        val getSoundList = ArrayList<Sound>()

        getSoundList.add(Sound(R.raw.dog_barking,R.drawable.cat,R.string.cat, true))
        getSoundList.add(Sound(R.raw.disco_groove_122074,R.drawable.dog,R.string.dog))
        getSoundList.add(Sound(R.raw.bell_sound,R.drawable.bell,R.string.bell))
        getSoundList.add(Sound(R.raw.funky_street_99110,R.drawable.chick,R.string.chimes))



        return getSoundList
    }
}