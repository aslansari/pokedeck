package com.aslansari.pokedeck.util

import junit.framework.TestCase

class SelectorKtTest : TestCase() {

    fun testLeastCommonMultiple() {
        var first = 3
        var second = 5
        var result = leastCommonMultiple(first, second)
        assertEquals(15, result)
    }

    fun testGreatestCommonDivisor() {
        var first = 3
        var second = 5
        var result = greatestCommonDivisor(first, second)
        assertEquals(1, result)
    }

    fun testRandomPicker() {
        // Arrange
        val map = mapOf(Pair("A", 10), Pair("B", 50), Pair("C", 5), Pair("D", 35))
        var sum = 0
        map.forEach{sum += it.value}
        val initTime = System.currentTimeMillis()
        val selector = ProbabilitySelector(map)
        val initEndTime = System.currentTimeMillis()
        println("created in ${initEndTime - initTime} ms")
        val result = mutableMapOf<String, Int>()
        val totalTryCount = 1_000_000
        // Act
        val startTime = System.currentTimeMillis()
        for (i in 0..totalTryCount) {
            val selected = selector.select()
            if (result.containsKey(selected)) {
                result[selected]?.plus(1)?.let { result.put(selected, it) }
            } else {
                result[selected] = 1
            }
        }
        val endTime = System.currentTimeMillis()
        println("Completed in ${endTime - startTime} ms")

        result.forEach {
            println("${it.key} is selected ${it.value} times")
        }
        // Assert
        map.forEach {
            val median = (totalTryCount / sum) * it.value
            println("Median is $median")
            val precision = 0.001
            val delta = totalTryCount * precision

            val ceil = (median + delta)
            val floor = (median - delta)
            val res = result[it.key]
            println("result is $res and it should be between $ceil and $floor")

            assertTrue(res!! < ceil && res > floor)
        }
    }
}

