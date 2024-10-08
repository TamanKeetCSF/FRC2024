package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Command for controlling the robot drive system in tank drive mode using an Xbox controller.
 */
public class DriveTrain_TankDrive extends Command{
    private final DriveTrain m_driveTrain;
    private final XboxController controller;
    private final double deadzone = 0.07;
    private final double speedScale = 1.0;

    public DriveTrain_TankDrive(DriveTrain subsystem, XboxController controller) {
        m_driveTrain = subsystem;
        this.controller = controller;
        addRequirements(m_driveTrain);  // Declare subsystem dependencies
    }

    @Override
    public void initialize() {
        m_driveTrain.setMotors(0, 0);  // Stop motors on initialization
    }

    @Override
    public void execute() {
        // Get joystick values for tank drive
        double leftPower = controller.getLeftY() * speedScale;
        double rightPower = controller.getRightY() * speedScale;

        // Apply deadzone to avoid accidental movement
        leftPower = (Math.abs(leftPower) < deadzone) ? 0 : leftPower;
        rightPower = (Math.abs(rightPower) < deadzone) ? 0 : rightPower;

        // Set the motors with scaled power
        m_driveTrain.setMotors(leftPower, rightPower);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setMotors(0, 0);  // Stop motors when the command ends
    }

    @Override
    public boolean isFinished() {
        return false;  // Run until interrupted
    }
}

