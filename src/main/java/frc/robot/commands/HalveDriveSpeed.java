package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

public class HalveDriveSpeed extends Command {
    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;

    /**
     * Creates a new HalveDriveSpeed.
     *
     * @param subsystem The drive subsystem this command will run on.
     * @param forward The control input for driving forwards/backwards
     * @param rotation The control input for turning
     */
    public HalveDriveSpeed(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
        m_drive = subsystem;
        m_forward = forward;
        m_rotation = rotation;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        // Get original drive power
        double leftPower = m_forward.getAsDouble() + m_rotation.getAsDouble();
        double rightPower = m_forward.getAsDouble() - m_rotation.getAsDouble();

        // Scale down the power by 50%
        leftPower *= 0.5;
        rightPower *= 0.5;

        // Adding a range of the joysticks in which the robot will not respond
        leftPower = (Math.abs(leftPower) < 0.07) ? 0 : leftPower;
        rightPower = (Math.abs(rightPower) < 0.07) ? 0 : rightPower;

        // Set motors with the scaled power
        //System.out.println("34");
        RobotContainer.m_robotDrive.setMotors(leftPower, rightPower);
    }
}