import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

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
            return arrayOfColors
        }


        /**
         * Uses BufferedImage method to translate array of simulated grains growth into image, in this case - in jpg format
         * @param xSize                         In v1.0 is the same as arraySize
         * @param ySize                         In v1.0 is the same as arraySize
         * @param arrayToDraw                   An array we are going to draw via the BufferedImage method
         * @param arrayOfColors                 An array of colors bonded to certain grains (nucleons)
         */
        fun drawArray(xSize: Int, ySize: Int, arrayToDraw: Array<Array<Int>>, arrayOfColors: Array<Int>, fileName: String): BufferedImage{
            var img =  BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB)
            var fileToSave: File
            for (i in 1..arrayToDraw.size - 2) {
                for (j in 1..arrayToDraw.size - 2) {
                    if(arrayToDraw[i][j]==-1)img.setRGB(i,j,0)
                    img.setRGB(i,j,arrayOfColors[arrayToDraw[i][j]])
                }
            }
            try {
                fileToSave = File("src/0.jpg")
                ImageIO.write(img, "jpg", fileToSave)
            } catch (e: IOException) {
                println("Error: " + e)
            }
            return img
        }

        fun drawArrayfromImport(path: String){

        }
    }
}