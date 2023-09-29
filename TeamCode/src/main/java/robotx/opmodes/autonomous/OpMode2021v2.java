package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robotx.libraries.XOpMode;
import robotx.modules.GrapplingHook;
import robotx.modules.OrientationDrive;
import robotx.modules.ArmSystem;
import robotx.modules.DroneLaunch;
import robotx.modules.IntakeSystem;
import robotx.modules.LiftMotors;

@TeleOp(name = "OpMode 23-24", group = "Default")

public class OpMode2021v2 extends XOpMode {

    OrientationDrive orientationDrive;
    ArmSystem armSystem;
    DroneLaunch droneLaunch;
    IntakeSystem intakeSystem;
    LiftMotors liftMotors;
    GrapplingHook grapplingHook;

    public void initModules() {

        super.initModules();

        orientationDrive = new OrientationDrive(this);
        activeModules.add(orientationDrive);

        armSystem = new ArmSystem(this);
        activeModules.add(armSystem);

        droneLaunch = new DroneLaunch(this);
        activeModules.add(droneLaunch);

        intakeSystem = new IntakeSystem(this);
        activeModules.add(intakeSystem);

        liftMotors = new LiftMotors(this);
        activeModules.add(liftMotors);

        grapplingHook = new GrapplingHook(this);
        activeModules.add(grapplingHook);

    }

    public void init() {
        super.init();
    }
}

/*
Controls
GamePad 1:
Joysticks to move
B to reset robot orientation
Left bumper to toggle slow mode
Right bumper to toggle super slow mode

Gamepad 2:

*/