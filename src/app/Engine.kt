package app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.Image
import javafx.scene.image.PixelReader
import javafx.scene.image.WritableImage
import tornadofx.View
import tornadofx.ViewModel
import tornadofx.getProperty
import tornadofx.property

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
    var probabilityOfChange = SimpleIntegerProperty()
    var fileToImport = SimpleStringProperty()
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



}