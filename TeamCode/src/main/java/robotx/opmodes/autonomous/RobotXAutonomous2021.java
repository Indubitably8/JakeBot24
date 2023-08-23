package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.modules.ClawSystem;
import robotx.modules.LiftMotors;
import robotx.modules.MecanumDrive;
import robotx.modules.OdomSystem;
import robotx.modules.OrientationDrive;

 @Autonomous(name = "RightSideNoDetection", group = "Default")
 
 public class RobotXAutonomous2021 extends LinearOpMode {

     //private ElapsedTime runtime = new ElapsedTime();

     //Modules being imported
     MecanumDrive mecanumDrive;
     OrientationDrive orientationDrive;
     ClawSystem clawSystem;
     LiftMotors liftMotors;
     OdomSystem odomSystem;

     @Override

     public void runOpMode() {

         //Text at bottom of phone
         telemetry.addData("Status", "Initialized");
         telemetry.update();

         mecanumDrive = new MecanumDrive(this);
         mecanumDrive.init();

         clawSystem = new ClawSystem(this);
         clawSystem.init();

         liftMotors = new LiftMotors(this);
         liftMotors.init();

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

             // all a backup is commented out
//About 13.3V
             DriveForward(1,150);
             sleep(sleepTime);
             StrafeLeft(1,150);
             //13.6
             // *each foam square is about 1 power for 300ms*
             //IMPORTANT! If code not working, make sure robot using battery of about 13V
             //Try to get battery around 13V (+-.5)
/*
             moveClawDown(100);
             sleep(sleepTime);
             closeClaw(100);
             sleep(sleepTime);
             rawLift(1, 15);
             sleep(sleepTime);

             // moves us forward three squares
             DriveForward(.7, 1240); //changed, increased
             sleep(sleepTime);
             StrafeLeft(1,375);
             sleep(sleepTime);

             // lift up
             rawLift(1,475);
             sleep(sleepTime);
             moveClawUp(60);
             sleep(sleepTime);

             // drop cone
             openClaw(50);
             sleep(sleepTime);
             closeClaw(50);
             sleep(sleepTime);

             // lift down
             moveClawDown(60);
             sleep(sleepTime);
             rawLower(1,200);

             sleep(sleepTime);

             // hot fix at comp to make it park in a spot, remove later bc strafe already included in SideConeFromPole
             StrafeRight(1, 375);

             //SideConeFromPole(sleepTime);


            /* //Resets back to starting orientation
             StrafeRight(1, 400);
             DriveBackward(1, 400);
             sleep(sleepTime);
             TurnLeft(1, 900);
             sleep(sleepTime);
             //Moving to original orientation, then parking point location
                //Instead of this, just make last turn 300 less ms
             TurnRight(1,300);
             sleep(sleepTime);
             DriveBackward(1,450);
                //Add code to decide which zone to go to
             sleep(sleepTime);
             //if zone 1
             StrafeLeft(1,400);
             //if zone 2
                //Do nothing
             //if zone 3
             //StrafeRight(1, 400);
             sleep(10000);

             */


            //Park based on OpenCV

         }
     }


     //Controls
     double cmCheck = 4.2;

     public void SideConeFromPole(int sleepTime){



         //Second cone
         //Driving to cone stack
         StrafeRight(1, 400);
         sleep(sleepTime);
         TurnLeft(.7, 380);
         sleep(sleepTime);
         openClaw(100);
         sleep(sleepTime);
         DriveBackward(.5, 800); // adjust to make bot not go to middle of square
         sleep(sleepTime);

         //need to figure out raising the lift here

         // Grab cone here

         //Drive back to pole
         DriveForward(.7, 600);
         sleep(sleepTime);
         TurnRight(.7, 380);
         //Will have to do math later, but repeat for however many times possible, then do more math to determine change in distance to align for grabbing cone
         sleep(sleepTime);
         StrafeLeft(1, 375);
         sleep(sleepTime);

         // up
         closeClaw(100);
         sleep(sleepTime);
         rawLift(1,475);
         sleep(sleepTime);
         moveClawUp(50);
         sleep(sleepTime);

         openClaw(50);
         sleep(sleepTime);
         closeClaw(50);
         sleep(sleepTime);



         //down
         moveClawDown(50);
         sleep(sleepTime);
         rawLower(1,400);
         sleep(sleepTime);

     }
/*
     public void  colorCheck(){
         for(int i = 0; i<3000; i++) {
             sleep(1);
             if ((((DistanceSensor) liftMotors.colorSensor).getDistance(DistanceUnit.CM)) < cmCheck) {
                 closeClaw(100);

             } else {
                 rawLower(.1, 100);

             }
         }
     }
*/
     public void DriveToWall(double power){

         DriveForward(power,1000);

         for (int i = 0; i < 4000; i++){
             sleep(1);
             if(liftMotors.touch.isPressed()){
                 break;
             }
             else {
                 DriveForward(.1,5);

             }
         }
     }

     public void odomsUp(int time){
         odomSystem.odom1.setPosition(0.46);
         odomSystem.odom1.setPosition(0.11);
         odomSystem.odom1.setPosition(0.46);
         sleep(time);
     }
     public void odomsDown(int time){
         odomSystem.odom1.setPosition(0.1);
         odomSystem.odom1.setPosition(0.77);
         odomSystem.odom1.setPosition(0.21);
         sleep(time);
     }
     public void rawLower(double power, int time) {
         liftMotors.LeftLift1.setPower(-power);
         liftMotors.LeftLift2.setPower(-power);
         liftMotors.RightLift1.setPower(power);
         liftMotors.RightLift2.setPower(power);
         sleep(time);
         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);
     }

     public void GearLockOpen(){

         liftMotors.gearServo.setPosition(.65);
         sleep(20);

     }

     public void Touch(){

         mecanumDrive.frontLeft.setPower(0.5);
         mecanumDrive.frontRight.setPower(-0.5);
         mecanumDrive.backLeft.setPower(-0.5);
         mecanumDrive.backRight.setPower(0.5);

         while (getRuntime() < 5000 && ! odomSystem.touchSensor.getState()){ idle();}

         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);

     }

     public void GearLockClose(){

         liftMotors.gearServo.setPosition(.4);
         sleep(20);

     }

     public void rawLift(double power, int time) {
         liftMotors.LeftLift1.setPower(power);
         liftMotors.LeftLift2.setPower(power);
         liftMotors.RightLift1.setPower(-power);
         liftMotors.RightLift2.setPower(-power);
         sleep(time);
         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);
     }

     /*public void LiftLowLevel(){

             liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

             liftMotors.LeftLift1.setTargetPosition(200);
             liftMotors.LeftLift2.setTargetPosition(200);
             liftMotors.RightLift1.setTargetPosition(200);
             liftMotors.RightLift2.setTargetPosition(200);

             liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

             liftMotors.LeftLift1.setPower(0.7);
             liftMotors.LeftLift2.setPower(0.7);
             liftMotors.RightLift1.setPower(0.7);
             liftMotors.RightLift2.setPower(0.7);

             while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy())
             {

             }

             liftMotors.LeftLift1.setPower(0);
             liftMotors.LeftLift2.setPower(0);
             liftMotors.RightLift1.setPower(0);
             liftMotors.RightLift2.setPower(0);

             liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
             liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
             liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
             liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         }

     public void LowerLowLevel(){

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         liftMotors.LeftLift1.setTargetPosition(-200);
         liftMotors.LeftLift2.setTargetPosition(-200);
         liftMotors.RightLift1.setTargetPosition(-200);
         liftMotors.RightLift2.setTargetPosition(-200);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         liftMotors.LeftLift1.setPower(-0.7);
         liftMotors.LeftLift2.setPower(-0.7);
         liftMotors.RightLift1.setPower(-0.7);
         liftMotors.RightLift2.setPower(-0.7);

         while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy())
         {

         }

         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }
 */
     public void LiftMidLevel() {

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         liftMotors.LeftLift1.setTargetPosition(290);
         liftMotors.LeftLift2.setTargetPosition(290);
         liftMotors.RightLift1.setTargetPosition(290);
         liftMotors.RightLift2.setTargetPosition(290);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         liftMotors.LeftLift1.setPower(0.7);
         liftMotors.LeftLift2.setPower(0.7);
         liftMotors.RightLift1.setPower(0.7);
         liftMotors.RightLift2.setPower(0.7);

         while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy()) {

         }

         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }

     public void LowerMidLevel() {

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         liftMotors.LeftLift1.setTargetPosition(-290);
         liftMotors.LeftLift2.setTargetPosition(-290);
         liftMotors.RightLift1.setTargetPosition(-290);
         liftMotors.RightLift2.setTargetPosition(-290);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         liftMotors.LeftLift1.setPower(-0.7);
         liftMotors.LeftLift2.setPower(-0.7);
         liftMotors.RightLift1.setPower(-0.7);
         liftMotors.RightLift2.setPower(-0.7);

         while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy()) {

         }

         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }

     public void LiftHighLevel() {

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         liftMotors.LeftLift1.setTargetPosition(1200);
         liftMotors.LeftLift2.setTargetPosition(1200);
         liftMotors.RightLift1.setTargetPosition(1200);
         liftMotors.RightLift2.setTargetPosition(1200);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         liftMotors.LeftLift1.setPower(1);
         liftMotors.LeftLift2.setPower(1);
         liftMotors.RightLift1.setPower(1);
         liftMotors.RightLift2.setPower(1);

         while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy()) {

         }

         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }

     public void LowerHighLevel() {

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         liftMotors.LeftLift1.setTargetPosition(-420);
         liftMotors.LeftLift2.setTargetPosition(-420);
         liftMotors.RightLift1.setTargetPosition(-420);
         liftMotors.RightLift2.setTargetPosition(-420);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         liftMotors.LeftLift1.setPower(-0.7);
         liftMotors.LeftLift2.setPower(-0.7);
         liftMotors.RightLift1.setPower(-0.7);
         liftMotors.RightLift2.setPower(-0.7);

         while (liftMotors.LeftLift1.isBusy() && liftMotors.LeftLift2.isBusy() && liftMotors.RightLift1.isBusy() && liftMotors.RightLift2.isBusy()) {

         }

         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);

         liftMotors.LeftLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.LeftLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         liftMotors.RightLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }

     public void moveClawDown(int time) {
         clawSystem.leftArm.setPosition(0.35);
         clawSystem.rightArm.setPosition(0.706);
         sleep(time);

     }

     public void moveClawUp(int time) {
         clawSystem.leftArm.setPosition(0.616);
         clawSystem.rightArm.setPosition(0.436);
         sleep(time);

     }

     public void openClaw(int time) {
         clawSystem.claw.setPosition(0.3);
         sleep(time);

     }

     public void closeClaw(int time) {
         clawSystem.claw.setPosition(0.6);
         sleep(time);

     }

     public void liftLift(double power, int time) {
         liftMotors.LeftLift1.setPower(power);
         liftMotors.LeftLift2.setPower(power);
         liftMotors.RightLift1.setPower(power);
         liftMotors.RightLift2.setPower(power);
         sleep(time);
         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);
     }

     public void lowerLift(double power, int time) {
         liftMotors.LeftLift1.setPower(-power);
         liftMotors.LeftLift2.setPower(-power);
         liftMotors.RightLift1.setPower(-power);
         liftMotors.RightLift2.setPower(-power);
         sleep(time);
         liftMotors.LeftLift1.setPower(0);
         liftMotors.LeftLift2.setPower(0);
         liftMotors.RightLift1.setPower(0);
         liftMotors.RightLift2.setPower(0);
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
