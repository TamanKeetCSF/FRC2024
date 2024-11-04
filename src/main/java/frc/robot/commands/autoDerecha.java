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
public class autoDerecha extends SequentialCommandGroup {
  /**ss
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   */
  public autoDerecha(DriveSubsystem drive, outake m_outake, pneumatics m_Pneumatics, intake m_intake) {
    addCommands(
    // Centro
    new InstantCommand(() -> drive.encoderReset(), drive) ,
    new speakerCommand(m_outake, m_Pneumatics, 3900),
    new DriveDistance(35, -0.2, drive)/* ,
    new DriveGiro(48, -0.2, drive),
    new InstantCommand(() -> m_intake.activateIntake(), m_intake),
    new DriveDistance(210, -0.18, drive),
    new WaitCommand(0.5),
    new InstantCommand(() -> m_intake.stopIntake(), m_intake),
    new DriveDistance(200, 0.4, drive),
    new DriveGiro(48, 0.2, drive),
    new DriveDistance(44, 0.3, drive),
    new WaitCommand(0.4),
    new speakerCommand(m_outake, m_Pneumatics, 3900),
    new DriveDistance(30, -0.2, drive),
    new DriveGiro(48, -0.2, drive),
    new DriveDistance(250, -0.42, drive)*/

    
    );
        
  }
}