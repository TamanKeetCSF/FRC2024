// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.outake;

public class RunOutakeToSpeedCommand extends Command {
    private final outake m_outake;
    private final double targetSpeed;

    public RunOutakeToSpeedCommand(outake outake, double speed) {
        this.m_outake = outake;
        this.targetSpeed = speed;
        addRequirements(outake);
    }

    @Override
    public void initialize() {
        m_outake.outakeSpeeker(); // Start motors at desired speed
    }

    @Override
    public boolean isFinished() {
        double currentSpeed = m_outake.getAverageSpeed();
        return currentSpeed >= targetSpeed;
    }

    @Override
    public void end(boolean interrupted) {
        // Optionally keep running or stop the motors
        // m_outake.stopOutake(); // Uncomment if you want to stop the motors
    }
}
