/**
 * Created by MIachaI on 07.10.2017.
 */
package mmGui
import java.util.Scanner
import java.util.Random
import Utils

fun main (args : Array<String>){
    println("Enter size of an array")
    val input = Scanner(System.`in`)
    var arraySize = input.nextInt()

    var previousStepArray = Array(arraySize, {Array(arraySize,{0})})
    var nextStepArray = previousStepArray

    println("Enter number of grains")
    val grains = input.nextInt()
    if(grains>arraySize*arraySize){
        println("There are more grains than cells! Program will not proceed.")
        return
    }
    Utils.setGrainsInArray(grains,arraySize,previousStepArray)


    for (i in 0..previousStepArray.size-1){
        for (j in 0..previousStepArray.size-1){

            nextStepArray[i][j]


        }
    }

    Utils.printArray(previousStepArray)
}


