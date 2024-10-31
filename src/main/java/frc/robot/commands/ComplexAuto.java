// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.outake;
import frc.robot.subsystems.pneumatics;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class ComplexAuto extends SequentialCommandGroup {
  /**ss
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   */
  public ComplexAuto(DriveSubsystem drive, outake m_outake, pneumatics m_Pneumatics, intake m_intake) {
    addCommands(
    
    new InstantCommand(() -> drive.encoderReset(), drive) ,
    new speakerCommand(m_outake, m_Pneumatics, 3500),
    new InstantCommand(() -> m_intake.activateIntake(), m_intake),
    new DriveDistance(39.37, 0.1, drive),
    new WaitCommand(0.5),
    new InstantCommand(() -> m_intake.activateIntake(), m_intake),
    new DriveDistance(39.37, 0.1, drive));





        
  }
}
