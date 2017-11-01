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
        val model = UserModel(User())
        fun getModelxSize() = model.xSize.value.toInt()
        fun setModelxSize(sizesize: Int){model.xSize = sizesize.toProperty()}

        fun getModelySize() =model.ySize.value.toInt()

        fun setModelySize(sizesize: Int){
            model.ySize = sizesize.toProperty()
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


        fun runSimulation(){
            Utils.grainGrow()


        }
    }
}