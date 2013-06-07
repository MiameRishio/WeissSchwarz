package jirr_sample;

import net.sf.jirr.*;

import java.io.IOException;

/**
 This tutorial shows how to use the built in User Interface of
 the Irrlicht Engine. It will give a brief overview and show
 how to create and use windows, buttons, scroll bars, static
 texts and list boxes.

 As always, we include the header files, and use the irrlicht
 namespaces. We also store a pointer to the Irrlicht device,
 a counter variable for changing the creation position of a window,
 and a pointer to a listbox.
 */
public class TestUserInterface
{
	static
	{
		System.loadLibrary("irrlicht_wrap");
	}

	static IrrlichtDevice device = null;
	static int cnt = 0;
	static IGUIListBox listbox = null;


/*
The Event Receiver is not only capable of getting keyboard and
mouse input events, but also events of the graphical user interface
(gui). There are events for almost everything: Button click,
Listbox selection change, events that say that a element was hovered
and so on. To be able to react to some of these events, we create
an event receiver.
We only react to gui events, and if it's such an event, we get the
id of the caller (the gui element which caused the event) and get
the pointer to the gui environment.
*/
	static class MyEventReceiver extends IEventReceiver
	{
		public boolean OnEvent(SEvent event)
		{
			if (event.getEventType() == EEVENT_TYPE.EET_GUI_EVENT)
			{
				int id = event.getGUIEventCaller().getID();
				IGUIEnvironment env = device.getGUIEnvironment();


				EGUI_EVENT_TYPE egui_event_type = event.getGUIEventType();
				/*
				If a scrollbar changed its scroll position, and it is 'our'
				scrollbar (the one with id 104), then we change the
				transparency of all gui elements. This is a very easy task:
				There is a skin object, in which all color settings are stored.
				We simply go through all colors stored in the skin and change
				their alpha value.
				*/
				if (egui_event_type == EGUI_EVENT_TYPE.EGET_SCROLL_BAR_CHANGED)
				{
					if (id == 104)
					{
						// todo check if possible without using additional method
						//int pos = ((IGUIScrollBar) event.getGUIEventCaller).getPos();
						IGUIElement element = event.getGUIEventCaller();
						IGUIScrollBar scrollbar = Jirr.castToIGUIScrollBar(element);
						int pos = scrollbar.getPos();
						for (int i = 0; i < EGUI_DEFAULT_COLOR.EGDC_COUNT.swigValue(); ++i)
						{
							SColor col = env.getSkin().getColor(EGUI_DEFAULT_COLOR.swigToEnum(i));
							col.setAlpha(pos);
							env.getSkin().setColor(EGUI_DEFAULT_COLOR.swigToEnum(i), col);
						}
					}
				}
				/*
				If a button was clicked, it could be one of 'our'
				three buttons. If it is the first, we shut down the engine.
				If it is the second, we create a little window with some
				text on it. We also add a string to the list box to log
				what happened. And if it is the third button, we create
				a file open dialog, and add also this as string to the list box.
				That's all for the event receiver.
				*/
				else if (egui_event_type == EGUI_EVENT_TYPE.EGET_BUTTON_CLICKED)
				{
					if (id == 101)
					{
						device.closeDevice();
						return true;
					}

					if (id == 102)
					{
						listbox.addItem("Window created");
						cnt += 30;
						if (cnt > 200)
							cnt = 0;

						IGUIWindow window = env.addWindow(new recti(100 + cnt, 100 + cnt, 300 + cnt, 200 + cnt),
																	 false, // modal?
																	 "Test window");

						env.addStaticText("Please close me", new recti(35, 35, 140, 50),
												true, // border?
												false, // wordwrap?
												window);

						return true;
					}

					if (id == 103)
					{
						listbox.addItem("File open");
						env.addFileOpenDialog("Please choose a file.");
						return true;
					}
				}
			}

			return false;
		}
	}


/*
Ok, now for the more interesting part. First, create the
Irrlicht device. As in some examples before, we ask the user which
driver he wants to use for this example:
*/
	public static void main(String[] args)
	{
		// ask user for driver
		E_DRIVER_TYPE driverType = E_DRIVER_TYPE.EDT_DIRECT3D9;

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

		/* The creation was successful, now we set the event receiver and
			store pointers to the driver and to the gui environment. */

		MyEventReceiver receiver = new MyEventReceiver();
		device.setEventReceiver(receiver);
		device.setWindowCaption("Irrlicht Engine @Java - User Inferface Demo");

		IVideoDriver driver = device.getVideoDriver();
		IGUIEnvironment env = device.getGUIEnvironment();

		/*
		We add three buttons. The first one closes the engine. The second
		creates a window and the third opens a file open dialog. The third
		parameter is the id of the button, with which we can easily identify
		the button in the event receiver.
		*/

		env.addButton(new recti(10, 210, 100, 240), null, 101, "Quit");
		env.addButton(new recti(10, 250, 100, 290), null, 102, "New Window");
		env.addButton(new recti(10, 300, 100, 340), null, 103, "File Open");

		/*
		Now, we add a static text and a scrollbar, which modifies the
		transparency of all gui elements. We set the maximum value of
		the scrollbar to 255, because that's the maximal value for
		a color value.
		Then we create an other static text and a list box.
		*/

		env.addStaticText("Transparent Control:", new recti(150, 20, 350, 40), true);
		IGUIScrollBar scrollbar = env.addScrollBar(true, new recti(150, 45, 350, 60), null, 104);
		scrollbar.setMax(255);

		env.addStaticText("Logging ListBox:", new recti(50, 80, 250, 100), true);
		listbox = env.addListBox(new recti(50, 110, 250, 180));

		/*
		To make the font a little bit nicer, we load an external font
		and set it as new font in the skin. An at last, we create a
		nice Irrlicht Engine logo in the top left corner.
		*/

		IGUISkin skin = env.getSkin();
		IGUIFont font = env.getFont("media/fonthaettenschweiler.bmp");
		if (font != null)
			skin.setFont(font);

		/*IGUIImage img =*/ env.addImage(driver.getTexture("media/irrlichtlogoalpha.tga"), new position2di(10, 10));

		/*
		That's all, we only have to draw everything.
		*/

		while (device.run())
		{
			if (device.isWindowActive())
			{
				driver.beginScene(true, true, new SColor(0, 200, 200, 200));

				env.drawAll();

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
		
		device.drop();

		System.exit(0);
	}
}