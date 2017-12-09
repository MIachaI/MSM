package app

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.WritableImage
import tornadofx.ViewModel

/**
 * Created by MIachaI on 19.10.2017.
 */

class User {
    val xSizeProperty = SimpleIntegerProperty()
    val ySizeProperty = SimpleIntegerProperty()
    val nucleonsNumberProperty = SimpleIntegerProperty()
    val inclusionsNumberProperty = SimpleIntegerProperty()
    val inclusionsSizeProperty = SimpleIntegerProperty()
    var imageProperty = SimpleObjectProperty<WritableImage>()
    var arrayToWorkOn = SimpleObjectProperty<Array<Array<Cell>>>()
    var arrayOfColors = SimpleObjectProperty<Array<Int>>()
    var arrayOfEnergy = SimpleObjectProperty<Array<Int>>()
    var probabilityOfChange = SimpleIntegerProperty()
    var fileToImport = SimpleStringProperty()
    var grainsToKeep = SimpleIntegerProperty()
    var drawBoundary = SimpleBooleanProperty()
    var energyLevel = SimpleIntegerProperty()
}
class UserModel(var integersSet: User) : ViewModel(){
    var xSize = bind { integersSet.xSizeProperty }
    var ySize = bind { integersSet.ySizeProperty }
    var nucleonsNumber = bind { integersSet.nucleonsNumberProperty }
    var inclusionsNumber = bind { integersSet.inclusionsNumberProperty }
    var inclusionsSize = bind { integersSet.inclusionsSizeProperty }
    var image = bind { integersSet.imageProperty }
    var array = bind {integersSet.arrayToWorkOn}
    var arrayOfColors = bind {integersSet.arrayOfColors}
    var probabilityOfChange = bind {integersSet.probabilityOfChange}
    var fileToImport = bind {integersSet.fileToImport}
    var grainsToKeep = bind { integersSet.grainsToKeep}
    var drawBoundary = bind { integersSet.drawBoundary}
    var energyLevel = bind {integersSet.energyLevel}
    var arrayOfEnergy = bind {integersSet.arrayOfEnergy}



}