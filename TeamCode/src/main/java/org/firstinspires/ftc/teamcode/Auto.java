package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;

/**
 * Outline of proposed autonomous redesign
 *
 * Preloads: Purple pixel in intake/outtake area, yellow pixel in claw (or perhaps miniclaw)
 * Additional note: all directions will be coded in BLUE. For RED, take the opposite of all thetas.
 * "a units" shall refer to travelling 1 full tile.
 *
 * //Scoring 20 purple pixel points
 * First, use the camera to determine where the TSE is (left 1, mid 2, right 3)
 * Go forwards a units
 * If 3, turn -pi/2
 * If 2 || 3, Outtake purple pixel
 * If 1 || 2, turn -pi/2
 * If 1, go backwards a units and outtake purple pixel
 * If 2 || 3 Go backwards a units
 *
 * //Move to other side, if necessary
 * //NOTICE: The following segment will ONLY be used when we are in the far position.
 * If in the near position, skip down to the next segment.
 * Go backwards 2a units
 *
 * //Scoring the 25 yellow pixel points
 * Go backwards b units (less than a) to approach backdrop
 * If 1, strafe right
 * If 3, strafe left
 * Release the Yellow Pixel
 *
 * //Parking NOTE: 3 OPTIONS, ParkLeft, ParkCenter, ParkRight
 * If ParkCenter, stop
 * If ParkLeft, strafe right
 * If ParkRight, strafe left
 * If !ParkCenter, go backwards a-b units
 *
 */
@Autonomous (name = "Auto")
public class Auto extends LinearOpMode {

    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor intake;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        intake = hardwareMap.get(DcMotor.class, "intake");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Say", "Hello Driver");

        waitForStart();

        move(2000,0);
        Intake(-1,1000);
        move(-200,0);
    }

    public void move(int d, int mode) {
        leftFront.setPower(.4);
        leftRear.setPower(.4);
        rightFront.setPower(.4);
        rightRear.setPower(.4);
        /** mode is to determine if the robot is
         * driving straight, strafing, or turning
         * using 0, 1, or 2 respectively for each mode
         */
        if (mode == 0) {
            leftFront.setTargetPosition(d);
            leftRear.setTargetPosition(d);
            rightFront.setTargetPosition(d);
            rightRear.setTargetPosition(d);
        }
        if (mode == 1) {
            leftFront.setTargetPosition(-d);
            leftRear.setTargetPosition(d);
            rightFront.setTargetPosition(d);
            rightRear.setTargetPosition(-d);
        }
        if (mode == 2) {
            leftFront.setTargetPosition(d);
            leftRear.setTargetPosition(d);
            rightFront.setTargetPosition(-d);
            rightRear.setTargetPosition(-d);
        }
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while ( leftRear.getCurrentPosition() != d) {
            sleep(10);
        }
        sleep(200);
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(200);
    }
    public void Intake(int p, int s) {

        intake.setPower(p);
        sleep(s);
        intake.setPower(0);
        sleep(200);
    }
}
