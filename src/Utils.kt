import java.util.*

/**
 * Created by MIachaI on 10.10.2017.
 */
class Utils{
    companion object {

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

        fun grainGrow(xSize: Int, ySize: Int, previousStepArray: Array<Array<Int>>, nextStepArray: Array<Array<Int>>){

//while (true){
            for (i in 1..xSize-2) {
                for (j in 1..ySize - 2) {
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
                            continue
                        }

                    }

                }
                // }

                // if(nextStepArray contentDeepEquals previousStepArray) {
                 //   return }
            }
            for (i in 0..xSize-1){
                for (j in 0..ySize-1){
                    previousStepArray[i][j]=nextStepArray[i][j]
                }
            }

        }
        fun printArray(arrayToPrint: Array<Array<Int>>) {
            for (i in 0..arrayToPrint.size - 1) {
                println(" ")
                for (j in 0..arrayToPrint.size - 1) {
                    print(" ")
                    print(arrayToPrint[i][j])
                }
            }
        }
    }
}