// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveGiro extends Command {
  private final DriveSubsystem m_drive;
  private final double m_giro;
  private final double m_speed;

/**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  /** Creates a new DriveGiro. */
  public DriveGiro(double grados, double speed, DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_giro = grados;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_giro > 0){
      m_drive.setMotorsRaw(.1, -0.1);
    }
    else{
      m_drive.setMotorsRaw(-.1, 0.1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.setMotorsRaw(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_drive.getLeftEncoder();
    m_drive.getRightEncoder();
    

    return m_drive.getAverageEncoderDistance() == (m_giro / 1.869);
  }
}
