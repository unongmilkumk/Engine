package io.github.itkova.api


fun randompercent(perc: Int): Boolean {

    val rand = ArrayList<Int>()

    for (i in 1..100) {
        rand.add(i)
    }

    rand.shuffle()

    return rand[1] < perc
}
