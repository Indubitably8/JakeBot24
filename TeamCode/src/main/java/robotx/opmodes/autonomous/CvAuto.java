package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
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

import robotx.modules.MecanumDrive;
import robotx.modules.OrientationDrive;
import robotx.modules.*;

@TeleOp(name = "OpenCvV1", group = "Default")
public class CvAuto extends LinearOpMode {

    OpenCvWebcam phoneCam;
    SkystoneDeterminationPipeline pipeline;
    MecanumDrive mecanumDrive;
    OrientationDrive orientationDrive;
    OdomSystem odomSystem;

    @Override
    public void runOpMode() {

        // Setup, initialize, import modules

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        orientationDrive = new OrientationDrive(this);
        orientationDrive.init();

        mecanumDrive = new MecanumDrive(this);
        mecanumDrive.init();

        odomSystem = new OdomSystem(this);
        odomSystem.init();

        mecanumDrive.start();
        orientationDrive.start();

        mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int sleepTime = 1000;
        String coneColor = null;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        waitForStart();

        while (opModeIsActive()) {

            String propPos = "None";

            // being built for red board side

            //fix all constants

            //use pipeline.getAnalysis1() / 2

            // use a for loop is so it doesn't get stuck

            // place robot so it scans three first

            for( int i = 0; i < 2000; i++){

                //robot at three & scan
                if(pipeline.getAnalysis1() < 0 && pipeline.getAnalysis2() > 0){
                    propPos = "Three";
                    break;
                }
                sleep(1);
            }

            if(propPos.equals("None")){
                for( int i = 0; i < 2000; i++){
                    //move to two
                    StrafeLeft(0.5,200);
                    //scan
                    if(pipeline.getAnalysis1() < 0 && pipeline.getAnalysis2() > 0){
                        propPos = "Two";
                        break;
                    }
                    sleep(1);
                }
                // if not in the other two positions, is in the first one
                if(propPos.equals("None")){
                    propPos = "One";
                }
            }


            //now movements based on the scan

            if(propPos == "One"){
                //will be in pos to scan two

                //purple

                StrafeLeft(.5,200);
                DriveForward(.5,300);

                //place purple pixel

                //yellow (on backdrop)

                DriveBackward(.5,50);
                TurnRight(.5,300);
                DriveForward(.8,800);
                StrafeLeft(.5,400);

                //place yellow pixel

                //should already be parked

            }

            if(propPos == "Two"){
                //will be in pos to scan two

                //purple

                StrafeLeft(.5,200);
                DriveForward(.5,300);

                //place purple pixel

                //yellow (on backdrop)

                DriveBackward(.5,50);
                TurnRight(.5,300);
                DriveForward(.8,800);
                StrafeLeft(.5,400);

                //place yellow pixel

                //should already be parked

            }

            if(propPos == "Three"){
                //will be in pos to scan three

                //purple

                StrafeLeft(.5,200);
                DriveForward(.5,300);

                //place purple pixel

                //yellow (on backdrop)

                DriveBackward(.5,50);
                TurnRight(.5,300);
                DriveForward(.8,800);
                StrafeLeft(.5,400);

                //place yellow pixel

                //should already be parked

            }

        }
    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline {

        /*
         * Some color constants
         */

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static final int duckThreshold = 100;

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(163, 96);

        static final int REGION_WIDTH = 10;
        static final int REGION_HEIGHT = 25;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat region1_Cr;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        Mat Cr = new Mat();

        int avg1;
        int avg2;

        int avgCb;
        int avgCr;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         * -> also added the Cr Channel
         */
        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }
        void inputToCr(Mat input){
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cr, 2);
        }


        public void init(Mat firstFrame) {

            inputToCb(firstFrame);
            inputToCr(firstFrame);

            region1_Cr = Cr.submat(new Rect(region1_pointA, region1_pointB));
            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);
            inputToCr(input);

            avgCb = (int) Core.mean(region1_Cb).val[0];
            avgCr = (int) Core.mean(region1_Cr).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines


            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis1() {
            avg1 = avgCb;
            return avgCb;
        }
        public int getAnalysis2(){
            avg2 = avgCr;
            return avgCr;
        }
    }

    //Controls


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
