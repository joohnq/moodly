package com.joohnq.security.impl

class PinHelper {
    fun keyboardBack(
        code: List<Int?>,
        focusedIndex: Int?,
    ): Pair<List<Int?>, Int?> {
        val previousIndex = getPreviousFocusedIndex(focusedIndex)

        val code =
            code.mapIndexed { i, number ->
                if (i == previousIndex) null else number
            }

        return Pair(code, previousIndex)
    }

    fun enterNumber(
        code: List<Int?>,
        focusedIndex: Int?,
        index: Int,
        number: Int?,
    ): Pair<List<Int?>, Int?> {
        val newCode =
            code.mapIndexed { currentIndex, currentNumber ->
                if (currentIndex == index) number else currentNumber
            }

        val wasNumberRemoved = number == null
        val focusedIndex =
            if (wasNumberRemoved || code.getOrNull(index) != null) {
                focusedIndex
            } else {
                getNextFocusedIndex(
                    currentCode = code,
                    currentFocusedIndex = focusedIndex
                )
            }

        return Pair(newCode, focusedIndex)
    }

    private fun getPreviousFocusedIndex(index: Int?): Int? = index?.minus(1)?.coerceAtLeast(0)

    private fun getNextFocusedIndex(
        currentCode: List<Int?>,
        currentFocusedIndex: Int?,
    ): Int? {
        if (currentFocusedIndex == null) return null
        if (currentFocusedIndex == 3) return currentFocusedIndex
        return getFirstEmptyFieldIndexAfterFocusedIndex(currentCode, currentFocusedIndex)
    }

    private fun getFirstEmptyFieldIndexAfterFocusedIndex(
        code: List<Int?>,
        currentFocusedIndex: Int,
    ): Int {
        code.forEachIndexed { i, number ->
            if (i <= currentFocusedIndex) return@forEachIndexed
            if (number == null) return i
        }
        return currentFocusedIndex
    }
}
