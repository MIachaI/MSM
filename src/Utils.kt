import java.util.*

/**
 * Created by MIachaI on 10.10.2017.
 */
class Utils{

    companion object {

        fun setGrainsInArray(grains: Int, arraySize: Int, previousStepArray: Array<Array<Int>>){
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
        }

        fun grainGrow(xSize: Int, ySize: Int, previousStepArray: Array<Array<Int>>, nextStepArray: Array<Array<Int>>){
while (true){
            for (i in 0..xSize-1){
                for (j in 0..ySize-1){
                    try {
                        if(previousStepArray[i][j]==0 && previousStepArray[i-1][j]!=0) {
                            nextStepArray[i][j]=previousStepArray[i-1][j]
                            continue
                        }
                        else if (previousStepArray[i][j]==0 && previousStepArray[i+1][j]!=0){
                            nextStepArray[i][j]=previousStepArray[i+1][j]
                            continue
                        }
                        else if (previousStepArray[i][j]==0 && previousStepArray[i][j-1]!=0){
                            nextStepArray[i][j]=previousStepArray[i][j-1]
                            continue
                        }
                        else if (previousStepArray[i][j]==0 && previousStepArray[i][j+1]!=0){
                            nextStepArray[i][j]=previousStepArray[i][j+1]
                            continue
                        }
                    }
                    catch (e: ArrayIndexOutOfBoundsException) {
                        continue
                    }
                }
            }
            if(nextStepArray contentEquals previousStepArray) {
                return }
           // previousStepArray=nextStepArray
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