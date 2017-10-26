package app
/**
import app.Cell.Companion.cellState
import app.Cell.Companion.color
import app.Cell.Companion.xCoordinate
import app.Cell.Companion.yCoordinate**/
import javafx.beans.property.SimpleIntegerProperty

class Cell(x:Int, y:Int, cell: String, colorSet: Int){

        var xCoordinate = x
        var yCoordinate = y
        var cellState = cell
        var color = colorSet

        private fun getxCoordinate(): Int{
            return xCoordinate
        }

        private fun createCell(x: Int,y: Int,state: String,cellColor: Int){
            xCoordinate = x
            yCoordinate = y
            cellState = state
            color = cellColor
        }



}
