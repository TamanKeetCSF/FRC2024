package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.pneumatics;

public class RetractPistonCommand extends InstantCommand {
    private final pneumatics m_pneumatics;

    public RetractPistonCommand(pneumatics pneumatics) {
        this.m_pneumatics = pneumatics;
        addRequirements(pneumatics);
    }

    @Override
    public void initialize() {
        m_pneumatics.bajarShooter();
    }
}