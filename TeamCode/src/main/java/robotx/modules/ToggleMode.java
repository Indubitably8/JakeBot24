package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class ToggleMode extends XModule {
    public static boolean toggle = true;

    public ToggleMode(OpMode op) {
        super(op);
    }

    public void init() {

    }

    public void loop() {
        // button presses, calls methods
        /*
        if (xGamepad2().right_stick_button.wasPressed() || xGamepad2().left_stick_button.wasPressed()) {
            if (!launched) {
                launch();
            }
        }
         */
        if (xGamepad1().back.wasPressed()) {
            toggle = !toggle;
        }
        /*
        if (xGamepad2().left_bumper.wasPressed()) {
            angle();
        }
        */
    }
}