package com.sokolov.recommender

import java.io.File

fun main() {
    val reader = ExcelReader()
    val file = File("C:\\Users\\Nikita\\Downloads\\awesome.xlsx")

    val matrix = reader.read(file)
    Printer.printMatrix(matrix, false)

    val normalized = Normalizer.normalize(matrix)
    Printer.printMatrix(normalized, true)

    val clusterer = Clusterer()
    val clustered = clusterer.cluster(normalized)

    val classifier = Classifier()
    classifier.classify(clustered)
}