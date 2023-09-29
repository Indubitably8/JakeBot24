package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class ArmSystem extends XModule {

    //var setup
    public Servo leftArm;
    public Servo rightArm;
    public Servo leftWrist;
    public Servo rightWrist;
    public Servo blockServo;

    boolean arm = false;
    boolean wrist = false;
    boolean blocked = false;



    //methods are built into one button as a toggle

    public void moveArm() {
        if (!arm) {
            leftArm.setPosition(.36);
            rightArm.setPosition(.696);
            leftWrist.setPosition(.36);
            rightWrist.setPosition(.696);
            arm = true;
        }
        else {
            leftArm.setPosition(0.62);
            rightArm.setPosition(0.42);
            leftWrist.setPosition(0.62);
            rightWrist.setPosition(0.42);
            arm = false;
        }
    }

    public void moveWrist() {
        if (!wrist) {
            leftWrist.setPosition(.36);
            rightWrist.setPosition(.696);
            wrist = true;
        }
        else {
            leftWrist.setPosition(0.62);
            rightWrist.setPosition(0.42);
            wrist = false;
        }
    }

    public void toggleBlock() {
        if (!blocked) {
            blockServo.setPosition(.36);
            blocked = true;
        }
        else {
            blockServo.setPosition(0.62);
            blocked = false;
        }
    }




    public ArmSystem(OpMode op) {
        super(op);
    }

    public void init() {
        // pulls servos from configs
        leftArm = opMode.hardwareMap.servo.get("leftArm");
        rightArm = opMode.hardwareMap.servo.get("rightArm");
        leftWrist = opMode.hardwareMap.servo.get("leftWrist");
        rightWrist = opMode.hardwareMap.servo.get("rightWrist");
    }

    public void loop() {
        // button presses, calls methods

            if (xGamepad1().b.wasPressed()) {
                if(!wrist){
                    moveArm();
                }
            }
        if (xGamepad1().y.wasPressed()) {
            if (arm) {
                moveWrist();
            }
        }
        if (xGamepad1().a.wasPressed()) {
            if (wrist) {
                toggleBlock();
            }
        }
    }
}
