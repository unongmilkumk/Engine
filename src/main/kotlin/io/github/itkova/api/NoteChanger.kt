package io.github.itkova.api

@Suppress("ControlFlowWithEmptyBody")
fun Int.toNote(): Double{
    return if ((this >= 0) && (this <= 24)) {
        2.squareWith((( this - 12 ) / 12).toDouble())
    } else {
        0.0
    }
}