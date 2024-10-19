package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder; // Import for encoder

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class outake extends SubsystemBase {

    private CANSparkMax outakeIzquierda = new CANSparkMax(1, MotorType.kBrushless);  
    private CANSparkMax outakeDerecha = new CANSparkMax(12, MotorType.kBrushless); 

    // Add encoders
    private RelativeEncoder encoderIzquierda = outakeIzquierda.getEncoder();
    private RelativeEncoder encoderDerecha = outakeDerecha.getEncoder();

    public outake() {
        outakeIzquierda.setInverted(true);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }

    public void outakeAmp() {
        outakeIzquierda.set(0.3);  
        outakeDerecha.set(0.3);  
        System.out.println("Outake amp");
    }

    public void outakeSpeeker() {
        outakeIzquierda.set(0.7);  
        outakeDerecha.set(0.7);  
        System.out.println("Outake Speeker");
    }

    public void stopOutake() {
        outakeIzquierda.set(0);  
        outakeDerecha.set(0);  
    }

    // Method to get average motor speed
    public double getAverageSpeed() {
        return (encoderIzquierda.getVelocity() + encoderDerecha.getVelocity()) / 2.0;
    }
}