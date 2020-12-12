package com.pitchblack.tiviplus

object Utils {

    fun String.getYear(): String {
        return if (this.isEmpty()) "-" else this.take(4)
    }

    fun Int.toHour(): String {
        val hour: Int = this/60
        val minutes: Int = this%60
        return "${hour}h ${minutes}m"
    }

}