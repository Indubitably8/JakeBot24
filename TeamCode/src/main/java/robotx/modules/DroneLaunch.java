package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class DroneLaunch extends XModule {

    //var setup
    boolean toggle;
    public Servo launchArm;
    //public Servo angleLaunch;
    boolean launched = false;
    boolean angled = false;


    //methods are built into one button as a toggle

    public void launch() {
        launchArm.setPosition(.3); //start pos .96   old launch pos .62
        launched = true;
    }

    public void angle() {
        /*
        if (!angled) {
            angleLaunch.setPosition(0.14);
            angled = true;
        } else {
            angleLaunch.setPosition(0);
            angled = false;
        }
        */
    }


    public DroneLaunch(OpMode op) {
        super(op);
    }

    public void init() {
        // pulls servos from configs
        launchArm = opMode.hardwareMap.servo.get("launchArm");
        //angleLaunch = opMode.hardwareMap.servo.get("angleLaunch");
        //angleLaunch.setPosition(0);
        launchArm.setPosition(.9574);
    }

    public void loop() {
        toggle = robotx.modules.ToggleMode.toggle;
        if(toggle) {
            if (xGamepad1().dpad_up.wasPressed()) {
                if (!launched) {
                    launch();
                }
            }
        } else {
            if (xGamepad2().right_stick_button.wasPressed() || xGamepad2().left_stick_button.wasPressed()) {
                if (!launched) {
                    launch();
                }
            }
        }
    }
}