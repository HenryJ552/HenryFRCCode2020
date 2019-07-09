/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Henry's Drive Subsystem
//Last edited: 7/9/19

package frc.robot.subsystems;

//Installing needed configurions 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StickDrive;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class DriveSubsystem extends Subsystem {

  //Configuring the 6 drive motor controllers
  final public WPI_TalonSRX
    rightMotor1 = new WPI_TalonSRX(RobotMap.rightMotor1),
    rightMotor2 = new WPI_TalonSRX(RobotMap.rightMotor2),
    rightMotor3 = new WPI_TalonSRX(RobotMap.rightMotor3),
    leftMotor4 = new WPI_TalonSRX(RobotMap.leftMotor4),
    leftMotor5 = new WPI_TalonSRX(RobotMap.leftMotor5),
    leftMotor6 = new WPI_TalonSRX(RobotMap.leftMotor6);

  public DriveSubsystem() {
    super();
    
    //Restores all motor configurations to their known factory default state
    rightMotor1.configFactoryDefault();
    rightMotor2.configFactoryDefault();
    rightMotor3.configFactoryDefault();
    leftMotor4.configFactoryDefault();
    leftMotor5.configFactoryDefault();
    leftMotor6.configFactoryDefault();

    //Telling motors 2&3 and 5&6 to follow their respective master motor
    rightMotor2.follow(rightMotor1);
    rightMotor3.follow(rightMotor1);
    leftMotor5.follow(leftMotor4);
    leftMotor6.follow(leftMotor4);

    //Makes sure that all the motors are spinning the correct direction
    rightMotor2.setInverted(InvertType.FollowMaster);
    rightMotor3.setInverted(InvertType.FollowMaster);
    leftMotor5.setInverted(InvertType.FollowMaster);
    leftMotor6.setInverted(InvertType.FollowMaster);

    //Tells the motors to brake if not receiving power
    setNeutralMode(NeutralMode.Brake);

    //Invert the right side motors so that the robot drives straight
    rightMotor1.setInverted(InvertType.InvertMotorOutput);

    //Configures encoders
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMotor4.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  //Method to set the zero power behavior as either break or float
  public void setNeutralMode(NeutralMode mode) {
    rightMotor1.setNeutralMode(mode);
    rightMotor2.setNeutralMode(mode);
    rightMotor3.setNeutralMode(mode);
    leftMotor4.setNeutralMode(mode);
    leftMotor5.setNeutralMode(mode);
    leftMotor6.setNeutralMode(mode);
  }

  //Setting the defualt command for the robot to run 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new StickDrive());
  }
    
  //Method to set motor powers based on controller input
  public void setMotorPowers(double forward, double rotate) {
    double max = Math.abs(forward) + Math.abs(rotate);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
    rightMotor1.set(scale * (forward + rotate));
    leftMotor4.set(scale * (forward - rotate));
  }

  //Method to stop all motors
  public void stop() {
    rightMotor1.set(0);
    leftMotor4.set(0);
  }

  //Method to drive forward
  public void forward(double motorPower) {
    setMotorPowers(motorPower,0);
  }

  //Method to turn
  public void turn(double turnSpeed) {
    setMotorPowers(0, turnSpeed);
  }
}

