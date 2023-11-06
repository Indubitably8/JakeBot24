package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class IntakeSystem extends XModule {

    // motors being used

    public DcMotor IntakeMotor;
    public Servo leftIntake;
    public Servo rightIntake;

    double power = 1;

    public IntakeSystem(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        IntakeMotor = opMode.hardwareMap.dcMotor.get("IntakeMotor");
        leftIntake = opMode.hardwareMap.servo.get("leftIntake");
        rightIntake = opMode.hardwareMap.servo.get("rightIntake");
    }

    public void loop() {

        if(xGamepad2().x.isDown()  || xGamepad1().x.isDown()) {
            IntakeMotor.setPower(power);
        }
        else{
            IntakeMotor.setPower(0);
        }
        leftIntake.setPosition(0);
        rightIntake.setPosition(0);
    }
}