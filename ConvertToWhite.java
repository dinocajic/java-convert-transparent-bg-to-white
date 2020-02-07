import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConvertToWhite {

    private static String folder_name = "tires"; // or wheels

    public static void main(String... args) {
        final File folder = new File("D:/DB/FCWT/applications/eci-website-images/" + folder_name);
        convertImagesToWhiteBackground( folder );
    }

    public static void convertImagesToWhiteBackground(final File folder) {
        for (final File fileEntry : Objects.requireNonNull( folder.listFiles() )) {
            if ( fileEntry.isDirectory() ) {
                convertImagesToWhiteBackground( fileEntry );
            } else {
                try {
                    File input  = new File("D:/DB/FCWT/applications/web-images/" + folder_name + "/" + fileEntry.getName());
                    File output = new File("D:/DB/FCWT/applications/web-images/" + folder_name + "_white/" + fileEntry.getName());

                    BufferedImage image = ImageIO.read(input);
                    BufferedImage result = new BufferedImage(
                            image.getWidth(),
                            image.getHeight(),
                            BufferedImage.TYPE_INT_RGB);
                    result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
                    ImageIO.write(result, "jpg", output);

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}