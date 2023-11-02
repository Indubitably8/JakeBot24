package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class IntakeSystem extends XModule {

//JOHN CODE NEW INTAKE SERVOS
    // motors being used

    public DcMotor IntakeMotor;
    public Servo leftIntake;
    public Servo rightIntake;

    boolean down = false;

    public void intakeServo() {
        if(down){
            leftIntake.setPosition(0);
            rightIntake.setPosition(0);
            down = true;
        } else {
            leftIntake.setPosition(0);
            rightIntake.setPosition(0);
            down = false;
        }
    }

    double power = 1;

    public IntakeSystem(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        IntakeMotor = opMode.hardwareMap.dcMotor.get("IntakeMotor");

    }

    public void loop() {

        if (xGamepad2().right_trigger > .5){
            IntakeMotor.setPower(power);
        }
        else {
            IntakeMotor.setPower(0.0);
        }

        //TEMPORARY BUTTON MAPPING: PLEASE CHANGE
        /*if (xGamepad2().x.wasPressed()){
            //intakeServo();
        }*/

    }
}

/*
x - intake
 */