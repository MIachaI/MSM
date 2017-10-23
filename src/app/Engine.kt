package app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
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
}
class UserModel(var integersSet: User) : ViewModel(){
    val xSize = bind { integersSet.xSizeProperty }
    val ySize = bind { integersSet.ySizeProperty }
    val nucleonsNumber = bind { integersSet.nucleonsNumberProperty }
    val inclusionsNumber = bind { integersSet.inclusionsNumberProperty }
    val inclusionsSize = bind { integersSet.inclusionsSizeProperty }
    var image = bind { integersSet.imageProperty }



}