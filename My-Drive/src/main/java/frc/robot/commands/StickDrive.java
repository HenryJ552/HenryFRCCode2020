/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Henry's Drive Command
//Last edited: 7/9/19

package frc.robot.commands;

//Installing needed configurions 
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;



public class StickDrive extends Command {
  public StickDrive() {
    //Letting the robot know that we need access to the drivetrain in order to run this command
     requires(Robot.m_drive);
  }

  //Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Starting the robot in brake mode
    Robot.m_drive.setNeutralMode(NeutralMode.Brake);  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Accessing the joystick from the OI configuration
    Joystick stick = Robot.getOI().stick;

    //Accsessing the needed joystick value needed to drive the robot
    double stickY = -stick.getY();
    double twist = -stick.getTwist();

    //Telemetry for debugging 
    SmartDashboard.putString("DB/String 0", String.format("joystickY: %4.31f", stick.getY()));
    SmartDashboard.putString("DB/String 1", String.format("joystickTwist: %4.31f", stick.getTwist()));
   
    //Assigning motors powers based on the given joystick values
    Robot.m_drive.setMotorPowers(stickY,twist);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Cut power to the drive motors once the robot has been stopped
    Robot.m_drive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
