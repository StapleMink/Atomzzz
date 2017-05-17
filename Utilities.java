
/* Gaurav Datta
 * 4/25/17
 * Utilities.java
 * This class contains 2 static methods to do the following
 * 	-Load an image given the name of the file and return it using try-catch
 * 	-Load a text file and instantiate and return a scanner for it using try-catch
 */

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Utilities
{
	public static Image loadImage(String imageName)
	{
		Image image = null;
		try // try catch block create image to return
		{
			image = ImageIO.read(new File(imageName));
		}

		catch (IOException e)
		{
			System.err.println("\n\n" + imageName + " can't be found.\n\n");
			e.printStackTrace();
		}

		return image;
	}

	public static Scanner loadText(String fileName)
	{
		File file;
		Scanner scan = null;

		file = new File(fileName);

		try // try catch block to create scanner that reads a text file to
			// return
		{
			scan = new Scanner(file);
		}

		catch (FileNotFoundException e)
		{
			System.err.println("\n\nCannot find " + fileName + " file.\n\n");
			System.exit(1);
		}

		return scan;
	}
}