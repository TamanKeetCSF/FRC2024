package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem for controlling the intake mechanism using CAN with VictorSPX controllers.
 */
public class intake extends SubsystemBase {
    // Motor declarations
    private CANSparkMax intkArriba = new CANSparkMax(6, MotorType.kBrushed);
    private CANSparkMax intkAbajo = new CANSparkMax(5, MotorType.kBrushed);  // Replace with the actual CAN ID for MotorArriba
    
    // Replace with the actual CAN ID for MotorAbajo

    public intake() {
        //intkArriba.setInverted(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Methods to control the intake motors
    // Métodos en el subsistema intake
    public void activateIntake() {
        intkArriba.set(-0.3);
        intkAbajo.set(0.3);
        System.out.println("intake activated");
    }

    public void activateDesintake() {
        intkArriba.set(0.3); // Activar en reversa
        intkAbajo.set(-0.3); // Activar en reversa
        System.out.println("Desintake activated");
    }

    public void stopIntake() {
        intkArriba.set(0); // Activar en reversa
        intkAbajo.set(0); // Activar en reversa
        System.out.println("intake stoped");
    }
}
