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

        fun grainGrow(previousStepArray: Array<Array<Int>>, nextStepArray: Array<Array<Int>>){

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