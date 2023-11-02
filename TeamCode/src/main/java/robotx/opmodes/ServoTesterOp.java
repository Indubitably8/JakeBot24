package robotx.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.PressHandler;

/**
 * Created by Nicholas on 11/6/16.
 * Use to test the servo at any position.
 * Hold down the back bumpers to change the unit,
 * and press the up and down buttons on the D-Pad to increment/decrement.
 */

@TeleOp(name = "ServoTesterOp", group = "Tests")
public class ServoTesterOp extends OpMode {

    Servo testServo11;
    Servo testServo12;

    Servo testServo21;
    Servo testServo22;

    Servo testServo31;
    Servo testServo32;



    double servoPosition1;
    double servoPosition2;

    PressHandler gamepad1_dpad_up;
    PressHandler gamepad1_dpad_down;
    PressHandler gamepad1_a;
    PressHandler gamepad1_b;
    PressHandler gamepad1_y;
    PressHandler gamepad1_left_bumper;
    PressHandler gamepad1_right_bumper;

    boolean scaleEnabled = false;



    int groupNumber = 1;

    Servo activeServo1;
    Servo activeServo2;

    Servo[] servoGroup1;
    Servo[] servoGroup2;
    Servo[] servoGroup3;

    Servo[][] servoGroups;

    @Override
    public void init() {
        /*with old config
        testServo1 = hardwareMap.servo.get("testServo1");
        testServo2 = hardwareMap.servo.get("testServo2");
        */

        // with new config
        testServo11 = hardwareMap.servo.get("testServo11");
        testServo12 = hardwareMap.servo.get("testServo12");

        testServo21 = hardwareMap.servo.get("testServo21");
        testServo22 = hardwareMap.servo.get("testServo22");

        testServo31 = hardwareMap.servo.get("testServo31");
        testServo32 = hardwareMap.servo.get("testServo32");

        activeServo1 = testServo11;
        activeServo2 = testServo12;

        servoGroup1 = new Servo[]{testServo11, testServo12};
        servoGroup2 = new Servo[]{testServo21, testServo22};
        servoGroup3 = new Servo[]{testServo31, testServo32};

        servoGroups = new Servo[][]{servoGroup1, servoGroup2, servoGroup3};

        gamepad1_dpad_up = new PressHandler();
        gamepad1_dpad_down = new PressHandler();
        gamepad1_a = new PressHandler();
        gamepad1_b = new PressHandler();
        gamepad1_y = new PressHandler();
        gamepad1_left_bumper = new PressHandler();
        gamepad1_right_bumper = new PressHandler();
    }

    @Override
    public void start() {
        servoPosition1 = 0.0;
        servoPosition2 = 0.0;
    }

    @Override
    public void loop() {

        gamepad1_dpad_up.update(gamepad1.dpad_up);
        gamepad1_dpad_down.update(gamepad1.dpad_down);
        gamepad1_a.update(gamepad1.a);
        gamepad1_b.update(gamepad1.b);
        gamepad1_y.update(gamepad1.y);
        gamepad1_left_bumper.update(gamepad1.left_bumper);
        gamepad1_right_bumper.update(gamepad1.right_bumper);

        if (gamepad1_b.onPress()) {
            if (scaleEnabled) {
                activeServo1.scaleRange(0.0, 1.0);
                activeServo2.scaleRange(0.0, 1.0);
                scaleEnabled = false;
            } else {
                activeServo1.scaleRange(0.02, 0.98);
                activeServo2.scaleRange(0.02, 0.98);
                scaleEnabled = true;
            }
        }
        if (gamepad1.right_trigger >= 0.5) {
            groupNumber += 1;
            if (groupNumber >= 4) {
                groupNumber = 1;
            }
        }
        if (gamepad1.left_trigger >= 0.5) {
            groupNumber -= 1;
            if (groupNumber <= 0) {
                groupNumber = 3;
            }
        }
        double unit = 0.1;
        if (gamepad1_left_bumper.onPress()) {
            unit = 0.01;
        }
        if (gamepad1_right_bumper.onPress()) {
            unit = 0.001;
        }
        if (gamepad1_left_bumper.onPress() && gamepad1_right_bumper.onPress()) {
            unit = 0.0001;
        }
        telemetry.addData("Unit: ", unit);

        if (gamepad1_dpad_up.onPress()) {
            servoPosition1 += unit;
        }
        if (gamepad1_dpad_down.onPress()) {
            servoPosition1 -= unit;
        }
        if (gamepad1_y.onPress()) {
            servoPosition2 += unit;
        }
        if (gamepad1_a.onPress()) {
            servoPosition2 -= unit;
        }

        if (servoPosition1 > 1.0) {
            servoPosition1 = 1.0;
        }
        if (servoPosition2 > 1.0) {
            servoPosition2 = 1.0;
        }
        if (groupNumber != 1) {
            if (servoPosition1 < 0.0) {
                servoPosition1 = 0.0;
            }
            if (servoPosition2 < 0.0) {
                servoPosition2 = 0.0;
            }
        } else {
            if (servoPosition1 < -1.0) {
                servoPosition1 = -1.0;
            }
            if (servoPosition2 < -1.0) {
                servoPosition2 = -1.0;
            }
        }

        activeServo1 = servoGroups[groupNumber - 1][0];
        activeServo2 = servoGroups[groupNumber - 1][1];

        activeServo1.setPosition(servoPosition1);
        activeServo2.setPosition(servoPosition2);

        telemetry.addData("Scale Enabled?    ", scaleEnabled);
        telemetry.addData("Servo Position 1: ", servoPosition1);
        telemetry.addData("Servo Position 2: ", servoPosition2);
        telemetry.addData("Group number:     ", groupNumber);
    }

    @Override
    public void stop() {

    }

}