package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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
public abstract class AutoBlueCenter extends LinearOpMode {

    //motors
    //PasteMotors.pasteMotors();
    @Override
    public void runOpMode() throws InterruptedException {

        //init

        waitForStart();

        //code
    }
}
