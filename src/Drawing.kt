import controller.engineController
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.WritableImage


/**
 * Created by MIachaI on 13.10.2017.
 */
class Drawing {

    companion object {

        fun setColors(grainsNumber: Int): Array<Int> {
            val arrayOfColors = Array(grainsNumber + 1, { 0 })

            for (i in 1..grainsNumber) {
                val alpha = (Math.random() * 256).toInt() //alpha
                val red = (Math.random() * 256).toInt() //red
                val green = (Math.random() * 256).toInt() //green
                val blue = (Math.random() * 256).toInt() //blue

                val pixel = alpha shl 24 or (red shl 16) or (green shl 8) or blue
                arrayOfColors[i] = pixel
            }
            arrayOfColors[0] = 0 shl 24 or (0 shl 16) or (0 shl 8) or 0
            return arrayOfColors
        }

        fun setEnergyColors(){
            var energyLevels = engineController.getEnergyLevel()
            val arrayOfColors = Array(energyLevels + 1, { 0 })

            for (i in 1..energyLevels) {
                val alpha = (Math.random() * 100).toInt() //alpha
                val red = (Math.random() * 100).toInt() //red
                val green = (Math.random() * 100).toInt() //green
                val blue = (Math.random() * 200).toInt() //blue

                val pixel = alpha shl 24 or (red shl 16) or (green shl 8) or blue
                arrayOfColors[i] = pixel
            }
            arrayOfColors[0] = 0 shl 24 or (0 shl 16) or (0 shl 8) or 0
            engineController.setEnergyColorArray(arrayOfColors)
        }

        fun drawArray(): WritableImage {
            val img = BufferedImage(engineController.getModelxSize(), engineController.getModelySize(), BufferedImage.TYPE_INT_RGB)
            val fileToSave: File
            for (i in 1..engineController.getModelxSize() - 2) {
                for (j in 1..engineController.getModelySize() - 2) {
                    if (engineController.getArray()[i][j].cellState == "inclusion") {
                        img.setRGB(i, j, 0)
                    }
                    if (engineController.getArray()[i][j].cellState == "dual phase") {
                        img.setRGB(i, j, 100 shl 24 or (100 shl 16) or (100 shl 8) or 100)
                    } else if (engineController.getArray()[i][j].cellState != "empty" && engineController.getArray()[i][j].cellState != "" && engineController.getArray()[i][j].cellState != "inclusion") {
                        engineController.getArray()[i][j].color = engineController.getModelColorArray()[engineController.getArray()[i][j].cellState.toInt()]
                        img.setRGB(i, j, engineController.getArray()[i][j].color)
                    }
                    if (engineController.getArray()[i][j].isBoundary && engineController.getBoundaryDrawing()) {
                        img.setRGB(i, j, 100)
                    }
                }
            }
            try {
                fileToSave = File("src/exportedImage.bmp")
                ImageIO.write(img, "bmp", fileToSave)
            } catch (e: IOException) {
                println("Error: " + e)
            }
            return SwingFXUtils.toFXImage(img, null)
        }

        fun drawEnergy(): WritableImage {
            val img = BufferedImage(engineController.getModelxSize(), engineController.getModelySize(), BufferedImage.TYPE_INT_RGB)
            for (i in 1..engineController.getModelxSize() - 2) {
                for (j in 1..engineController.getModelySize() - 2) {
                    img.setRGB(i,j,engineController.getEnergyColorArray()[engineController.getArray()[i][j].energy])
                }
            }
            engineController.setModelImage(SwingFXUtils.toFXImage(img, null))
            return SwingFXUtils.toFXImage(img, null)
        }
    }
}