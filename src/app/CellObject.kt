package app

import controller.engineController
import java.time.temporal.TemporalAdjusters.previous
import java.util.*


class Cell(var xCoordinate: Int, var yCoordinate: Int,  var cellState: String, var cellPreviousState: String,var color: Int,  var isBoundary: Boolean, var isLocked: Boolean, var energy: Int)

class Moore (var upperLeft: String,var up: String,var upperRight: String,var left: String,var right: String,var bottomLeft: String,var down: String,var bottomRight: String) {

    fun firstRule(): String {
        val occuranceList = arrayListOf<String>()
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
        val occuranceList = arrayListOf<String>()
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
        val occuranceList = arrayListOf<String>()
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
        val random = Random()
        val occuranceList = arrayListOf<String>()
        var count = 0
        var maxCount = 0
        var popular = "empty"
        val randomValue = random.nextInt(100)
        if (randomValue >= engineController.getModelProbability()) {


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