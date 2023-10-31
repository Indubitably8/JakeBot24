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
        

        if (xGamepad2().dpad_up.isDown()){
            LeftLift.setPower(power);
            RightLift.setPower(power);
        }
        else if (xGamepad2().dpad_down.isDown()) {
            LeftLift.setPower(-power2);
            RightLift.setPower(-power2);
        }
        else {
            LeftLift.setPower(0.0);
            RightLift.setPower(0.0);
        }


        if (xGamepad2().dpad_left.isDown()){
            LeftLift.setPower(0.3);
            RightLift.setPower(0.3);
        }
        else if (xGamepad2().dpad_right.isDown()) {
            LeftLift.setPower(-0.3);
            RightLift.setPower(-0.3);
        }
        else {
            LeftLift.setPower(0.0);
            RightLift.setPower(0.0);
        }
    }
}

/*
a - all motors same direction
b - all motors opposite direction
 */