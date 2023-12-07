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

    double rightArmPos = .698;
    double leftArmPos = .286;

    double rightWristPos = .946;
    double leftWristPos = .154;

    boolean arm = true; //so arm system knows it starts down
    boolean wrist = true;
    boolean blocked = true;


    int k = 0;
    //methods are built into one button as a toggle

    public void moveArm() {
        if (k == 0) {
            //DOWN
            leftWrist.setPosition(leftWristPos);
            rightWrist.setPosition(rightWristPos);
            leftArm.setPosition(leftArmPos);
            rightArm.setPosition(rightArmPos);
            k++;
        }

        else if (k == 1){
            leftWrist.setPosition((.98+leftWristPos)/2);
            rightWrist.setPosition((.02+rightWristPos)/2);
            k++;
        }

        else{
            //UP
            leftWrist.setPosition(.98);
            rightWrist.setPosition(.02);
            leftArm.setPosition(.542);
            rightArm.setPosition(0.53);
            k=k-2;
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

        //leftWrist.setDirection(Servo.Direction.REVERSE);
        //rightWrist.setDirection(Servo.Direction.REVERSE);
        leftArm.setDirection(Servo.Direction.REVERSE);
        rightArm.setDirection(Servo.Direction.REVERSE);

        leftWrist.setPosition(leftWristPos);
        rightWrist.setPosition(rightWristPos);
        leftArm.setPosition(leftArmPos);
        rightArm.setPosition(rightArmPos);

        blockServo.setPosition(.6);
    }

    public void loop() {
        // button presses, calls methods

        if (xGamepad2().b.wasPressed()) {
                toggleBlock();
        }
        if (xGamepad2().y.wasPressed()) {

            leftWrist.setPosition(leftWristPos);
            rightWrist.setPosition(rightWristPos);
            leftArm.setPosition(leftArmPos);
            rightArm.setPosition(rightArmPos);

            k=0;


        }
        if (xGamepad2().a.wasPressed()) {
           moveArm();
        }
    }
}
