package io.github.itkova.api

fun Int.squareWith(double: Double): Double {
    return if (this != 0) {
        var r = 0.0
        for (i in 0 until this) {
            r += double
        }
        r
    } else {
        0.0
    }
}