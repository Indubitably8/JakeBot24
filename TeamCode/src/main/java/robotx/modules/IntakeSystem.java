package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.libraries.XModule;

public class IntakeSystem extends XModule {


    // motors being used

    public DcMotor IntakeMotor;

    double power = 1;

    public IntakeSystem(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        IntakeMotor = opMode.hardwareMap.dcMotor.get("IntakeMotor");

    }

    public void loop() {

        if (xGamepad2().x.isDown()){
            IntakeMotor.setPower(power);
        }
        else {
            IntakeMotor.setPower(0.0);
        }

    }
}

/*
x - intake
 */