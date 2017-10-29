package app

class Cell(x: Int, y: Int, cellState: String, previousCellState: String, colorSet: Int, boundary: Boolean){

        var xCoordinate = x
        var yCoordinate = y
        var cellState = cellState
        var cellPreviousState = previousCellState
        var color = colorSet
        var isBoundary = boundary
}
