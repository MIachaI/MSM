package controller

import app.User
import app.UserModel
import tornadofx.Controller

/**
 * Created by MIachaI on 19.10.2017.
 */

class engineController : Controller() {
    companion object {



        fun runSimulation(xSize: Int, ySize: Int, nucleonsNumber: Int): Array<Array<Int>> {

            var previousStepArray = Array(xSize, { Array(ySize, { 0 }) })
            var nextStepArray = Array(xSize, { Array(ySize, { 0 }) })
            Utils.setGrainsInArray(nucleonsNumber, xSize, previousStepArray)
            var colorSet = Drawing.setColors(nucleonsNumber)
            Utils.grainGrow(xSize, ySize, previousStepArray, nextStepArray, colorSet)
            Drawing.drawArray(xSize, ySize, previousStepArray, colorSet, "0")
            return previousStepArray

        }
    }
}