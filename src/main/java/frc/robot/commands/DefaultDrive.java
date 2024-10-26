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

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param speeder
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDrive(DriveSubsystem subsystem, BooleanSupplier speeder,DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = subsystem;
    m_speeder = speeder;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
  } 
  @Override
  public void execute() {


    double leftPower = m_forward.getAsDouble() + m_rotation.getAsDouble();
        double rightPower = m_forward.getAsDouble() - m_rotation.getAsDouble();

        // Add a range of the joysticks in which the robot will not respond
        leftPower = (Math.abs(leftPower) < 0.07) ? 0 : leftPower;
        rightPower = (Math.abs(rightPower) < 0.07) ? 0 : rightPower;

        // If the speeder button is pressed, reduce speed by half
        if (m_speeder.getAsBoolean()) {
            leftPower /= 2;
            rightPower /= 2;
        }

        // Set motor powers
        RobotContainer.m_robotDrive.setMotors(leftPower, rightPower);
    
  }
}