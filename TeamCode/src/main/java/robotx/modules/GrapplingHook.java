package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class GrapplingHook extends XModule {


    // motors being used
    boolean toggle;
    public DcMotor GrappleMotor;
    public Servo Stabilizer;

    double power = 1;
    boolean stabilized = false;
    boolean motor = false;

    public GrapplingHook(OpMode op) {
        super(op);
    }

    public void init() {

        //init motors from configs

        GrappleMotor = opMode.hardwareMap.dcMotor.get("GrappleMotor");
        Stabilizer = opMode.hardwareMap.servo.get("Stabilizer");

        Stabilizer.setPosition(.26);
    }

    public void loop() {
        toggle = robotx.modules.ToggleMode.toggle;
        if(toggle) {
            GrappleMotor.setPower(0);
            if (xGamepad1().left_bumper.wasPressed()) {
                if (motor) {
                    motor = false;
                } else {
                    motor = true;
                }
                Stabilizer.setPosition(.26);
            }
            if (motor) {
                GrappleMotor.setPower(-power);
            }
            if (xGamepad1().right_bumper.isDown()) {
                GrappleMotor.setPower(power);
                Stabilizer.setPosition(.8);
            }
        } else {
            if (xGamepad2().left_bumper.wasPressed()){
                if(motor){
                    motor = false;
                } else {
                    motor = true;
                }
            } else {
                GrappleMotor.setPower(0.0);
                if(motor){
                    GrappleMotor.setPower(-power);
                }
                if(xGamepad2().right_bumper.isDown()) {
                    GrappleMotor.setPower(power);
                }
            }
        }
        /*
        if(xGamepad2().x.wasPressed()) {
            if(stabilized) {
                Stabilizer.setPosition(.8);
                stabilized = false;
            } else {
                Stabilizer.setPosition(.26);
                stabilized = true;
            }
        }
         */
    }// stabilizer .26 up, .8 down

}

/*
x - intake
 */