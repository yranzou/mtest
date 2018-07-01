package com.mtest.server.common;

import com.mtest.server.exception.ServerException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

public class Resizer {
    public static byte[] resizeImage(byte[] imageBytes) throws ServerException {

        final int newWidth = 300;

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

//            Image image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageBytes));
            String imageType = contentType.substring(contentType.lastIndexOf("/")+1);
            System.out.println("!!!!!!!!!!!!!!!! " + imageType);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int newHeight = height * newWidth / width;

//            Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
//            BufferedImage bufferedResizedImage = (BufferedImage) resizedImage;

            BufferedImage bufferedResizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            Graphics g = bufferedResizedImage.createGraphics();
            g.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
            g.dispose();




            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedResizedImage, imageType, bos );
            return bos.toByteArray();


        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

}
