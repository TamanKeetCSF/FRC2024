package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveTrain_TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.outake;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

    private static RobotContainer m_robotContainer = new RobotContainer();

    // Subsystems
    public static final DriveTrain m_robotDrive = new DriveTrain();
    public final intake m_intake = new intake();
    public final outake m_outake = new outake();  // New outake subsystem

    // Joysticks
    private final XboxController xboxController1 = new XboxController(0);
    private final PS4Controller ControlPiloto = new PS4Controller(1);
    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();


        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        



        // Configure autonomous sendable chooser
        SmartDashboard.putData("Auto Mode", m_chooser);
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    /**
     * Use this method to define your button->command mappings.
     */
    private void configureButtonBindings() {


        
        m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DriveTrain_TankDrive(
            m_robotDrive,
            () -> -ControlPiloto.getRawAxis(1),
            () -> -ControlPiloto.getRawAxis(2)));


        // Intake button mappings
        final JoystickButton buttonA = new JoystickButton(xboxController1, XboxController.Button.kA.value);
        final JoystickButton buttonB = new JoystickButton(xboxController1, XboxController.Button.kB.value);

        // Outake button mappings
        final JoystickButton buttonX = new JoystickButton(xboxController1, XboxController.Button.kX.value);
        final JoystickButton buttonY = new JoystickButton(xboxController1, XboxController.Button.kY.value);

        // Button A activates the intake
        buttonA.onTrue(new InstantCommand(() -> m_intake.activateIntake(), m_intake))
           .onFalse(new InstantCommand(() -> m_intake.stopIntake(), m_intake));

        // Button B stops the intake
        buttonB.onTrue(new InstantCommand(() -> m_intake.activateDesintake(), m_intake))
           .onFalse(new InstantCommand(() -> m_intake.stopIntake(), m_intake));


        // Button X activates the speeker shooter
        buttonX.onTrue(new InstantCommand(() -> m_outake.outakeSpeeker(), m_outake))
           .onFalse(new InstantCommand(() -> m_outake.stopOutake(), m_outake));

        // Button Y stops the amp shooter
        buttonY.onTrue(new InstantCommand(() -> m_outake.outakeAmp(), m_outake))
           .onFalse(new InstantCommand(() -> m_outake.stopOutake(), m_outake));
    }

    public XboxController getXboxController1() {
        return xboxController1;
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }
}

