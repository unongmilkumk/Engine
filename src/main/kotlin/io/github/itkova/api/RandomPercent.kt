package io.github.itkova.api


fun randompercent(perc: Int): Boolean {

    val rand = (1..100).random()
    return rand < perc
}
