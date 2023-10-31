package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class DroneLaunch extends XModule {

    //var setup
    public Servo launchArm;

    boolean launched = false;

/*

    //methods are built into one button as a toggle

    public void launch() {
            launchArm.setPosition(.36);
            launched = true;
    }




    public DroneLaunch(OpMode op) {
        super(op);
    }

    public void init() {
        // pulls servos from configs
        launchArm = opMode.hardwareMap.servo.get("launchArm");
    }

    public void loop() {
        // button presses, calls methods

            if (xGamepad1().right_bumper.wasPressed()) {
                if(!launched){
                    launch();
                }
            }
    */
}
