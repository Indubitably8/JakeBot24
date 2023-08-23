package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import robotx.libraries.XModule;

public class LiftMotors extends XModule {


    // motors being used

    public DcMotor LeftLift1;
    public DcMotor LeftLift2;

    public DcMotor RightLift1;
    public DcMotor RightLift2;

    public Servo gearServo;

    double power = 1;
    double power2 = .6;
    boolean blocked;

    public TouchSensor touch;

    public ColorSensor colorSensor;

    public LiftMotors(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        LeftLift1 = opMode.hardwareMap.dcMotor.get("leftLift1");
        LeftLift2 = opMode.hardwareMap.dcMotor.get("leftLift2");
        RightLift1 = opMode.hardwareMap.dcMotor.get("rightLift1");
        RightLift2 = opMode.hardwareMap.dcMotor.get("rightLift2");
        gearServo = opMode.hardwareMap.servo.get("gearLockServo");

        touch = opMode.hardwareMap.touchSensor.get("touchSensor");

        colorSensor = opMode.hardwareMap.colorSensor.get("colorSensor");


    }

    public void gearLock() {
        if (!blocked) {
            gearServo.setPosition(0.5);
            blocked = true;
        }
        else {
            gearServo.setPosition(0.88);
            blocked = false;
        }
    }


    public void loop() {

        if (xGamepad2().dpad_left.wasPressed()){
            gearLock();
        }
        if (xGamepad2().a.isDown()){
            LeftLift1.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            LeftLift1.setPower(-power2);
        }

        else {
            LeftLift1.setPower(0.0);
        }

        if (xGamepad2().a.isDown()){
            LeftLift2.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            LeftLift2.setPower(-power2);
        }

        else {
            LeftLift2.setPower(0.0);
        }
        if (xGamepad2().a.isDown()){
            RightLift1.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            RightLift1.setPower(-power2);
        }

        else {
            RightLift1.setPower(0.0);
        }

        if (xGamepad2().a.isDown()){
            RightLift2.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            RightLift2.setPower(-power2);
        }

        else {
            RightLift2.setPower(0.0);
        }
        if (xGamepad2().dpad_up.isDown()){
            LeftLift1.setPower(.4);
        }

        else if (xGamepad2().dpad_down.isDown()) {
            LeftLift1.setPower(-.4);
        }

        else {
            LeftLift1.setPower(0.0);
        }

        if (xGamepad2().dpad_up.isDown()){
            LeftLift2.setPower(.4);
        }

        else if (xGamepad2().dpad_down.isDown()) {
            LeftLift2.setPower(-.4);
        }

        else {
            LeftLift2.setPower(0.0);
        }
        if (xGamepad2().dpad_up.isDown()){
            RightLift1.setPower(.4);
        }

        else if (xGamepad2().dpad_down.isDown()) {
            RightLift1.setPower(-.4);
        }

        else {
            RightLift1.setPower(0.0);
        }

        if (xGamepad2().dpad_up.isDown()){
            RightLift2.setPower(.4);
        }

        else if (xGamepad2().dpad_down.isDown()) {
            RightLift2.setPower(-.4);
        }

        else {
            RightLift2.setPower(0.0);
        }
    }
}

/*
a - all motors same direction
b - all motors opposite direction
 */