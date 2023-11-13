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
            //DOWN
            leftWrist.setPosition(.02);
            rightWrist.setPosition(.98);
            leftArm.setPosition(.440);
            rightArm.setPosition(.471);




            arm = true;
        }
        else {
            //UP
            leftWrist.setPosition(.95);
            rightWrist.setPosition(.15);
            leftArm.setPosition(.313);
            rightArm.setPosition(0.661);


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
            blockServo.setPosition(.1);
            blocked = true;
        }
        else {
            blockServo.setPosition(.6);
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
        blockServo = opMode.hardwareMap.servo.get("blockServo");

        leftWrist.setPosition(.02);
        rightWrist.setPosition(.98);
        leftArm.setPosition(.445);
        rightArm.setPosition(.490);
    }

    public void loop() {
        // button presses, calls methods

            if (xGamepad2().b.wasPressed()) {
                toggleBlock();
            }
        if (xGamepad2().y.wasPressed()) {

        }
        if (xGamepad2().a.wasPressed()) {
           moveArm();
        }
    }
}
