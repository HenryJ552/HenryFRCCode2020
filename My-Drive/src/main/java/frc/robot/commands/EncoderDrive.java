/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Henry's Encoder Drive: 7/11/19

package frc.robot.commands;

//Installing needed configurions 
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class EncoderDrive extends Command {
  public int encoderTicks;
  public EncoderDrive(int encoderTicks) {
    this.encoderTicks = encoderTicks;
    //Letting the robot know that we need access to the drive train in order to run this command
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Resetting encoders
    Robot.m_drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Access encoder readings from drive subsystem 
    int rightEnc = Robot.m_drive.readRightEncoder();
    int leftEnc = Robot.m_drive.readLeftEncoder();
    double avgEnc = (Math.abs(rightEnc) + Math.abs(leftEnc))/2;

    //Telemetry for debugging 
    SmartDashboard.putString("DB/String 2", String.format("rightEncoder: %d", rightEnc));
    SmartDashboard.putString("DB/String 3", String.format("leftEncoder: %d", leftEnc)); 
    SmartDashboard.putString("DB/String 4", String.format("avgEncoder: %4.31f", avgEnc));

    //Go forward
    Robot.m_drive.forward(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //Access encoder readings from drive subsystem 
    int rightEnc = Robot.m_drive.readRightEncoder();
    int leftEnc = Robot.m_drive.readLeftEncoder();
    double avgEnc = (Math.abs(rightEnc) + Math.abs(leftEnc))/2;

    //Check if encoder is greater than a set value and if so end this command
    if(avgEnc > encoderTicks) {
      return true;
    } else {
      return false;
    }
  
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
