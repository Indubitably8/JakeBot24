package robotx.opmodes.autonomous.OdomAutons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.modules.ArmSystem;
import robotx.modules.IntakeSystem;
import robotx.modules.LiftMotors;
import robotx.modules.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.*;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.*;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;


@Autonomous(name = "OdomTester", group = "Default")

public class OdomTester extends LinearOpMode {
    MecanumDrive mecanumDrive;
    ArmSystem armSystem;
    LiftMotors liftMotors;
    IntakeSystem intakeSystem;

    @Override
    public void runOpMode() {

        //Text at bottom of phone
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        mecanumDrive = new MecanumDrive(this);
        mecanumDrive.init();

        armSystem = new ArmSystem(this);
        armSystem.init();

        intakeSystem = new IntakeSystem(this);
        intakeSystem.init();

        liftMotors = new LiftMotors(this);
        liftMotors.init();

        //odomSystem.start();
        mecanumDrive.start();
        armSystem.start();
        intakeSystem.start();
        liftMotors.start();

        mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        telemetry.addData(">", "Press Play to Start Op Mode");
        telemetry.update();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        int sleepTime = 1000;

        Pose2d startingPosition = new Pose2d(14.5, -62.75, Math.toRadians(90.00));
        /* TrajectorySequence fullAuton = drive.trajectorySequenceBuilder(startingPosition.plus((new Pose2d(0, -0.1, 0))))
                .lineToLinearHeading((startingPosition.plus(new Pose2d(0, -0.1, 0))).plus(new Pose2d(0, -24, 0)))
                .build(); */
        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(5)
                .build();


        drive.setPoseEstimate(startingPosition);

        waitForStart();

        drive.followTrajectory(myTrajectory);

        if (isStopRequested()) return;
        while (!isStopRequested() && opModeIsActive());
    }
}