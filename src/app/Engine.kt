package app

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.View
import tornadofx.ViewModel
import tornadofx.getProperty
import tornadofx.property

/**
 * Created by MIachaI on 19.10.2017.
 */

class User {
    val xSizeProperty = SimpleIntegerProperty()
   // fun xSizeGetter() = getProperty()
    val ySizeProperty = SimpleIntegerProperty()
    val nucleonsNumberProperty = SimpleIntegerProperty()
    val inclusionsNumberProperty = SimpleIntegerProperty()
    val inclusionsSizeProperty = SimpleIntegerProperty()
}
class UserModel(var integersSet: User) : ViewModel(){
    val xSize = bind { integersSet.xSizeProperty }
    val ySize = bind { integersSet.ySizeProperty }
    val nucleonsNumber = bind { integersSet.nucleonsNumberProperty }
    val inclusionsNumber = bind { integersSet.inclusionsNumberProperty }
    val inclusionsSize = bind { integersSet.inclusionsSizeProperty }



}