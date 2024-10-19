// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.pneumatics;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ExtendPistonCommand extends InstantCommand {
    private final pneumatics m_pneumatics;

    public ExtendPistonCommand(pneumatics pneumatics) {
        this.m_pneumatics = pneumatics;
        addRequirements(pneumatics);
    }

    @Override
    public void initialize() {
        m_pneumatics.estirarShooter();
    }
}


