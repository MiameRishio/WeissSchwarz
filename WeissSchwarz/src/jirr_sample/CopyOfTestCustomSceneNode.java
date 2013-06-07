package jirr_sample;
import net.sf.jirr.*;

// Java port (c) 2005 by S.Dingfelder

public class CopyOfTestCustomSceneNode
{
	static
	{
		System.loadLibrary("irrlicht_wrap");
	}

	public static final SColor S_COLOR = new SColor(0, 100, 100, 100);

/*
This Tutorial is a tutorial for more advanced developers.
If you are currently just playing around with the Irrlicht
engine, please look at other examples first.
This tutorials shows how to create a custom scene node and
how to use it in the engine. A custom scene node is needed,
if you want to implement a render technique, the Irrlicht
Engine is currently not supporting. For example you can write
a indoor portal based renderer or a advanced terrain scene
node with it. With creating custom scene nodes, you can
easily extend the Irrlicht Engine and adapt it to your
needs.

I will keep the tutorial simple: Keep everything very
short, everything in one .cpp file, and I'll use the engine
here as in all other tutorials.

To start, I include the header files, use the irr namespace,
and tell the linker to link with the .lib file.
*/

/*
Here comes the most sophisticated part of this tutorial:
The class of our very own custom scene node. To keep it simple,
our scene node will not be an indoor portal renderer nor a terrain
scene node, but a simple tetraeder, a 3d object consiting of 4
connected vertices, which only draws itself and does nothing more.

To let our scene node be able to be inserted into the Irrlicht
Engine scene, the class we create needs only be derived from the
ISceneNode class and has to override some methods.
*/

	class CSampleSceneNode extends ISceneNode
	{
		/*
		First, we declare some member variables. Some space to
		hold data for our tetraeder: The bounding box, 4 vertices, and
		the material of the tetraeder.
		*/
		aabbox3df Box = new aabbox3df(0, 0, 0, 0, 0, 0);
		S3DVertex Vertices[] = new S3DVertex[4];
		SMaterial Material[] = new SMaterial[]{new SMaterial()};

		ISceneManager SceneManager;

		/*
		The parameters of the constructor specify the parent of the scene node,
		a pointer to the scene manager, and an id of the scene node.
		In the constructor itself, we call the parent classes constructor,
		set some properties of the material we use to draw the scene nodem and
		create the 4 vertices of the tetraeder we will draw later.
		*/

		public CSampleSceneNode(ISceneNode parent, ISceneManager mgr, int id)
		{
			super(parent, mgr, id);
			SceneManager = mgr;

			Material[0].setFlag(E_MATERIAL_FLAG.EMF_WIREFRAME, false);
			Material[0].setFlag(E_MATERIAL_FLAG.EMF_LIGHTING, false);
			Material[0].setFlag(E_MATERIAL_FLAG.EMF_GOURAUD_SHADING, false);
			//Material.setMaterialType(E_MATERIAL_TYPE.EMT_TRANSPARENT_VERTEX_ALPHA);
			getMaterial(0).setTexture(0, SceneManager.getVideoDriver().getTexture("33100449_p0.jpg"));
			
			Vertices[0] = new S3DVertex(-3.5f, 5.f, 0, 0, 0, -1, new SColor(0, 255, 255, 255), 0, 0);
			Vertices[1] = new S3DVertex(3.5f, 5.f, 0, 0, 0, -1, new SColor(0, 255, 255, 255), 1, 0);
			Vertices[2] = new S3DVertex(-3.5f, -5.f, 0, 0, 0, -1, new SColor(0, 255, 255, 255), 0, 1);
			Vertices[3] = new S3DVertex(3.5f, -5.f, 0, 0, 0, -1, new SColor(0, 255, 255, 255), 1, 1);

			/*
			The Irrlicht Engine needs to know the bounding box of your scene node.
			It will use it for doing automatic culling and other things. Hence we
			need to create a bounding box from the 4 vertices we use.
			If you do not want the engine to use the box for automatic culling,
			and/or don't want to create the box, you could also write
			AutomaticCullingEnabled = false;.
			*/
			Box.reset(Vertices[0].getPos());
			for (int i = 1; i < 4; ++i)
				Box.addInternalPoint(Vertices[i].getPos());

			//setAutomaticCulling(false);
		}

		/** only one material here - though you are free to use an array
		 *
		 * @param l index
		 * @return requesed SMaterial
		 */
		public SMaterial getMaterial(long l)
		{
			return Material[(int)l];
		}

		/*
		Before it is drawn, the OnPreRender() method of every scene node in the scene
		is called by the scene manager. If the scene node wishes to draw itself,
		it may register itself in the scene manager to be drawn. This is necessary to
		tell the scene manager when it should call the ::render method. For example
		normal scene nodes render their content one after another, while
		stencil buffer shadows would like to be drawn after all other scene nodes. And
		camera or light scene nodes need to be rendered before all other scene
		nodes (if at all).
		So here we simply register the scene node to get render normally. If we would like
		to let it be rendered like cameras or light, we would have to call
		SceneManager.registerNodeForRendering(this, SNRT_LIGHT_AND_CAMERA);
		After this, we call the OnPreRender-method of the base class ISceneNode,
		which simply lets also all the child scene nodes of this node register themselves.
		*/
		public void OnRegisterSceneNode()
		{
			///    todo remove block - for testing only
			/*
			System.out.println("" + getMaterial(0).getMaterialType().swigValue());
			ICameraSceneNode cam = SceneManager.getActiveCamera();
			aabbox3df tbox = getBoundingBox();
			getAbsoluteTransformation().transformBox(tbox);
         boolean bla = tbox.intersectsWithBox(cam.getViewFrustrum().getBoundingBox());
			IMaterialRenderer rnd =
					SceneManager.getVideoDriver().getMaterialRenderer(getMaterial(0).getMaterialType().swigValue());
					*/
			///

			if (isVisible())
			{
				SceneManager.registerNodeForRendering(this, E_SCENE_NODE_RENDER_PASS.ESNRP_TRANSPARENT);
			}

			//super.OnPreRender(); // stackoverflow due to directors
			OnRegisterSceneNodeJava();
		}

		/*
		In the render() method most of the interesting stuff happenes: The
		Scene node renders itself. We override this method and draw the
		tetraeder.
		*/
		public void render()
		{
			int indices[] = {0, 1, 2, 1, 3, 2};
			IVideoDriver driver = SceneManager.getVideoDriver();

			driver.setMaterial(Material[0]);
			driver.setTransform(E_TRANSFORMATION_STATE.ETS_WORLD, getAbsoluteTransformation());
			driver.drawIndexedTriangleList(Vertices, 3, indices, 2);
		}

		/*
		At least, we create three small additional methods.
		GetBoundingBox() returns the bounding box of this scene node,
		GetMaterialCount() returns the amount of materials in this scene node
		(our tetraeder only has one material), and getMaterial() returns the
		material at an index. Because we have only one material here, we can
		return the only one meterial, assuming that no one ever calls getMaterial()
		with an index greater than 0.
		*/
		public aabbox3df getBoundingBoxConst()
		{
			return Box;
		}

		public long getMaterialCount()
		{
			return Material.length;
		}
	}

/*
That's it. The Scene node is done. Now we simply have to start
the engine, create the scene node and a camera, and look at the result.
*/
	public static void main(String args[])
	{
		CopyOfTestCustomSceneNode testCustomSceneNode = new CopyOfTestCustomSceneNode();
		testCustomSceneNode.run();
	}

	public void run()
	{
		// create engine and camera

		IrrlichtDevice device = Jirr.createDevice(E_DRIVER_TYPE.EDT_OPENGL, new dimension2di(640, 480), 16, false, false, false, null);
		device.setWindowCaption("Custom Scene Node @Java - Irrlicht Engine");

		IVideoDriver driver = device.getVideoDriver();
		ISceneManager smgr = device.getSceneManager();

		smgr.addCameraSceneNode(null, new vector3df(0, 0, -10), new vector3df(0, 0, 0));

		/*
		Create our scene node. Note that it is dropped (.drop()) instantly after
		we create it. This is possible because the scene manager now takes
		care of it. This is not nessecary, it would also be possible to drop it
		at the end of the program.
		*/

		CSampleSceneNode myNode = new CSampleSceneNode(smgr.getRootSceneNode(), smgr, 666);
		//myNode.drop();

		/*
		To animate something in this boring scene consisting only of one tetraeder,
		and to show, that you now can use your scene node like any other scene
		node in the engine, we add an animator to the scene node, which rotates
		the node a little bit.
		*/

		/*
		ISceneNodeAnimator anim = smgr.createRotationAnimator(new vector3df(0, 0, -0.8f));
		myNode.addAnimator(anim);
		anim.drop();
		*/
		
		/*
		Now draw everything and finish.
		*/

		String str;
		int fps, lastFPS = 0;
		
		long t0 = System.currentTimeMillis();
		while (device.run())
		{
			driver.beginScene(true, true, S_COLOR);
			float sc = (float) (System.currentTimeMillis() - t0) / 3000;
			myNode.setScale(new vector3df(sc, sc, sc));
			smgr.drawAll();

			driver.endScene();
			
			// display frames per second in window title
			fps = driver.getFPS();
			if (lastFPS != fps)
			{
				str = "Custom Scene Node @Java - Irrlicht Engine [" + driver.getName() + "] FPS:" + fps;
         	device.setWindowCaption(str);
				lastFPS = fps;
			}
		}

		device.drop();
	}
}
