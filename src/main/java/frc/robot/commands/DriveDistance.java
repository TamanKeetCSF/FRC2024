// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveDistance extends Command {
  private final DriveSubsystem m_drive;
  private final double m_distance;
  private final double m_speed;

  /**
   * Creates a new DriveDistance.
   *
   * @param cm The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveDistance(double cm, double speed, DriveSubsystem drive) {
    m_distance = cm;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.setMotorsRaw(m_speed, m_speed);
  }

  @Override
  public void execute() {
   m_drive.setMotorsRaw(m_speed, m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.setMotorsRaw(0, 0);
  }

  @Override
  public boolean isFinished() {
    //System.out.println(m_drive.getAverageEncoderDistance());
    return m_drive.getAverageEncoderDistance() >= (m_distance / 4.8);
  }
}
