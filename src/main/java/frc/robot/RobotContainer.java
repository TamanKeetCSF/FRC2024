// Import statements
package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.AutoConstants;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.OutakeAndPistonCommandGroup; // Import the new command group
import frc.robot.subsystems.Controles;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.outake;
import frc.robot.subsystems.pneumatics; // Import pneumatics subsystem
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  public static final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final intake m_intake = new intake();
  public final outake m_outake = new outake();
  public final pneumatics m_pneumatics = new pneumatics(); // Instantiate pneumatics subsystem

  // The driver's controller
  public static final Controles control = new Controles();

  // Autonomous commands and chooser
  private final Command m_simpleAuto =
      new DriveDistance(
          AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);
  private final Command m_complexAuto = new ComplexAuto(m_robotDrive);
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        new DefaultDrive(
            m_robotDrive,
            () -> -RobotContainer.control.readPS4Axis(1),
            () -> -RobotContainer.control.readPS4Axis(2)));

    // Add commands to the autonomous command chooser
    m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);
    m_chooser.addOption("Complex Auto", m_complexAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
    Shuffleboard.getTab("Drivetrain").add(m_robotDrive);

    // Log Shuffleboard events for command initialize, execute, finish, interrupt
    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandExecute(
            command ->
                Shuffleboard.addEventMarker(
                    "Command executed", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));
  }

  /**
   * Use this method to define your button->command mappings.
   */
  private void configureButtonBindings() {
    final JoystickButton button1 = new JoystickButton(control.getPS4(), 2);
    final JoystickButton button2 = new JoystickButton(control.getPS4(), 1);
    //final JoystickButton halveDriveButton = new JoystickButton(control.getPS4(), 3);

    // Halve drive speed while holding the button
    //halveDriveButton.whileTrue(
      //  new HalveDriveSpeed(
        //    m_robotDrive,
          //  () -> -control.readJoystickAxis(1),  // Forward/Backward
           // () -> -control.readJoystickAxis(2)   // Rotation
        //)
    //);

    // Intake controls
    button1.onTrue(new InstantCommand(() -> m_intake.activateIntake(), m_intake))
           .onFalse(new InstantCommand(() -> m_intake.stopIntake(), m_intake));

    // Outake with pneumatics command group
    button2.onTrue(new OutakeAndPistonCommandGroup(m_outake, m_pneumatics, 2000)); // Use desired target speed

    // You can add additional button bindings here
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
