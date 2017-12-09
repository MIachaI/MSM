package controller

import app.User
import app.UserModel
import javafx.scene.image.WritableImage
import tornadofx.Controller
import tornadofx.toProperty
import app.Cell


/**
 * Created by MIachaI on 19.10.2017.
 */

class engineController : Controller(){


    companion object {
        private val model = UserModel(User())
        fun getModelxSize() = model.xSize.value.toInt()
        fun setModelxSize(size: Int){model.xSize = size.toProperty()}

        fun getModelySize() =model.ySize.value.toInt()

        fun setModelySize(size: Int){
            model.ySize = size.toProperty()
        }

        fun getArray(): Array<Array<Cell>>{
            return model.array.value
        }
        fun setArray(arrayModel: Array<Array<Cell>>) {
            model.array = arrayModel.toProperty()
        }

        fun getNucleonsNumber(): Int{
            return model.nucleonsNumber.value.toInt()
        }
        fun setNucleonsNumber(numberToSet: Int){
            model.nucleonsNumber = numberToSet.toProperty()
        }

        fun getInclusionsNumber(): Int{
            return model.inclusionsNumber.value.toInt()
        }
        fun setInclusionsNumber(numberToSet: Int){
            model.inclusionsNumber = numberToSet.toProperty()
        }

        fun getInclusionsSize(): Int{
            return model.inclusionsSize.value.toInt()
        }
        fun setInclusionsSize(SizeToSet: Int){
            model.inclusionsSize = SizeToSet.toProperty()
        }

        fun getModelColorArray(): Array<Int>{
           return model.arrayOfColors.value
        }
        fun setModelColorArray(arrayToSet: Array<Int>){
            model.arrayOfColors = arrayToSet.toProperty()
        }

        fun setModelImage(imageToSet: WritableImage){
            model.image = imageToSet.toProperty()
        }
        fun getModelImage(): WritableImage{
            return model.image.value
        }

        fun setModelProbability(probability: Int){
            model.probabilityOfChange = probability.toProperty()
        }
        fun getModelProbability():Int{
            return model.probabilityOfChange.value.toInt()
        }
        fun getFileToReadPath(): String{
            return model.fileToImport.value.toString()
        }
        fun setFileToReadPath(path: String){
            model.fileToImport = path.toProperty()
        }
        fun setNumberOfSelectedGrains(numberOfGrains: Int){
            model.grainsToKeep = numberOfGrains.toProperty()
        }
        fun getNumberOfSelectedGrains(): Int{
           return model.grainsToKeep.value.toInt()
        }
        fun setBoundaryDrawing(draw: Boolean){
            model.drawBoundary = draw.toProperty()
        }
        fun getBoundaryDrawing(): Boolean{
            return model.drawBoundary.value
        }
        fun getEnergyLevel(): Int{
            return model.energyLevel.value.toInt()
        }
        fun setEnergyLevel(energyLevel: Int){
            model.energyLevel = energyLevel.toProperty()
        }
        fun getEnergyColorArray(): Array<Int>{
            return model.arrayOfEnergy.value
        }
        fun setEnergyColorArray(arrayToSet: Array<Int>){
            model.arrayOfEnergy = arrayToSet.toProperty()
        }

/**
        fun runSimulation(){
            Utils.grainGrow()


        }
        **/
    }
}