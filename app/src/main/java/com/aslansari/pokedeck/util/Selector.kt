package com.aslansari.pokedeck.util

import java.util.concurrent.ThreadLocalRandom

interface Selector<T> {
    fun select() : T
}

class ProbabilitySelector(private val values: Map<String, Int>) : Selector<String> {
    private val pool = mutableListOf<String>()

    init {
        val dataset = values.toMutableMap()
        val gcm = findGreatestCommonDivisor(values.values.toMutableList())

        if (gcm != 1) {
            dataset.entries.map {
                it.setValue(it.value / gcm)
            }
        }

        dataset.forEach {
            for (i in 1..it.value) {
                pool.add(it.key)
            }
        }
        pool.shuffle()
    }
    override fun select(): String {
        return if (values.size < 2) {
            values.entries.first().key
        } else {
            val randomIndex = ThreadLocalRandom.current().nextInt(0, pool.size)
            pool[randomIndex]
        }
    }
}

fun leastCommonMultiple(number1: Int, number2: Int): Int {
    return (number1 / greatestCommonDivisor(number1, number2)) * number2
}

fun greatestCommonDivisor(number1: Int, number2: Int): Int {
    if (number1 == 0) {
        return number2;
    }
    return greatestCommonDivisor(number2 % number1, number1)
}

fun findGreatestCommonDivisor(numbers: MutableList<Int>) : Int {
    return if (numbers.size < 2)  {
        numbers[0]
    } else {
        var gcm = greatestCommonDivisor(numbers[0], numbers[1])
        for(index in numbers.indices) {
            gcm = greatestCommonDivisor(gcm, numbers[index+1])
            if (index == numbers.size - 2) {
                break
            }
        }
        gcm
    }

}