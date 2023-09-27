package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import robotx.libraries.XModule;

public class LiftMotors extends XModule {


    // motors being used

    public DcMotor LeftLift;
    public DcMotor RightLift;

    double power = 1;
    double power2 = .6;

    public LiftMotors(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        LeftLift = opMode.hardwareMap.dcMotor.get("leftLift");
        RightLift = opMode.hardwareMap.dcMotor.get("rightLift");

    }

    public void loop() {

        if (xGamepad2().a.isDown()){
            LeftLift.setPower(power);
        }
        else if (xGamepad2().b.isDown()) {
            LeftLift.setPower(-power2);
        }
        else {
            LeftLift.setPower(0.0);
        }

        if (xGamepad2().a.isDown()){
            RightLift.setPower(power);
        }
        else if (xGamepad2().b.isDown()) {
            RightLift.setPower(-power2);
        }
        else {
            RightLift.setPower(0.0);
        }
        /*
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
         */
    }
}

/*
a - all motors same direction
b - all motors opposite direction
 */