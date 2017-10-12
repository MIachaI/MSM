/**
 * Created by MIachaI on 07.10.2017.
 */
package mmGui
import java.util.Scanner
import java.util.Random
import Utils
import java.io.BufferedWriter
import java.io.File

fun main (args : Array<String>){
    println("Enter size of an array")
    val input = Scanner(System.`in`)
    var arraySize = input.nextInt()

    var previousStepArray = Array(arraySize, {Array(arraySize,{0})})
    var nextStepArray = Array(arraySize, {Array(arraySize,{0})})

    println("Enter number of grains")
    val grains = input.nextInt()
    if(grains>arraySize*arraySize){
        println("There are more grains than cells! Program will not proceed.")
        return
    }
    Utils.setGrainsInArray(grains,arraySize,previousStepArray)

    for (i in 0..arraySize-1){
        for (j in 0..arraySize-1){
            nextStepArray[i][j]=previousStepArray[i][j]
        }
    }

    Utils.printArray(nextStepArray)

    Utils.grainGrow(arraySize,arraySize,previousStepArray,nextStepArray)
    println(" ")
    Utils.printArray(previousStepArray)
    Utils.saveToFile(arraySize,arraySize,previousStepArray)
    Utils.writeFromFile()


    }



