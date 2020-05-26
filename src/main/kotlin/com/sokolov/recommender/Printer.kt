package com.sokolov.recommender

object Printer {
    private const val INDENT_SIZE = 7
    private const val BORDER = "-"
    private const val EMPTY = ""

    fun printMatrix(matrix: Array<DoubleArray>, normalized: Boolean) {
        val additionalIndent = if (!normalized) 0 else (matrix[0].size - 1) * 2

        val bound = Array(INDENT_SIZE * matrix[0].size + additionalIndent) { BORDER }
            .joinToString(separator = EMPTY, prefix = EMPTY, postfix = EMPTY)

        println(bound)

        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, _ ->
                if (normalized) {
                    System.out.printf("  %.3f  ", matrix[rowIndex][columnIndex])
                } else {
                    System.out.printf("%${INDENT_SIZE}d", matrix[rowIndex][columnIndex].toInt())
                }
            }
            println()
        }

        println(bound)
    }
}