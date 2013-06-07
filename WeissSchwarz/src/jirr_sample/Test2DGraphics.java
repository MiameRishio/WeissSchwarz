package jirr_sample;
/*
This Tutorial shows how to do 2d graphics with the Irrlicht Engine.
It shows how to draw images, keycolor based sprites,
transparent rectangles and different fonts. You will may consider
this useful if you want to make a 2d game with the engine, or if
you want to draw a cool interface or head up display for your 3d game.

As always, I include the header files, use the irr namespace,
and tell the linker to link with the .lib file.
*/

import net.sf.jirr.*;

import java.io.IOException;

public class Test2DGraphics
{
	static
	{
		System.loadLibrary("irrlicht_wrap");
	}

	static IrrlichtDevice device = null;
/*
At first, we let the user select the driver type, then
start up the engine, set a caption, and get a pointer
to the video driver.
*/
	public static void  main(String args[])
	{
		// ask user for driver
		E_DRIVER_TYPE driverType = E_DRIVER_TYPE.EDT_SOFTWARE;

		System.out.println("Please select the driver you want for this example:\n"
								 + " (a) Direct3D 9.0c\n (b) Direct3D 8.1\n (c) OpenGL 1.2\n"
								 + " (d) Software Renderer\n (e) NullDevice\n (otherKey) exit\n\n");

		try
		{
			int i = System.in.read();

			switch (i)
			{
				case 'a':
					driverType = E_DRIVER_TYPE.EDT_DIRECT3D9;
					break;
				case 'b':
					driverType = E_DRIVER_TYPE.EDT_DIRECT3D8;
					break;
				case 'c':
					driverType = E_DRIVER_TYPE.EDT_OPENGL;
					break;
				case 'd':
					driverType = E_DRIVER_TYPE.EDT_SOFTWARE;
					break;
				case 'e':
					driverType = E_DRIVER_TYPE.EDT_NULL;
					break;
				default:
					System.exit(0);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();  //To change body of catch statement use Options | File Templates.
		}

		// create device and exit if creation failed

		device = Jirr.createDevice(driverType, new dimension2di(640, 480), 32, false, false, false, null);

		if (device == null)
			System.exit(1); // could not create selected driver.

		device.setWindowCaption("Irrlicht Engine @Java - 2D Graphics Demo");

		IVideoDriver driver = device.getVideoDriver();

/*
	All 2d graphics in this example are put together into one texture,
	2ddemo.bmp. Because we want to draw colorkey based sprites, we need
	to load this texture and tell the engine, which
	part of it should be transparent based on a colorkey. In this example,
	we don't tell it the color directly, we just say "Hey Irrlicht Engine,
	you'll find the color I want at position (0,0) on the texture.".
	Instead, it would be also possible to call
	driver->makeColorKeyTexture(images, SColor(0,0,0,0)), to make
	e.g. all black pixels transparent. Please note, that makeColorKeyTexture
	just creates an alpha channel based on the color.
*/
		ITexture images = driver.getTexture("media/2ddemo.bmp");
		driver.makeColorKeyTexture(images, new position2di(0, 0));

		// some special effects - modify texture!
		if (1 != 0)
		{
//			// experimental textures
//			// - nur bei castToByte benötigt:
//			// 16, 24, 32 bit? <=> 2, 3, 4 bytes
//			ECOLOR_FORMAT ecolor_format = images.getColorFormat();
//			int byteMultiplier;
//			switch (ecolor_format)
//			{
//				// 16
//				case ECF_A1R5G5B5:
//				case ECF_R5G6B5:
//					byteMultiplier = 2; break;
//				//	24
//				case ECF_R8G8B8:
//					byteMultiplier = 3; break;
//				// 32
//				case ECF_A8R8G8B8:
//					byteMultiplier = 4; break;
//				default:
//					byteMultiplier = 1;
//			}

			dimension2di dim = images.getSize();
			SWIGTYPE_p_void imagesLock = images.lock();
			int sbuffer[] = Jirr.castVoidToInt32Array(imagesLock, dim);
//			SColor sbuffer[] = Jirr.castToSColorArray(imagesLock, images);
			SColor dbuffer[]= new SColor[dim.getHeight() * dim.getWidth()];
			if(sbuffer == null)
			{
				throw new RuntimeException("SOURCE fehlt");
			}
			for (int y = 0; y < dim.getHeight(); y++)
			{
				for(int x = 0; x < dim.getWidth(); x++)
				{
					int sindex = y * dim.getWidth() + x ;
					// 
					int dindex = sindex;

					// vary alpha
					int a = x % 256;
					//int a = (sbuffer[sindex] >> 24) & 255;
					int r = (sbuffer[sindex] >> 16) & 255;
					int g = (sbuffer[sindex] >> 8) & 255;
					int b = sbuffer[sindex] & 255;


					a = x % 256;
					//dbuffer[dindex] = a << 24 + r << 16 + g << 8 + b;
					dbuffer[dindex] = new SColor(a, r, g, b);
				}
			}

			Jirr.copySColorArrayToVoid(dbuffer, dim, imagesLock);
			//Jirr.copyInt32ArrayToVoid(imagesLock, images, dbuffer);

			images.unlock();

			//IImage image=driver.createImageFromData(ECOLOR_FORMAT.ECF_A8R8G8B8, dim, Jirr.castArrayToVoid(dbuffer),false);
			//IImage image=driver.createImageFromData(ECOLOR_FORMAT.ECF_A8R8G8B8, dim, Jirr.castSColorArrayToVoid(dbuffer),false);
//			IImage image=driver.createImageFromData(ECOLOR_FORMAT.ECF_A8R8G8B8, new dimension2di(10, 10), Jirr.copySColorArrayToVoid(imagesLock, images, dbuffer),false);
//			images=driver.addTexture("MEMORY", image);
		}
/*
	To be able to draw some text with two different fonts, we load them.
	Ok, we load just one, as first font we just use the default font which is
	built into the engine.
	Also, we define two rectangles, which specify the position of the
	images of the red imps (little flying creatures) in the texture.
*/
		IGUIFont font = device.getGUIEnvironment().getBuiltInFont();
		IGUIFont font2 = device.getGUIEnvironment().getFont("media/fonthaettenschweiler.bmp");

		recti imp1 = new recti(349, 15, 385, 78);
		recti imp2 = new recti(387, 15, 423, 78);
		                

/*
	Everything is prepared, now we can draw everything in the draw loop,
	between the begin scene and end scene calls. In this example, we
	are just doing 2d graphics, but it would be no problem to mix them
	with 3d graphics. Just try it out, and draw some 3d vertices or set
	up a scene with the scene manager and draw it.
*/
		int alpha = 0;
		while (device.run() && driver != null)
		{
			if (device.isWindowActive())
			{
				long time = device.getTimer().getTime();

				driver.beginScene(true, true, new SColor(0, 120, 102, 136));

				/*
				First, we draw 3 sprites, using the alpha channel we created with
				makeColorKeyTexture. The last parameter specifiys that the drawing
				method should use thiw alpha channel. The parameter before the last
				one specifies a color, with wich the sprite should be colored.
				(255,255,255,255) is full white, so the sprite will look like the
				original. The third sprite is drawed colored based on the time.
				*/

				// draw fire & dragons background world
				driver.draw2DImage(images, new position2di(50, 50),
										 new recti(0, 0, 342, 224), null,
										 new SColor(128, 128, 128, 128), true);

				// draw flying imp
				driver.draw2DImage(images, new position2di(164, 125),
										 ((time / 500 % 2) != 0) ? imp1 : imp2, null,
										 new SColor(128, 255, 255, 255), true);

				// draw second flying imp with colorcylce
				driver.draw2DImage(images, new position2di(270, 105),
										 ((time / 500 % 2) != 0) ? imp1 : imp2, null,
										 new SColor((int)(time % 255), (int)(time % 255), 255, 255), true);

				/*
				Drawing text is really simple. The code should be self explanatory.
				*/

				// draw some text
				if (font != null)
					font.draw("This demo shows that Irrlicht is also capable of drawing 2D graphics.",
					new recti(130, 10, 300, 50),
					new SColor(255, 255, 255, 255));

				// draw some other text
				if (font2 != null)
					font2.draw("Also mixing with 3d graphics is possible.",
					new recti(130, 20, 300, 60),
					new SColor(255, (int)(time % 255), (int)(time % 255), 255));

				/*
				At last, we draw the Irrlicht Engine logo (without using a color or
				an alpha channel) and a transparent 2d Rectangle at the position of
				the mouse cursor.
				*/

				// draw logo
				driver.draw2DImage(images,new  position2di(10, 10),
										 new recti(354, 87, 442, 118), null, new SColor(255,255,255,255));

				driver.draw2DImage(images,new  position2di(150, 150),
										 new recti(354, 87, 442, 118), null, new SColor(alpha++, alpha, alpha, 255), true);
//				System.out.println("alpha " + alpha);

				if (alpha > 255) alpha = -255;

//				for (int i = 0; i < driver.getMaterialRendererCount() ; i++)
//				{
//					System.out.println("driver + " + i + ":" + driver.getMaterialRenderer(i).isTransparent());
//				}

//				driver.draw2DRectangle(new SColor(25, 255, 255, 255),
//				  							  new recti(10, 10, 600, 200));


				// draw transparent rect under cursor
				position2di m = device.getCursorControl().getPosition();
				driver.draw2DRectangle(new SColor(100, 155, 155, 155),
				  							  new recti(m.getX() - 20, m.getY() - 20, m.getX() + 20, m.getY() + 20));

				driver.endScene();

				try
				{
					Thread.sleep(5);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();  //To change body of catch statement use Options | File Templates.
				}
			}
		}

		/*
		That's all, it was not really difficult, I hope.
		*/

		device.drop();

		System.exit(0);
	}
}