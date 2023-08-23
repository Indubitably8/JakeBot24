package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robotx.libraries.XOpMode;
import robotx.modules.OrientationDrive;

@TeleOp(name = "OpMode 22-23", group = "Default")

public class OpMode2021v2 extends XOpMode {

    OrientationDrive orientationDrive;

    public void initModules() {

        super.initModules();

        orientationDrive = new OrientationDrive(this);
        activeModules.add(orientationDrive);


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
claw open/ claw close y
four bar movement x
lift a and b
*/