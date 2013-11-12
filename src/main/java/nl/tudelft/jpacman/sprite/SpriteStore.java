package nl.tudelft.jpacman.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Utility to load {@link Sprite}s.
 * 
 * @author Jeroen Roosen <j.roosen@student.tudelft.nl>
 */
public class SpriteStore {

	/**
	 * Loads a sprite from a resource on the class path.
	 * 
	 * @param resource
	 *            The resource path.
	 * @return A new sprite for the resource.
	 * @throws IOException
	 *             When the resource could not be loaded.
	 */
	public Sprite loadSprite(String resource) throws IOException {
		InputStream input = SpriteStore.class.getResourceAsStream(resource);
		if (input == null) {
			throw new IOException("Unable to load " + resource
					+ ", resource does not exist.");
		}
		BufferedImage image = ImageIO.read(input);
		return new ImageSprite(image);
	}

	/**
	 * Creates a new {@link AnimatedSprite} from a base image.
	 * 
	 * @param baseImage
	 *            The base image to convert into an animation.
	 * @param frames
	 *            The amount of frames of the animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether this sprite is a looping animation or not.
	 * @return The animated sprite.
	 */
	public AnimatedSprite createAnimatedSprite(Sprite baseImage, int frames,
			int delay, boolean loop) {
		assert baseImage != null;
		assert frames > 0;

		int frameWidth = baseImage.getWidth() / frames;

		Sprite[] animation = new Sprite[frames];
		for (int i = 0; i < frames; i++) {
			animation[i] = baseImage.split(i * frameWidth, 0, frameWidth,
					baseImage.getHeight());
		}

		return new AnimatedSprite(animation, delay, loop);
	}

}