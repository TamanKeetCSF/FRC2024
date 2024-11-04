// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDrive extends Command {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;
  public BooleanSupplier m_speeder;
  public BooleanSupplier m_partDrive;
  public BooleanSupplier m_fullDrive;
  int polaridad = 1;

  private boolean DriveModePressed = false;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param speeder
   * @param partDrive
   * @param fullDrive
   * @param forward The control input for driving forwards/backwards
   * @param rotation
   */
  public DefaultDrive(DriveSubsystem subsystem, BooleanSupplier speeder, BooleanSupplier partDrive,BooleanSupplier fullDrive ,DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = subsystem;
    m_speeder = speeder;
    m_partDrive = partDrive;
    m_fullDrive = fullDrive;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
  } 
  @Override
  public void execute() {
      double leftPower;
      double rightPower;

      if (m_partDrive.getAsBoolean()){
         DriveModePressed = true;
      }
      else if (m_fullDrive.getAsBoolean()){
         DriveModePressed = false;
      }


      if (DriveModePressed){
        polaridad = -1; 
      }
      else {
         polaridad = 1;
      }
      

        // Add a range of the joysticks in which the robot will not respond
         leftPower = m_forward.getAsDouble() + (m_rotation.getAsDouble() * 0.9);
         rightPower = m_forward.getAsDouble() - (m_rotation.getAsDouble() * 0.9);
        
        
        // If the speeder button is pressed, reduce speed by half
        if (m_speeder.getAsBoolean()) {
            leftPower /= 5;
            rightPower /= 5;
        }
        leftPower = (Math.abs(leftPower) < 0.1) ? 0 : leftPower;
        rightPower = (Math.abs(rightPower) < 0.1) ? 0 : rightPower;
        
        // Set motor powers
        //System.out.println(m_drive.getAverageEncoderDistance());
        RobotContainer.m_robotDrive.setMotors(leftPower, rightPower, polaridad);
    
  }
}