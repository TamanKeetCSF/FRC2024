// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ampCommand;
import frc.robot.commands.autoDerecha;
import frc.robot.commands.autoIzquierda;
import frc.robot.commands.speakerCommand;
import frc.robot.subsystems.Controles;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.outake;
import frc.robot.subsystems.pneumatics;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.cameraserver.CameraServer;
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
    public final pneumatics m_Pneumatics = new pneumatics();
    private final Joystick ControlMecanismos = new Joystick(1);
    

  // The autonomous routines

  // A complex auto routine that shoots,  drives forward, sucks a note, and then drives backward.
  private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_outake, m_Pneumatics, m_intake);
  private final Command m_autoIzquierda = new autoIzquierda(m_robotDrive, m_outake, m_Pneumatics, m_intake);
  private final Command m_autoDerecha = new autoDerecha(m_robotDrive, m_outake, m_Pneumatics, m_intake);

  // The driver's controller
public static final Controles control = new Controles();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {


    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // First drive
     
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive,
            () -> RobotContainer.control.getPS4().getRawButton(8),
            () -> RobotContainer.control.getPS4().getRawButton(5),
            () -> RobotContainer.control.getPS4().getRawButton(6),
            () -> ControlMecanismos.getRawAxis(1),
            () -> ControlMecanismos.getRawAxis(4)));   

  }
   


  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    

    final JoystickButton button1 = new JoystickButton(ControlMecanismos, 1); // A button
    final JoystickButton button3 = new JoystickButton(ControlMecanismos, 3); // X button
    final JoystickButton button4 = new JoystickButton(ControlMecanismos, 2);// B button

    



    //Intake
    button1.onTrue(new InstantCommand(() -> m_intake.activateIntake(), m_intake))
           .onFalse(new InstantCommand(() -> m_intake.stopIntake(), m_intake));

    //outakeS    

    //outakeCome

    button3.onTrue(new InstantCommand(() -> m_outake.outakeCome(), m_outake))
           .onFalse(new InstantCommand(() -> m_outake.stopOutake(), m_outake));

    //Desintake
    button4.onTrue(new InstantCommand(() -> m_intake.activateDesintake(), m_intake))
           .onFalse(new InstantCommand(() -> m_intake.stopIntake(), m_intake));
   
    Trigger leftTrigger = new Trigger(() -> ControlMecanismos.getRawAxis(2) > 0.5); // Left trigger
    Trigger rightTrigger = new Trigger(() -> ControlMecanismos.getRawAxis(3) > 0.5); // Right trigger

        // Bind commands to triggers
    rightTrigger.onTrue(new speakerCommand(m_outake, m_Pneumatics, 4000)); // Command for left trigger
    leftTrigger.onTrue(new ampCommand(m_outake, m_Pneumatics, 750)); // Command for right trigger
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //return m_complexAuto; // autonomo de centro
    //return m_autoIzquierda; // izquierda para azul, derecha para rojo 
    return m_autoDerecha; // derecha para azul, izquierda para rojo
  }
}
