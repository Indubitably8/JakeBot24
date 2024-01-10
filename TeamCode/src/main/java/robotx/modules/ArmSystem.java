package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;


public class ArmSystem extends XModule {
    boolean toggle;
    //boolean toggle = true;
    //var setup
    public Servo rightClaw;
    public Servo leftClaw;

    public Servo armServo;

    boolean right = false; //so arm system knows it starts down
    boolean left = false;
    boolean arm = false;

    //methods are built into one button as a toggle

    public void lClaw(){
        if(left){
            leftClaw.setPosition(0);
            left = false;
        } else {
            leftClaw.setPosition(0);
            left = true;
        }
    }

    public void rClaw(){
        if(right){
            rightClaw.setPosition(0);
            right = false;
        } else {
            rightClaw.setPosition(0);
            right = true;
        }
    }

    public void moveArm(){
        if(arm){
            armServo.setPosition(0);
            arm = false;
        } else {
            armServo.setPosition(0);
            arm = true;
        }
    }

    public ArmSystem(OpMode op) {
        super(op);
    }
    public void init() {
        // pulls servos from configs
        leftClaw = opMode.hardwareMap.servo.get("leftClaw");
        rightClaw = opMode.hardwareMap.servo.get("rightClaw");
        armServo = opMode.hardwareMap.servo.get("armServo");

        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
        armServo.setPosition(0);
    }

    public void loop() {
        toggle = robotx.modules.ToggleMode.toggle;
        if(toggle) {
            if (xGamepad1().b.wasPressed()) {
                rClaw();
            }
            if (xGamepad1().a.wasPressed()) {
                lClaw();
            }
            if (xGamepad1().x.wasPressed()) {
                moveArm();
            }
        } else {
            if (xGamepad2().b.wasPressed()) {
                rClaw();
            }
            if (xGamepad2().a.wasPressed()) {
                lClaw();
            }
            if (xGamepad2().x.wasPressed()) {
                moveArm();
            }
        }
    }
}
