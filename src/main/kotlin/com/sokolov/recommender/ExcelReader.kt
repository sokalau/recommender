package com.sokolov.recommender

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

class ExcelReader {
    companion object {
        private const val INITIAL_INDEX = 0
    }

    private var rows: Int = 0
    private var columns: Int = 0

    fun read(file: File?): Array<DoubleArray> {
        val result: Array<DoubleArray>

        val pcg: OPCPackage = OPCPackage.open(file)
        val workbook = XSSFWorkbook(pcg)
        val sheet: XSSFSheet = workbook.getSheetAt(INITIAL_INDEX)

        rows = sheet.physicalNumberOfRows

        preprocess(sheet)
        result = process(sheet)

        return result
    }

    private fun preprocess(sheet: XSSFSheet) {
        var i = 1
        var temp: Int
        var row: XSSFRow?

        while (i < 10 || i < rows) {
            row = sheet.getRow(i)
            if (row != null) {
                temp = sheet.getRow(i).physicalNumberOfCells
                if (temp > columns) {
                    columns = temp
                }
            }
            i++
        }
    }

    private fun process(sheet: XSSFSheet): Array<DoubleArray> {
        var row: XSSFRow
        var cell: XSSFCell

        val result: Array<DoubleArray> = Array(rows - 1) { DoubleArray(columns) }

        for (r in 1 until rows) {
            row = sheet.getRow(r)
            if (row != null) {
                for (c in 0 until columns) {
                    cell = row.getCell(c.toShort().toInt())
                    if (cell != null) {
                        result[r - 1][c] = cell.numericCellValue
                    }
                }
            }
        }

        return result
    }
}