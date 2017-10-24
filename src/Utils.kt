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
        fun setGrainsInArray(grains: Int, arraySize: Int, previousStepArray: Array<Array<Int>>): Array<Array<Int>>{
            var iterator=1
            val random = Random()
            while(iterator <= grains) {

                var xSize = random.nextInt(arraySize-2)+1
                var ySize = random.nextInt(arraySize-2)+1
                while (previousStepArray[xSize][ySize] == 0) {
                    previousStepArray[xSize][ySize] = iterator
                    iterator++
                }
            }
            return previousStepArray
        }

        fun setDiagonalInclusionsInArray(inclusions: Int, inclusionSize: Int, arrayToBeSet: Array<Array<Int>>, arraySize: Int): Array<Array<Int>>{
            var iterator=1
            val random = Random()
            while(iterator <= inclusions) {

                var xSize = random.nextInt(arraySize-2*inclusionSize)+inclusionSize
                var ySize = random.nextInt(arraySize-2*inclusionSize)+inclusionSize
                while (arrayToBeSet[xSize][ySize] == 0) {
                    arrayToBeSet[xSize][ySize] = -1
                    iterator++
                }
            }
            return arrayToBeSet
        }

        fun setCircleInclusionsInArray(inclusions: Int, inclusionRadius: Int, arrayToBeSet: Array<Array<Int>>, arraySize: Int): Array<Array<Int>>{
            var iterator=1
            val random = Random()
            while(iterator <= inclusions) {

                var xSize = random.nextInt(arraySize-2)+1
                var ySize = random.nextInt(arraySize-2)+1
                while (arrayToBeSet[xSize][ySize] == 0) {
                    arrayToBeSet[xSize][ySize] = -1
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
        fun grainGrow(xSize: Int, ySize: Int, previousStepArray: Array<Array<Int>>, nextStepArray: Array<Array<Int>>, colorSet: Array<Int>){
            var name = 0
            while (true) {

                var buffer = 0;
                for (i in 1..xSize - 2) {
                    for (j in 1..ySize - 2) {
                        if (previousStepArray[i][j] == 0) {
                            buffer++
                            try {
                                if (previousStepArray[i - 1][j] != 0 && previousStepArray[i - 1][j] != -1) {
                                    nextStepArray[i][j] = previousStepArray[i - 1][j]
                                }
                                else if (previousStepArray[i + 1][j] != 0 && previousStepArray[i + 1][j] != -1) {
                                    nextStepArray[i][j] = previousStepArray[i + 1][j]
                                }
                                else if (previousStepArray[i][j - 1] != 0 && previousStepArray[i][j - 1] != -1) {
                                    nextStepArray[i][j] = previousStepArray[i][j - 1]
                                }
                                else if (previousStepArray[i][j + 1] != 0 && previousStepArray[i][j + 1] != -1) {
                                    nextStepArray[i][j] = previousStepArray[i][j + 1]
                                }

                            } catch (e: ArrayIndexOutOfBoundsException) {
                                println("error in $i and $j ")
                                continue
                            }
                        }
                    }
                }

                //Drawing.drawArray(xSize,ySize,previousStepArray,colorSet,name.toString())
                name++

                    if(buffer==0) return

                    for (i in 0..xSize - 1) {
                        for (j in 0..ySize - 1) {
                            previousStepArray[i][j] = nextStepArray[i][j]

                        }
                    }
                }
            }

        /**
         * Print array to console
         * @param arrayToPrint              Two dimensional array to print
         */
        fun printArray(arrayToPrint: Array<Array<Int>>) {
            for (i in 1..arrayToPrint.size - 2) {
                println(" ")
                for (j in 1..arrayToPrint.size - 2) {
                    print(" ")
                    print(arrayToPrint[i][j])
                }
            }
        }

        /**
         * Save array to file
         * @param xSize                     In v1.0 is the same as arraySize
         * @param ySize                     In v1.0 is the same as arraySize
         * @param previousStepArray         Two dimensional array to save
         */
        fun saveToFile(xSize: Int, ySize: Int, previousStepArray: Array<Array<Int>>){
            val fileToWrite = File("export.txt")
            fileToWrite.writeText("$xSize $ySize 1 ")
            //fileToWrite.appendText("\n")
            for (i in 0..previousStepArray.size - 1) {
                for (j in 0..previousStepArray.size - 1) {
                    fileToWrite.appendText("$i $j 0 ")
                    var previousArrayImage = previousStepArray[i][j]
                    fileToWrite.appendText(previousArrayImage.toString())
                    //fileToWrite.appendText(" \n")
                    fileToWrite.appendText(" ")

                }
            }
        }

        fun writeFromFile(){
            val inputStream: InputStream = File("export.txt").inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            var inputList = inputString.split(" ")
            var xSize = inputList[0]
            var ySize = inputList[1]
            var bufferList = inputList.subList(4,inputList.size)
            var previousStepArray = Array(xSize.toInt(), {Array(ySize.toInt(),{0})})

            for(x in 0..bufferList.size){
                if(x%4==0) {
                    var raz = bufferList[x]
                    var dwa = bufferList[x+2]
                    var trzy = bufferList[x+3]
                    previousStepArray[bufferList[x].toInt()][bufferList[x + 1].toInt()] = bufferList[x + 3].toInt()
                }
            }
          /**  for (i in 0..previousStepArray.size - 1) {
                for (j in 0..previousStepArray.size - 1) {
                   println(previousStepArray[i][j])
                }
            }**/
        }


    }}

