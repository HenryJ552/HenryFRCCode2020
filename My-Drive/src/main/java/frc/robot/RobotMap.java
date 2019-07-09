/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Henry's Configuration File
//Last edited: 7/9/19

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Configuring motor ports and names
  //Motor 1 and motor 4 are masters, the rest are slaves
  public static int
    rightMotor1 = 1,
    rightMotor2 = 2,
    rightMotor3 = 3,
    leftMotor4 = 4,
    leftMotor5 = 5,
    leftMotor6 = 6;
}
