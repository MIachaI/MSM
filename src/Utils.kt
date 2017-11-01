import app.Cell
import controller.engineController
import java.io.File
import java.io.InputStream
import java.util.*




/**
 * Created by MIachaI on 10.10.2017.
 */
class Utils{

    companion object {

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

        fun setDiagonalInclusionsBefore() {
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
                                betaArray[xSize + i][ySize + j].cellPreviousState = "inclusion"
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
        }
        fun setDiagonalInclusionsAfter(){
            var iterator = 1
            val random = Random()
            var betaArray = engineController.getArray()
            while (iterator <= engineController.getInclusionsNumber()) {
                try {
                    var xSize = random.nextInt(engineController.getModelxSize() - 2) + 1
                    var ySize = random.nextInt(engineController.getModelySize() - 2) + 1
                    if (betaArray[xSize][ySize].isBoundary == true) {
                        for (i in 0..engineController.getInclusionsSize()) {
                            for (j in 0..engineController.getInclusionsSize()) {
                                betaArray[xSize + i][ySize + j].cellState = "inclusion"
                                betaArray[xSize + i][ySize + j].cellPreviousState = "inclusion"
                            }
                        }
                        iterator++
                    }


                }
                catch (e: ArrayIndexOutOfBoundsException){
                    println("found error")
                    continue
                }
            }
            engineController.setArray(betaArray)

        }


        fun setCircleInclusionsBefore(){

            var iterator=1
            val random = Random()
            var betaArray = engineController.getArray()
            while(iterator <= engineController.getInclusionsNumber()) {

                var xSize = random.nextInt(engineController.getModelxSize()-2)+1
                var ySize = random.nextInt(engineController.getModelySize()-2)+1
                while (betaArray[xSize][ySize].cellState == "empty") {

                    for (i in -engineController.getInclusionsSize()..engineController.getInclusionsSize()){
                        for (j in -engineController.getInclusionsSize()..engineController.getInclusionsSize()){
                            if (i*i + j*j <= engineController.getInclusionsSize()*engineController.getInclusionsSize() + engineController.getInclusionsSize()*0.8){
                                try {
                                    betaArray[xSize + i][ySize + j].cellState = "inclusion"
                                    betaArray[xSize + i][ySize + j].cellPreviousState = "inclusion"
                                    println("$xSize $ySize")
                                }
                                catch (e: ArrayIndexOutOfBoundsException){
                                    println("found error")
                                    continue
                                }
                            }
                        }
                    }
                }
                iterator++
            }
            engineController.setArray(betaArray)
        }

        fun setCircleInclusionsAfter(){
            var iterator=1

            val random = Random()
            var betaArray = engineController.getArray()
            while(iterator <= engineController.getInclusionsNumber()) {

                var xSize = random.nextInt(engineController.getModelxSize()-2)+1
                var ySize = random.nextInt(engineController.getModelySize()-2)+1
                if (betaArray[xSize][ySize].isBoundary == true) {
                    for (i in -engineController.getInclusionsSize()..engineController.getInclusionsSize()){
                        for (j in -engineController.getInclusionsSize()..engineController.getInclusionsSize()){
                            if (i*i + j*j <= engineController.getInclusionsSize()*engineController.getInclusionsSize() + engineController.getInclusionsSize()*0.8){
                                try {
                                    betaArray[xSize + i][ySize + j].cellState = "inclusion"
                                    betaArray[xSize + i][ySize + j].cellPreviousState = "inclusion"
                                }
                                catch (e: ArrayIndexOutOfBoundsException){
                                    println("found error")
                                    continue
                                }
                            }

                        }

                    }
                    iterator++
                }

            }
            engineController.setArray(betaArray)
        }


        fun cellsAtBoundary(){
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            for (i in 1..xSize - 2) {
                for (j in 1..ySize - 2) {
                        try {
                            if (array[i - 1][j].cellPreviousState.equals(array[i][j].cellState)!=true && array[i - 1][j].cellPreviousState.equals("inclusion")!=true) {
                                array[i][j].isBoundary=true
                            }
                            else if (array[i + 1][j].cellPreviousState.equals(array[i][j].cellState)!=true && array[i + 1][j].cellPreviousState.equals("inclusion")!=true) {
                                array[i][j].isBoundary=true
                            }
                            else if (array[i][j - 1].cellPreviousState.equals(array[i][j].cellState)!=true && array[i][j - 1].cellPreviousState.equals("inclusion")!=true) {
                                array[i][j].isBoundary=true
                            }
                            else if (array[i][j + 1].cellPreviousState.equals(array[i][j].cellState)!=true && array[i][j + 1].cellPreviousState.equals("inclusion")!=true) {
                                array[i][j].isBoundary=true
                            }
                        } catch (e: ArrayIndexOutOfBoundsException) {
                            println("error in $i and $j ")
                            continue
                        }
                }
            }
            engineController.setArray(array)
        }


        fun grainGrow(){
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
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
                    println(buffer)
                }
                    if(buffer==0) {
                        engineController.setArray(array)
                    return
                    }
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
            var testArray = Array(engineController.getModelxSize(), {Array(engineController.getModelySize(),{Cell(0,0,"empty","empty",-1,false)})})

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

