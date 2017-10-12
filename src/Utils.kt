import java.io.File
import java.io.InputStream
import java.util.*

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

                var xSize = random.nextInt(arraySize)
                var ySize = random.nextInt(arraySize)
                while (previousStepArray[xSize][ySize] == 0) {
                    previousStepArray[xSize][ySize] = iterator
                    iterator++
                }
            }
            return previousStepArray
        }

        /**
         * This function is main engine to simulate gran growth. It requiers sizes, both x and y, and two arrays
         * on which simulation will proceed
         * @param xSize                 In v1.0 is the same as arraySize
         * @param ySize                 In v1.0 is the same as arraySize
         * @param previousStepArray     Two dimensional array to compare certain cells
         * @param nextStepArray         Two dimensional array to update changes between previous and next step in real time
         */
        fun grainGrow(xSize: Int, ySize: Int, previousStepArray: Array<Array<Int>>, nextStepArray: Array<Array<Int>>){
            var buffer = 0;
            //while (true) {
                for (i in 0..xSize - 1) {
                    for (j in 0..ySize - 1) {
                        printArray(previousStepArray)
                        println("")
                        if (previousStepArray[i][j] == 0) {
                            try {

                                if (previousStepArray[i - 1][j] != 0) {
                                    nextStepArray[i][j] = previousStepArray[i - 1][j]
                                } else if (previousStepArray[i + 1][j] != 0) {
                                    nextStepArray[i][j] = previousStepArray[i + 1][j]
                                } else if (previousStepArray[i][j - 1] != 0) {
                                    nextStepArray[i][j] = previousStepArray[i][j - 1]
                                } else if (previousStepArray[i][j + 1] != 0) {
                                    nextStepArray[i][j] = previousStepArray[i][j + 1]
                                }

                            } catch (e: ArrayIndexOutOfBoundsException) {
                                println("error in $i and $j ")
                                continue
                            }
                        }
                    }
                    for (i in 0..xSize - 1) {
                        for (j in 0..ySize - 1) {
                            previousStepArray[i][j] = nextStepArray[i][j]
                        }
                    }
                //}
            }}

        /**
         * Print array to console
         * @param arrayToPrint              Two dimensional array to print
         */
        fun printArray(arrayToPrint: Array<Array<Int>>) {
            for (i in 0..arrayToPrint.size - 1) {
                println(" ")
                for (j in 0..arrayToPrint.size - 1) {
                    print(" ")
                    //print(arrayToPrint[i][j])
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
            val file = File("export.txt")
            file.writeText("$xSize $ySize 1")
            file.appendText("\n")
            for (i in 0..previousStepArray.size - 1) {
                for (j in 0..previousStepArray.size - 1) {
                    file.appendText("$i $j 0 ")
                    var ij = previousStepArray[i][j]
                    file.appendText(ij.toString())
                    file.appendText("\n")
                }
            }
        }

        fun writeFromFile(){
            val inputStream: InputStream = File("export.txt").inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            println(inputString)
        }
    }}

