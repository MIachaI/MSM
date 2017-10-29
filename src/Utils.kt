import app.Cell
import controller.engineController
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.InputStream
import java.util.*
import java.io.IOException
import javax.imageio.ImageIO



/**
 * Created by MIachaI on 10.10.2017.
 */
class Utils{

    companion object {
        /**
         * This function was created to set grains at random places within the simulated area
         * @param grains                How many grains will grow
         * @param arraySize             Basically in v1.0 xSize and ySize are the same - the arraySize
         * @param previousStepArray     Two dimensional array on which we will generate grains
         */
        fun setGrainsInArray(grains: Int, array: Array<Array<Cell>>): Array<Array<Cell>>{
            var iterator=1
            val random = Random()
            while(iterator <= grains) {

                var xSize = random.nextInt(engineController.getModelxSize()-2)+1
                var ySize = random.nextInt(engineController.getModelySize()-2)+1
                while (array[xSize][ySize].cellState == "empty") {
                    array[xSize][ySize].cellState = iterator.toString()
                    array[xSize][ySize].cellPreviousState = iterator.toString()
                    iterator++
                }
            }
            return array
        }

        fun setDiagonalInclusionsBefore(): Array<Array<Cell>> {
            var iterator = 1
            val random = Random()
            var betaArray = engineController.getArray()
            while (iterator <= engineController.getInclusionsNumber()) {
                try {
                    var xSize = random.nextInt(engineController.getModelxSize() - 2) + 1
                    var ySize = random.nextInt(engineController.getModelySize() - 2) + 1
                    while (betaArray[xSize][ySize].cellState == "empty")
                        for (i in 0..engineController.getInclusionsSize()) {
                            for (j in 0..engineController.getInclusionsSize()) {
                                betaArray[xSize + i][ySize + j].cellState = "inclusion"
                            }

                        }

                    iterator++
                }
                catch (e: ArrayIndexOutOfBoundsException){
                    println("found error")
                    continue
                }

            }
            engineController.setArray(betaArray)
            return betaArray

        }

        fun setCircleInclusionsInArray(inclusions: Int, inclusionRadius: Int, arrayToBeSet: Array<Array<Cell>>, arraySize: Int): Array<Array<Cell>>{
            var iterator=1
            val random = Random()
            while(iterator <= inclusions) {

                var xSize = random.nextInt(arraySize-2)+1
                var ySize = random.nextInt(arraySize-2)+1
                while (arrayToBeSet[xSize][ySize].cellState == "empty") {
                    arrayToBeSet[xSize][ySize].cellState = "inclusion"
                    iterator++
                }
            }
            return arrayToBeSet
        }



        /**
         * This function is main engine to simulate gran growth. It requiers sizes, both x and y, and two arrays
         * on which simulation will proceed
         * @param xSize                 In v1.0 is the same as arraySize
         * @param ySize                 In v1.0 is the same as arraySize
         * @param previousStepArray     Two dimensional array to compare certain cells
         * @param nextStepArray         Two dimensional array to update changes between previous and next step in real time
         */
        fun grainGrow(xSize: Int, ySize: Int, array: Array<Array<Cell>>){

            while (true) {
                var buffer = 0
                for (i in 1..xSize - 2) {
                    for (j in 1..ySize - 2) {
                        if (array[i][j].cellPreviousState.equals("empty")) {
                            buffer++
                            try {
                                if (array[i - 1][j].cellPreviousState.equals("empty")!=true && array[i - 1][j].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i - 1][j].cellPreviousState
                                }
                                else if (array[i + 1][j].cellPreviousState.equals("empty")!=true && array[i + 1][j].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i + 1][j].cellPreviousState
                                }
                                else if (array[i][j - 1].cellPreviousState.equals("empty")!=true && array[i][j - 1].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i][j - 1].cellPreviousState
                                }
                                else if (array[i][j + 1].cellPreviousState.equals("empty")!=true && array[i][j + 1].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i][j + 1].cellPreviousState
                                }
                            } catch (e: ArrayIndexOutOfBoundsException) {
                                println("error in $i and $j ")
                                continue
                            }
                        }
                    }
                }

                for (i in 1..xSize-2){
                    for (j in 1..ySize-2){
                        engineController.getArray()[i][j].cellPreviousState = engineController.getArray()[i][j].cellState
                    }
                }
                    if(buffer==0) return
                }
            }



        fun saveToFile(){
            val fileToWrite = File("export.txt")
            fileToWrite.writeText("${engineController.getModelxSize()} ${engineController.getModelySize()} 1 ")
            var stringer = ""
            for (i in 0..engineController.getModelxSize() - 1) {
                for (j in 0..engineController.getModelySize() - 1) {
                    stringer+="$i $j 0 ${engineController.getArray()[i][j].cellPreviousState} ${engineController.getArray()[i][j].cellState} ${engineController.getArray()[i][j].color} ${engineController.getArray()[i][j].isBoundary} "
                }
            }
            fileToWrite.appendText(stringer)

        }

        fun writeFromFile(){
            val inputStream: InputStream = File("export.txt").inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            var inputList = inputString.split(" ")
            engineController.setModelxSize(inputList[0].toInt())
            engineController.setModelySize(inputList[1].toInt())
            var bufferList = inputList.subList(3,inputList.size)
            var testArray = Array(engineController.getModelxSize(), {Array(engineController.getModelySize(),{Cell(0,0,"empty","empty",0,false)})})

             for(x in 0..bufferList.size-5){
                if(x%7==0) {
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].cellPreviousState = bufferList[x + 3]
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].cellState = bufferList[x + 4]
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].color = bufferList[x + 5].toInt()
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].isBoundary = bufferList[x + 6].toBoolean()


                }
            }
            engineController.setArray(testArray)
        }


    }}

