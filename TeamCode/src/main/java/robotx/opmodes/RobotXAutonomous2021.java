package robotx.opmodes;

import com.acmerobotics.roadrunner.geometry.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import robotx.modules.MecanumDrive;
import robotx.modules.OdomSystem;
import robotx.modules.OrientationDrive;
import robotx.modules.ArmSystem;
import robotx.modules.LiftMotors;

 @Autonomous(name = "MainAutonRight", group = "Default")
 
 public class RobotXAutonomous2021 extends LinearOpMode {

     //private ElapsedTime runtime = new ElapsedTime();

     //Modules being imported
     MecanumDrive mecanumDrive;
     OrientationDrive orientationDrive;
     OdomSystem odomSystem;
     ArmSystem armSystem;
     LiftMotors liftMotors;

     @Override
     public void runOpMode() {

         //Text at bottom of phone
         telemetry.addData("Status", "Initialized");
         telemetry.update();

         mecanumDrive = new MecanumDrive(this);
         mecanumDrive.init();

         orientationDrive = new OrientationDrive(this);
         orientationDrive.init();

         odomSystem = new OdomSystem(this);
         odomSystem.init();

         armSystem = new ArmSystem(this);
         armSystem.init();

         liftMotors = new LiftMotors(this);
         liftMotors.init();

         odomSystem.start();
         mecanumDrive.start();
         orientationDrive.start();
         armSystem.start();
         liftMotors.start();

         mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


         telemetry.addData(">", "Press Play to Start Op Mode");
         telemetry.update();

         SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

         Pose2d start = new Pose2d(0,0,0);
         Vector2d board = new Vector2d(0,0);

         drive.setPoseEstimate(start);

         TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(start)
                 .lineToConstantHeading(board)
                 .build();


         double power = 0.5;
         int sleepTime = 1000;

         waitForStart();

         //runtime.reset();

         if (opModeIsActive()) {
             //Function Sequence goes here

         }
     }

     public void DriveForward(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);  //top left when rev is down and ducky wheel is right
         mecanumDrive.frontRight.setPower(power); //bottom left
         mecanumDrive.backLeft.setPower(-power);   //top right
         mecanumDrive.backRight.setPower(power); // bottom right
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DriveBackward(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void StrafeRight(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void StrafeLeft(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DiagonalLeft(double power, int time){
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DiagonalRight(double power, int time){
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void TurnLeft(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void TurnRight(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void FirstLift() {
         double liftPower = 1;
         int liftTime = 100;
         liftMotors.LeftLift.setPower(liftPower);
         liftMotors.RightLift.setPower(-liftPower);
         sleep(liftTime);
         liftMotors.LeftLift.setPower(0);
         liftMotors.RightLift.setPower(0);
     }

     public void RaiseLift(double power, int time) {
         liftMotors.LeftLift.setPower(power);
         liftMotors.RightLift.setPower(-power);
         sleep(time);
         liftMotors.LeftLift.setPower(0);
         liftMotors.RightLift.setPower(0);
     }

     public void LowerLift(double power, int time) {
         liftMotors.LeftLift.setPower(-power);
         liftMotors.RightLift.setPower(power);
         sleep(time);
         liftMotors.LeftLift.setPower(0);
         liftMotors.RightLift.setPower(0);
     }

     public void OpenLeft(){
         armSystem.leftClaw.setPosition(0);
     }

     public void OpenRight(){
         armSystem.rightClaw.setPosition(0);
     }

 }
