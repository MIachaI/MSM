package app

import controller.engineController
import java.time.temporal.TemporalAdjusters.previous
import java.util.*


class Cell(x: Int, y: Int, cellState: String, previousCellState: String, colorSet: Int, boundary: Boolean){

        var xCoordinate = x
        var yCoordinate = y
        var cellState = cellState
        var cellPreviousState = previousCellState
        var color = colorSet
        var isBoundary = boundary
}
class Moore (upperLeft: String, up: String, upperRight: String, left: String, right: String, bottomLeft: String, down: String, bottomRight: String) {
    var upperLeft = upperLeft
    var up = up
    var upperRight = upperRight
    var left = left
    var right = right
    var bottomLeft = bottomLeft
    var down = down
    var bottomRight = bottomRight


    fun firstRule(): String {
        var occuranceList = arrayListOf<String>()
        var count = 0
        var maxCount = 0
        var popular = ""

        occuranceList.add(upperLeft)
        occuranceList.add(up)
        occuranceList.add(upperRight)
        occuranceList.add(left)
        occuranceList.add(right)
        occuranceList.add(bottomLeft)
        occuranceList.add(down)
        occuranceList.add(bottomRight)
        for (i in occuranceList){
            if (i=="empty"){
                count++
            }
        }
        if(count>=3){
            return "empty"
        }
        for (i in occuranceList) {
            for (j in occuranceList) {
                if (i == j && i!="empty" && i!="inclusion") {
                    count++
                }
                if (maxCount <= count) {
                    maxCount = count
                    popular = i
                }
            }
            count = 0
        }
        if (maxCount >= 5) {
            return popular
        }
        return "empty"

    }

    fun secondRule(): String {
        var occuranceList = arrayListOf<String>()
        var count = 0
        var maxCount = 0
        var popular = ""

        occuranceList.add(up)
        occuranceList.add(left)
        occuranceList.add(right)
        occuranceList.add(down)

        for (i in occuranceList) {
            for (j in occuranceList) {
                if (i == j && i!="empty" && i!="inclusion") {

                    count++
                }
                if (maxCount <= count) {
                    maxCount = count
                    popular = i
                }

            }
            count = 0
        }
        if (maxCount >= 3) {
            return popular
        }
        return "empty"
    }

    fun thirdRule(): String {
        var occuranceList = arrayListOf<String>()
        var count = 0
        var maxCount = 0
        var popular = ""

        occuranceList.add(upperLeft)
        occuranceList.add(upperRight)
        occuranceList.add(bottomLeft)
        occuranceList.add(bottomRight)

        for (i in occuranceList) {
            for (j in occuranceList) {
                if (i == j && i!="empty" && i!="inclusion") {
                    count++
                }
                if (maxCount <= count) {
                    maxCount = count
                    popular = i
                }
            }
            count = 0
        }
        if (maxCount >= 5) {
            return popular
        }
        return "empty"
    }

    fun fourthRule(): String{
        var random = Random()
        var occuranceList = arrayListOf<String>()
        var count = 0
        var maxCount = 0
        var popular = "empty"
        var randomValue = random.nextInt(100)
        if (randomValue <= engineController.getModelProbability()) {


            occuranceList.add(upperLeft)
            occuranceList.add(up)
            occuranceList.add(upperRight)
            occuranceList.add(left)
            occuranceList.add(right)
            occuranceList.add(bottomLeft)
            occuranceList.add(down)
            occuranceList.add(bottomRight)


            for (i in occuranceList) {
                for (j in occuranceList) {
                    if (i == j && i!="empty" && i!="inclusion") {
                        count++
                    }
                    if (maxCount <= count && i!="inclusion") {
                        maxCount = count
                        popular = i
                    }
                }
                count = 0
            }

        }
        return popular
    }

    fun moore(): String{
        var returnValue = firstRule()
        if (returnValue=="empty"){
            returnValue = secondRule()
            if (returnValue=="empty"){
                returnValue = thirdRule()
                if (returnValue=="empty"){
                    returnValue = fourthRule()
                }
            }
        }
        return returnValue
    }
}