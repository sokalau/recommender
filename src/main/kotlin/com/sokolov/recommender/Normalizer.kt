package com.sokolov.recommender

object Normalizer {
    fun normalize(matrix: Array<DoubleArray>): Array<DoubleArray> {

        matrix.forEachIndexed { rowIndex, row ->
            val min = row.toList().min() ?: Double.MIN_VALUE
            val max = row.toList().max() ?: Double.MAX_VALUE
            row.forEachIndexed { columnIndex, _ ->
                matrix[rowIndex][columnIndex] = (matrix[rowIndex][columnIndex] - min) / (max - min)
            }
        }

        return matrix
    }
}
