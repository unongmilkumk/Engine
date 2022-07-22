package io.github.itkova.api


fun randint(min: Int, max: Int): Int {
    val rand = ArrayList<Int>()
    for (i in min..max) {
        rand.add(i)
    }
    rand.shuffle()
    return rand[0]
}