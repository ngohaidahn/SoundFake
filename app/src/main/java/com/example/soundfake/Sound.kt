package com.example.soundfake

import java.io.Serializable

class Sound(
    var sound: Int, var icon: Int, var name: Int,
    var isSelected: Boolean = false,
    var onFlash: Boolean = false,
    var onVibration: Boolean = false,
    var onSound: Boolean = false,
    var volumn: Int = 50,

    ) : Serializable {

}