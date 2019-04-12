package yaw;

import clojure.lang.IFn;
import yaw.engine.Input;
import yaw.engine.KeyCallback;
import yaw.engine.World;
import yaw.engine.camera.Camera;
import yaw.engine.items.ItemObject;
import yaw.engine.meshs.MeshBuilder;
import yaw.engine.meshs.Texture;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class InputCallbackTest implements InputCallback {
    private int key;
    private int scancode;
    private int action;
    private int mods;
    private Camera camera;



    public InputCallbackTest(Camera camera){
        this.camera = camera;
    }




    public void sendKey(int key, int scancode, int action, int mods) {


        this.key=key;
        this.scancode=scancode;
        this.action=action;
        this.mods=mods;

        if(key == GLFW_KEY_UP){
            camera.translate(0,0,0.1f);
        }



    }




    public static void main(String[] args){
        World world = new World(0, 0, 800, 600);
        InputCallbackTest key = new InputCallbackTest(world.getCamera());
        world.registerKeyCallback(key);
        ItemObject cube = world.createItemObject("cube", 0f, 0f, -2f, 1.0f, MeshBuilder.generateBlock(1, 1, 1));
        cube.getMesh().getMaterial().setTexture(new Texture("/ressources/diamond.png"));
        world.getCamera().setPosition(0,0,3);
        Thread th = new Thread(world);
        th.start();


        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}



