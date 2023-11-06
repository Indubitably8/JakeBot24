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

    double rightArmPos = .48;
    double leftArmPos = .405;

    double rightWristPos = .672;
    double leftWristPos = .308;

    boolean arm = false;
    boolean wrist = false;
    boolean blocked = false;

    //methods are built into one button as a toggle

    public void moveArm() {
        if (!arm) {
            leftArm.setPosition(.15);
            rightArm.setPosition(.66);
            leftWrist.setPosition(.36);
            rightWrist.setPosition(.696);
            arm = true;
        }
        else {
            leftArm.setPosition(0.405);
            rightArm.setPosition(0.48);
            leftWrist.setPosition(0.308);
            rightWrist.setPosition(0.672);
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

        rightArm.setPosition(rightArmPos);
        leftArm.setPosition(leftArmPos);

        rightWrist.setPosition(rightWristPos);
        leftWrist.setPosition(leftWristPos);
    }

    public void loop() {
        // button presses, calls methods

            if (xGamepad2().b.wasPressed()) {
                if(!wrist){
                    //moveArm();
                }
            }
        if (xGamepad2().y.wasPressed()) {
            if (arm) {
                //moveWrist();
            }
        }
        if (xGamepad2().a.wasPressed()) {
            if (wrist) {
                //toggleBlock();
            }
        }
    }
}
