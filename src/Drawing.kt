import app.User
import app.UserModel
import controller.engineController
import javafx.beans.property.SimpleObjectProperty
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.WritableImage
import tornadofx.toProperty


/**
 * Created by MIachaI on 13.10.2017.
 */
class Drawing {

    companion object {
        /**
         * Sets random color to certain grain - it gives high scaleability
         * @param grainsNumber                  Number of grains which growth we are trying to simulate
         * @return arrayOfColors                An array with colors on the grain position
         */
        fun setColors(grainsNumber: Int): Array<Int>{
            var arrayOfColors = Array(grainsNumber+1, { 0 })

            for (i in 1..grainsNumber){
                val alpha = (Math.random() * 256).toInt() //alpha
                val red = (Math.random() * 256).toInt() //red
                val green = (Math.random() * 256).toInt() //green
                val blue = (Math.random() * 256).toInt() //blue

                var pixel = alpha shl 24 or (red shl 16) or (green shl 8) or blue
                arrayOfColors[i]=pixel
            }
            arrayOfColors[0] = 0 shl 24 or (0 shl 16) or (0 shl 8) or 0
            return arrayOfColors
        }


        fun putColorsIntoCellObject(){
            engineController.getArray()

        }

        /**
         * Uses BufferedImage method to translate array of simulated grains growth into image, in this case - in jpg format
         * @param xSize                         In v1.0 is the same as arraySize
         * @param ySize                         In v1.0 is the same as arraySize
         * @param arrayToDraw                   An array we are going to draw via the BufferedImage method
         * @param arrayOfColors                 An array of colors bonded to certain grains (nucleons)
         */
        fun drawArray(): WritableImage {
            var img =  BufferedImage(engineController.getModelxSize(), engineController.getModelySize(), BufferedImage.TYPE_INT_RGB)
            var fileToSave: File
            for (i in 1..engineController.getModelxSize() - 2) {
                for (j in 1..engineController.getModelySize() - 2) {
                    if(engineController.getArray()[i][j].cellState.equals("inclusion")){img.setRGB(i,j,0)}
                    else if (engineController.getArray()[i][j].cellState.equals("empty")!=true)
                    {
                        engineController.getArray()[i][j].color = engineController.getModelColorArray()[engineController.getArray()[i][j].cellState.toInt()]
                        img.setRGB(i, j, engineController.getArray()[i][j].color)
                    }
                }
            }
            try {
                fileToSave = File("src/exportedImage.bmp")
                ImageIO.write(img, "bmp", fileToSave)
            } catch (e: IOException) {
                println("Error: " + e)
            }
            val image = SwingFXUtils.toFXImage(img, null)
            return image
        }

    }
}