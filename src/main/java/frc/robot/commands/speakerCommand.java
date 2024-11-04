package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import frc.robot.subsystems.outake;
import frc.robot.subsystems.pneumatics;

public class speakerCommand extends SequentialCommandGroup {
    public speakerCommand(outake m_outake, pneumatics m_pneumatics, double targetSpeed) {
        addCommands(
            // Run the outake motors continuously
            new InstantCommand(() -> m_outake.outakeSpeeker(), m_outake),
            // Wait until the motors reach the target speed
            //new WaitUntilCommand(() -> m_outake.getAverageSpeed() >= targetSpeed),
            new ParallelRaceGroup(
                new WaitUntilCommand(() -> m_outake.getAverageSpeed() >= targetSpeed), // Condition 1
                new WaitCommand(1.4) // Condition 2
            ),
             
            // Extend the piston
            new InstantCommand(() -> m_pneumatics.estirarShooter(), m_pneumatics),
            // Wait for 1 second
            new WaitCommand(1.0),
            // Retract the piston
            new InstantCommand(() -> m_pneumatics.bajarShooter(), m_pneumatics),
            new WaitCommand(1.0),
            // Optionally, stop the outake motors after the piston retracts
            new InstantCommand(() -> m_outake.stopOutake(), m_outake)
        );
    }
}
