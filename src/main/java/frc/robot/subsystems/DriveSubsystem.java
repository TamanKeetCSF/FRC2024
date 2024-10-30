
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private CANSparkMax m_leftLeader = new CANSparkMax(9, MotorType.kBrushless);
  private CANSparkMax m_leftFollower = new CANSparkMax(8, MotorType.kBrushless);
  //private final TalonSRX m_leftLeader = new TalonSRX(11);
  //private final TalonSRX m_leftFollower = new TalonSRX(9);

  // The motors on the right side of the drive.
  private CANSparkMax m_rightLeader = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax m_rightFollower = new CANSparkMax(3, MotorType.kBrushless);

  //private final TalonSRX m_rightLeader = new TalonSRX(13);
  //private final TalonSRX m_rightFollower = new TalonSRX(12);

  
	public void setMotors(double left, double right) {
    left = scaleLeft(left);
    right = scaleRight(right);
    
    setMotorsRaw(left, right);
  }
  
  public void setMotorsRaw(double left, double right) {
    double velocidadleft = safetyTest(left);
    double velocidadright = safetyTest(right);

    //m_leftLeader.set(ControlMode.PercentOutput, -velocidadleft);
    //m_leftFollower.set(ControlMode.PercentOutput, -velocidadleft);
    //m_rightLeader.set(ControlMode.PercentOutput, velocidadright);	
    //m_rightFollower.set(ControlMode.PercentOutput, velocidadright);

    m_leftLeader.set(-velocidadleft);
    m_leftFollower.set(-velocidadleft);
    m_rightLeader.set(velocidadright);	
    m_rightFollower.set(velocidadright);
    //System.out.println("left: " + velocidadleft);
    //System.out.println("right:" + velocidadright);
}
  
  private double safetyTest(double motorValue) {
      motorValue = (motorValue < -1) ? -1 : motorValue;
      motorValue = (motorValue > 1) ? 1 : motorValue;
      
      return motorValue;
  }
  
  private double scaleLeft(double left) {
    return 0.5 * left;
  }
  
  private double scaleRight(double right) {
    return 0.5 * right;
  }

  
}



