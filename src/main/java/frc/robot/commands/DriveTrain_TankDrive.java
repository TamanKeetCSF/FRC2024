// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DriveTrain_TankDrive extends Command {
  private final DriveTrain m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */

  public DriveTrain_TankDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);    
  }

  //public double getLeftPower() {
    //return m_forward.getAsDouble() + m_rotation.getAsDouble();
//}

//public double getRightPower() {
  //  return m_forward.getAsDouble() - m_rotation.getAsDouble();
//}



  @Override
  public void execute() {
    double leftPower = m_forward.getAsDouble() + m_rotation.getAsDouble()  ;
    double rightPower = m_forward.getAsDouble() - m_rotation.getAsDouble()  ;
    

        // Adding a range of the joysticks in which the robot will not respond
        leftPower = (Math.abs(leftPower) <  0.07)? 0 : leftPower;
        rightPower = (Math.abs(rightPower) <  0.07)? 0 : rightPower;
        
        RobotContainer.m_robotDrive.setMotorsRaw(leftPower, rightPower);
  }
}
