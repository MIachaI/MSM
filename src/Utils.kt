import app.Cell
import app.Moore
import controller.engineController
import java.io.File
import java.io.InputStream
import java.util.*




/**
 * Created by MIachaI on 10.10.2017.
 */
class Utils{

    companion object {

        fun setGrainsInArray(): Array<Array<Cell>>{
            //cellsAtBoundary()
            var iterator=1
            val random = Random()
            val grains = engineController.getNucleonsNumber()
            val array = engineController.getArray()
            while(iterator <= grains) {

                var xSize = random.nextInt(engineController.getModelxSize()-2)+1
                var ySize = random.nextInt(engineController.getModelySize()-2)+1
                while (array[xSize][ySize].cellState == "empty" && !array[xSize][ySize].isLocked) {
                    array[xSize][ySize].cellState = iterator.toString()
                    array[xSize][ySize].cellPreviousState = iterator.toString()
                    iterator++
                }
            }
            return array
        }
        fun setCoordinatesInArray(){
            var array = engineController.getArray()
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            for (i in 1..xSize-2) {
                for (j in 1..ySize-2) {
                    array[i][j].xCoordinate = i
                    array[i][j].yCoordinate = j
                }
            }
        }

        fun setEnergyInArray(){
            var array = engineController.getArray()
            val random = Random()
            var nucleons = engineController.getNucleonsNumber()
            println(nucleons)
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            for (i in 1..xSize-2) {
                for (j in 1..ySize-2) {
                    if(!array[i][j].isLocked) {
                        array[i][j].cellState = random.nextInt(nucleons).toString()
                    }
                }
            }

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
                            if (array[i - 1][j].cellPreviousState.equals(array[i][j].cellState)!=true && array[i - 1][j].cellPreviousState.equals("inclusion")!=true && array[i][j].cellPreviousState.equals("empty")!=true) {
                                array[i][j].isBoundary=true
                                array[i][j].isLocked=true
                            }
                            else if (array[i + 1][j].cellPreviousState.equals(array[i][j].cellState)!=true && array[i + 1][j].cellPreviousState.equals("inclusion")!=true && array[i][j].cellPreviousState.equals("empty")!=true) {
                                array[i][j].isBoundary=true
                                array[i][j].isLocked=true
                            }
                            else if (array[i][j - 1].cellPreviousState.equals(array[i][j].cellState)!=true && array[i][j - 1].cellPreviousState.equals("inclusion")!=true && array[i][j].cellPreviousState.equals("empty")!=true) {
                                array[i][j].isBoundary=true
                                array[i][j].isLocked=true
                            }
                            else if (array[i][j + 1].cellPreviousState.equals(array[i][j].cellState)!=true && array[i][j + 1].cellPreviousState.equals("inclusion")!=true && array[i][j].cellPreviousState.equals("empty")!=true) {
                                array[i][j].isBoundary=true
                                array[i][j].isLocked=true
                            }
                            else {
                                array[i][j].isBoundary=false
                                array[i][j].isLocked=false
                            }
                        } catch (e: ArrayIndexOutOfBoundsException) {
                            println("error in $i and $j ")
                            continue
                        }
                }
            }
            engineController.setArray(array)
        }

        fun distributeEnergyHeterogeniously(){
            cellsAtBoundary()
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            for (i in 1..xSize - 2) {
                for (j in 1..ySize - 2) {
                    try {
                       if (array[i][j].isBoundary){
                           array[i][j].energy=5
                       }
                        else {
                           array[i][j].energy=2
                       }
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        println("error in $i and $j ")
                        continue
                    }
                }
            }
            engineController.setArray(array)
        }
        fun distributeEnergyHomogeniously(){
            cellsAtBoundary()
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            for (i in 1..xSize - 2) {
                for (j in 1..ySize - 2) {
                    try {
                        array[i][j].energy=5

                    } catch (e: ArrayIndexOutOfBoundsException) {
                        println("error in $i and $j ")
                        continue
                    }
                }
            }
            engineController.setArray(array)
        }

        fun placeSomeNucleonsWithEnergy(){
            var iterator = 1
            val random = Random()
            var betaArray = engineController.getArray()
            while (iterator <= engineController.getNucleonsNumber()) {
                try {
                    var xSize = random.nextInt(engineController.getModelxSize() - 2) + 1
                    var ySize = random.nextInt(engineController.getModelySize() - 2) + 1
                    betaArray[xSize][ySize].cellState = iterator.toString()
                    betaArray[xSize][ySize].cellPreviousState = iterator.toString()
                    betaArray[xSize][ySize].energy = 0
                    iterator++
                }
                catch (e: ArrayIndexOutOfBoundsException){
                    println("found error")
                    continue
                }
            }
            engineController.setArray(betaArray)
        }

        fun placeSomeNucleonsWithEnergyOnBoundary(){
            var iterator = 1
            val random = Random()
            var betaArray = engineController.getArray()
            while (iterator <= engineController.getNucleonsNumber()) {
                try {
                    var xSize = random.nextInt(engineController.getModelxSize() - 2) + 1
                    var ySize = random.nextInt(engineController.getModelySize() - 2) + 1
                    if(betaArray[xSize][ySize].isBoundary){
                        betaArray[xSize][ySize].cellState = iterator.toString()
                        betaArray[xSize][ySize].cellPreviousState = iterator.toString()
                        betaArray[xSize][ySize].energy = 0
                        iterator++}

                }
                catch (e: ArrayIndexOutOfBoundsException){
                    println("found error")
                    continue
                }
            }
            engineController.setArray(betaArray)
        }

        fun grainGrow(){
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            var stopValue = 0
            var nextBuffer = 0
            while (true) {
                var previousBuffer = 0
                for (i in 1..xSize - 2) {
                    for (j in 1..ySize - 2) {
                        if (array[i][j].cellPreviousState=="empty") {
                            previousBuffer++
                            try {
                                if (array[i - 1][j].cellPreviousState.equals("empty")!=true && array[i - 1][j].isLocked ==false && array[i - 1][j].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i - 1][j].cellPreviousState
                                }
                                else if (array[i + 1][j].cellPreviousState.equals("empty")!=true && array[i + 1][j].isLocked==false && array[i + 1][j].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i + 1][j].cellPreviousState
                                }
                                else if (array[i][j - 1].cellPreviousState.equals("empty")!=true && array[i][j - 1].isLocked==false && array[i][j - 1].cellPreviousState.equals("inclusion")!=true) {
                                    array[i][j].cellState = array[i][j - 1].cellPreviousState
                                }
                                else if (array[i][j + 1].cellPreviousState.equals("empty")!=true && array[i][j + 1].isLocked==false && array[i][j + 1].cellPreviousState.equals("inclusion")!=true) {
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
                println(previousBuffer)
                if(previousBuffer==nextBuffer){

                    if(stopValue==previousBuffer){
                        return
                    }
                    stopValue=nextBuffer
                }
                if(previousBuffer==0) {
                    engineController.setArray(array)
                    return
                }
                nextBuffer=previousBuffer
            }
            }


        fun mooreGrowth() {
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            while (true) {
                var buffer = 0
                for (i in 1..xSize - 2) {
                    for (j in 1..ySize - 2) {
                        if (!array[i][j].isLocked){
                            if (array[i][j].cellPreviousState == "empty" && !array[i][j].isLocked) {
                                buffer++
                                try {
                                    var temporary = Moore(
                                            array[i - 1][j - 1].cellPreviousState,
                                            array[i - 1][j].cellPreviousState,
                                            array[i - 1][j + 1].cellPreviousState,
                                            array[i][j - 1].cellPreviousState,
                                            array[i][j + 1].cellPreviousState,
                                            array[i + 1][j - 1].cellPreviousState,
                                            array[i + 1][j].cellPreviousState,
                                            array[i + 1][j + 1].cellPreviousState)
                                    array[i][j].cellState = temporary.moore()
                                } catch (e: ArrayIndexOutOfBoundsException) {
                                    println("error in $i and $j ")
                                    continue
                                }
                            }
                    }
                    }
                }

                for (i in 1..xSize - 2) {
                    for (j in 1..ySize - 2) {
                        engineController.getArray()[i][j].cellPreviousState = engineController.getArray()[i][j].cellState
                    }
                }
                println(buffer)
                if (buffer == 0) {
                    engineController.setArray(array)
                    return
                }
            }
        }
        fun shuffledListOfCells(array: Array<Array<Cell>>): MutableList<Cell>{
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var shuffledList: MutableList<Cell> = ArrayList()
            for (i in 2..xSize-3) {
                for (j in 2..ySize-3) {
                    shuffledList.add(array[i][j])
                }
            }
            Collections.shuffle(shuffledList)
            return shuffledList
        }

        fun checkNeighboursEnergy(){

        }
        fun energyGrowth(){
            var xSize = engineController.getModelxSize()
            var ySize = engineController.getModelySize()
            var array = engineController.getArray()
            var shuffledList = shuffledListOfCells(array)
            val occuranceList = arrayListOf<Int>()
            var kroneckersDelta = 0;
            var changingEnergy =0;
            val random = Random()
            var randomCell =0;
            for(k in 0..engineController.getSimulationSteps()){
                shuffledList=shuffledListOfCells(array)
                for (l in shuffledList){
                    occuranceList.clear()
                    kroneckersDelta=0;
                    changingEnergy=0;


                    var i = l.xCoordinate
                    var j = l.yCoordinate

                    occuranceList.add(array[i - 1][j - 1].cellState.toInt())
                    occuranceList.add(array[i - 1][j].cellState.toInt())
                    occuranceList.add(array[i - 1][j + 1].cellState.toInt())
                    occuranceList.add(array[i][j - 1].cellState.toInt())
                    occuranceList.add(array[i][j + 1].cellState.toInt())
                    occuranceList.add(array[i + 1][j - 1].cellState.toInt())
                    occuranceList.add(array[i + 1][j].cellState.toInt())
                    occuranceList.add(array[i + 1][j + 1].cellState.toInt())
                    for(surroundingCell in 0..occuranceList.size-1){
                        if(array[i][j].cellState.toInt()==occuranceList[surroundingCell]){
                            kroneckersDelta++
                        }
                    }
                    randomCell = random.nextInt(7)
                    for(surroundingCell in 0..occuranceList.size-1){
                        if(occuranceList[surroundingCell]==occuranceList[randomCell]){
                            changingEnergy++
                        }
                    }
                    if(kroneckersDelta<=changingEnergy){
                        array[i][j].cellState=occuranceList[randomCell].toString()
                    }
                }
                engineController.setArray(array)
            }





            engineController.setArray(array)
        }

        fun resetArrayWithSelectedGrains(){
            var temporaryArray = engineController.getArray()
            var numberOfGrainstoKeep = engineController.getNumberOfSelectedGrains()
            for (i in 0..engineController.getModelxSize() - 1) {
                for (j in 0..engineController.getModelySize() - 1) {
                    if(temporaryArray[i][j].cellState=="empty" || temporaryArray[i][j].cellState=="inclusion" || temporaryArray[i][j].cellState.toInt()<=numberOfGrainstoKeep){
                        temporaryArray[i][j].cellState = "empty"
                        temporaryArray[i][j].cellPreviousState = "empty"}
                    else{
                        temporaryArray[i][j].isLocked = true
                    }
                }
            }
            engineController.setArray(temporaryArray)
        }

        fun dualPhase(){
            var temporaryArray = engineController.getArray()
            for (i in 0..engineController.getModelxSize() - 1) {
                for (j in 0..engineController.getModelySize() - 1) {
                    if(temporaryArray[i][j].cellState!="empty"){
                        temporaryArray[i][j].cellState="dual phase"
                        temporaryArray[i][j].cellPreviousState="dual phase"
                        temporaryArray[i][j].isLocked = true
                        println("$i $j")
                    }
                }
            }
            engineController.setArray(temporaryArray)
        }

        fun clearSpace(){
            var temporaryArray = engineController.getArray()
            var numberOfGrainstoKeep = engineController.getNumberOfSelectedGrains()
            println(numberOfGrainstoKeep)
            for (i in 0..engineController.getModelxSize() - 1) {
                for (j in 0..engineController.getModelySize() - 1) {
                    if(temporaryArray[i][j].cellState!="empty"){
                    if(temporaryArray[i][j].isBoundary==false) {
                        if (temporaryArray[i][j].cellState.toInt() <= numberOfGrainstoKeep){
                        temporaryArray[i][j].cellState = "empty"
                        temporaryArray[i][j].cellPreviousState = "empty"
                    }
                    }
                    }
                }
            }
            engineController.setArray(temporaryArray)
        }


        fun saveToFile(){
            val fileToWrite = File(engineController.getFileToReadPath()+"/export.txt")
            fileToWrite.writeText("${engineController.getModelxSize()} ${engineController.getModelySize()} 1 ")
            var stringer = ""
            for (i in 0..engineController.getModelxSize() - 1) {
                for (j in 0..engineController.getModelySize() - 1) {
                    stringer+="$i $j 0 ${engineController.getArray()[i][j].cellPreviousState} ${engineController.getArray()[i][j].cellState} ${engineController.getArray()[i][j].color} ${engineController.getArray()[i][j].isBoundary} ${engineController.getArray()[i][j].isLocked} ${engineController.getArray()[i][j].energy} "
                }
                fileToWrite.appendText(stringer)
                stringer=""
            }


        }

        fun writeFromFile(){
            val inputStream: InputStream = File(engineController.getFileToReadPath()).inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            var inputList = inputString.split(" ")
            engineController.setModelxSize(inputList[0].toInt())
            engineController.setModelySize(inputList[1].toInt())
            var bufferList = inputList.subList(3,inputList.size)
            var testArray = Array(engineController.getModelxSize(), {Array(engineController.getModelySize(),{Cell(0,0,"empty","empty",-1,false, false, 0)})})

             for(x in 0..bufferList.size-5){
                if(x%7==0) {
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].cellPreviousState = bufferList[x + 3]
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].cellState = bufferList[x + 4]
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].color = bufferList[x + 5].toInt()
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].isBoundary = bufferList[x + 6].toBoolean()
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].isLocked = bufferList[x + 7].toBoolean()
                    testArray[bufferList[x].toInt()][bufferList[x + 1].toInt()].energy = bufferList[x + 8].toInt()
                }
            }
            engineController.setArray(testArray)
        }
    }
}

