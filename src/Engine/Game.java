package Engine;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * A Game is an image (BufferedImage) and an integer. The integer is the solution of the game that is described in the 
 * image. 
 * @author Marc Conrad
 *
 */
public class Game {

	
	BufferedImage image = null; 	
	int solution = -1;
	
	/**
	 * Image of the game and what is the solution to the game.
	 * @param image
	 * @param solution
	 */
	public Game(BufferedImage image, int solution) {
		super();
		this.image = image;
		this.solution = solution;
	}
	
	/**
	 * The image of the game. 
	 * @return the location of the game.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return The solution of the game.
	 */
	public int getSolution() {
		return solution;
	}
	
	
	
	

}


