package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class DroneLaunch extends XModule {

    //var setup
    public Servo launchArm;
    public Servo angleLaunch;

    boolean launched = false;
    boolean angled = false;


    //methods are built into one button as a toggle

    public void launch() {
        launchArm.setPosition(0.62); //start pos .96
        launched = true;
    }

    public void angle() {
        if (!angled) {
            angleLaunch.setPosition(0.14);
            angled = true;
        } else {
            angleLaunch.setPosition(0);
            angled = false;
        }
    }


    public DroneLaunch(OpMode op) {
        super(op);
    }

    public void init() {
        // pulls servos from configs
        launchArm = opMode.hardwareMap.servo.get("launchArm");
        angleLaunch = opMode.hardwareMap.servo.get("angleLaunch");
    }

    public void loop() {
        // button presses, calls methods

        if (xGamepad2().right_bumper.wasPressed()) {
            if (!launched) {
                launch();
            }
        }
        if (xGamepad2().left_bumper.wasPressed()) {
            angle();
        }
    }
}