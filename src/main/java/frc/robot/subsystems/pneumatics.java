// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class pneumatics extends SubsystemBase {
  /** Creates a new pneumatics. */
  
    PneumaticHub m_pH = new PneumaticHub(20);
    DoubleSolenoid empujador= m_pH.makeDoubleSolenoid(0, 1);

    
        public void estirarShooter(){
            empujador.set(Value.kForward);
            System.out.println("extiende");
        }
        public void bajarShooter(){
            empujador.set(Value.kReverse);
            System.out.println("desextiende");

        }
       
    
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
