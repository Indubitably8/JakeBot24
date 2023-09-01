package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.modules.MecanumDrive;
import robotx.modules.OdomSystem;
import robotx.modules.OrientationDrive;

 @Autonomous(name = "RightSideNoDetection", group = "Default")
 
 public class RobotXAutonomous2021 extends LinearOpMode {

     //private ElapsedTime runtime = new ElapsedTime();

     //Modules being imported
     MecanumDrive mecanumDrive;
     OrientationDrive orientationDrive;
     OdomSystem odomSystem;

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

         odomSystem.start();
         mecanumDrive.start();
         orientationDrive.start();

         mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

         telemetry.addData(">", "Press Play to Start Op Mode");
         telemetry.update();


         double power = 0.5;
         int sleepTime = 1000;

         waitForStart();

         //runtime.reset();

         if (opModeIsActive()) {
             sleep(2000);
             DriveForward(1, 100);
             sleep(sleepTime);
             StrafeLeft(1, 100);
             sleep(sleepTime);
             DriveForward(-1, 100 );
             sleep(sleepTime);
;            StrafeRight(1, 100);


         }
     }


     public void DriveForward(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);  //top left when rev is down and ducky wheel is right
         mecanumDrive.frontRight.setPower(power); //bottom left
         mecanumDrive.backLeft.setPower(power);   //top right
         mecanumDrive.backRight.setPower(-power); // bottom right
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DriveBackward(double power, int time) {
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

     public void StrafeRight(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void StrafeLeft(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(power);
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
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void TurnRight(double power, int time) {
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

 }
