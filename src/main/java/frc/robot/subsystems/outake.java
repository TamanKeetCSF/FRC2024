package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder; // Import for encoder

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class outake extends SubsystemBase {

    private CANSparkMax outakeIzquierda = new CANSparkMax(11, MotorType.kBrushless);  
    private CANSparkMax outakeDerecha = new CANSparkMax(1, MotorType.kBrushless); 

    // Add encoders
    private RelativeEncoder encoderIzquierda = outakeIzquierda.getEncoder();
    private RelativeEncoder encoderDerecha = outakeDerecha.getEncoder();

    public outake() {
        outakeDerecha.setInverted(true);
        outakeIzquierda.setInverted(false);

    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }

    public void outakeAmp() {
        outakeIzquierda.set(0.11);  
        outakeDerecha.set(0.11);  
        //System.out.println("Outake amp");
    }

    public void outakeSpeeker() {
        outakeIzquierda.set(0.98);  
        outakeDerecha.set(0.98);  
        
    }

    public void outakeCome() {
        outakeIzquierda.set(-0.15);  
        outakeDerecha.set(-0.15);  
        //System.out.println(getAverageSpeed());
    }

    public void stopOutake() {
        outakeIzquierda.set(0.0);  
        outakeDerecha.set(0.0); 
        System.out.println("desactivate outake");

    }

    // Method to get average motor speed
    public double getAverageSpeed() {
        //System.out.println((Math.abs(encoderIzquierda.getVelocity()) + Math.abs(encoderDerecha.getVelocity())) / 2.0);
        return (Math.abs(encoderIzquierda.getVelocity()) + Math.abs(encoderDerecha.getVelocity())) / 2.0;
    }
}