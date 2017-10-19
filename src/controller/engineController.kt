package controller

import app.User
import tornadofx.Controller
/**
 * Created by MIachaI on 19.10.2017.
 */

class engineController : Controller(){
    fun runSimulation(model: User){
        var previousStepArray = Array(model.xSizeProperty.value.toInt(), {Array(model.ySizeProperty.value.toInt(),{0})})
        var nextStepArray = Array(model.xSizeProperty.value.toInt(), {Array(model.ySizeProperty.value.toInt(),{0})})
        Utils.setGrainsInArray(model.nucleonsNumberProperty.value.toInt(),model.xSizeProperty.value.toInt(),previousStepArray)
        var colorSet = Drawing.setColors(model.nucleonsNumberProperty.value.toInt())
        Utils.grainGrow(model.xSizeProperty.value.toInt(),model.ySizeProperty.value.toInt(),previousStepArray,nextStepArray, colorSet)
        Drawing.drawArray(model.xSizeProperty.value.toInt(),model.ySizeProperty.value.toInt(),previousStepArray, colorSet, "0")
        println("zrobione")
    }
}