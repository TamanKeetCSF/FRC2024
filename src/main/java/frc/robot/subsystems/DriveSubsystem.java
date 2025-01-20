
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
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private CANSparkMax m_leftLeader = new CANSparkMax(9, MotorType.kBrushless);
  private CANSparkMax m_leftFollower = new CANSparkMax(8, MotorType.kBrushless);

  RelativeEncoder EncoderL = m_leftLeader.getEncoder();    
  RelativeEncoder EncoderL2 = m_leftFollower.getEncoder();


  //private final TalonSRX m_leftLeader = new TalonSRX(11);
  //private final TalonSRX m_leftFollower = new TalonSRX(9);
 




  // The motors on the right side of the drive.
  private CANSparkMax m_rightLeader = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax m_rightFollower = new CANSparkMax(3, MotorType.kBrushless);
  RelativeEncoder EncoderR = m_rightLeader.getEncoder();
  RelativeEncoder EncoderR2 = m_rightFollower.getEncoder();
  

  //private final TalonSRX m_rightLeader = new TalonSRX(13);
  //private final TalonSRX m_rightFollower = new TalonSRX(12);


  public void encoderReset(){
    m_rightLeader.restoreFactoryDefaults();
    m_rightFollower.restoreFactoryDefaults();
    m_leftFollower.restoreFactoryDefaults();
    m_leftLeader.restoreFactoryDefaults();
}

  
	public void setMotors(double left, double right, int polaridad) {
    left = scaleLeft(left);
    right = scaleRight(right);
    
    setMotorsRaw(left, right, polaridad);
  }
  
  public void setMotorsRaw(double left, double right, int polaridad ) {
    double velocidadleft = safetyTest(left);
    double velocidadright = safetyTest(right);

    //m_leftLeader.set(ControlMode.PercentOutput, -velocidadleft);
    //m_leftFollower.set(ControlMode.PercentOutput, -velocidadleft);
    //m_rightLeader.set(ControlMode.PercentOutput, velocidadright);	
    //m_rightFollower.set(ControlMode.PercentOutput, velocidadright);

    m_leftLeader.set(velocidadleft);
    m_leftFollower.set(velocidadleft);
    m_rightLeader.set(velocidadright * polaridad );	
    m_rightFollower.set(-velocidadright);
    //System.out.println("left: " + velocidadleft);
    //System.out.println("right:" + velocidadright);
}
  
  private double safetyTest(double motorValue) {
      motorValue = (motorValue < -1) ? -1 : motorValue;
      motorValue = (motorValue > 1) ? 1 : motorValue;
      
      return motorValue;
  }
  
  private double scaleLeft(double left) {
    return 0.8 * left;
  }
  
  private double scaleRight(double right) {
    return 0.8 * right;
  }

  //Encoders

  // The left-side drive encoder



  public void resetEncoders() {
    EncoderL.setPosition(0);
     EncoderR.setPosition(0);
     EncoderL2.setPosition(0);
     EncoderR2.setPosition(0);
  }
  
  //public void arcadeDrive(double fwd, double rot) {
    //m_drive.arcadeDrive(fwd, rot);
  //}

  public double getAverageEncoderDistance() {
    double encoderIzq1 = valorAbsoluto(EncoderL.getPosition());
    double encoderIzq2 = valorAbsoluto(EncoderL2.getPosition());
    double encoderDer1 = valorAbsoluto(EncoderR.getPosition());
    double encoderDer2 = valorAbsoluto(EncoderR2.getPosition());
    return (encoderIzq1 + encoderIzq2 + encoderDer1 + encoderDer2) / 4;

    //return Math.abs(m_rightLeader.getEncoder());
  }

  public double getLeftEncoder() {
    double encoderIzq1 = valorAbsoluto(EncoderL.getPosition());
    double encoderIzq2 = valorAbsoluto(EncoderL2.getPosition());
    return (encoderIzq1 + encoderIzq2) / 2;

    //return Math.abs(m_rightLeader.getEncoder());
  }

  public double getRightEncoder() {
    double encoderDer1 = valorAbsoluto(EncoderR.getPosition());
    double encoderDer2 = valorAbsoluto(EncoderR2.getPosition());
    return (encoderDer1 + encoderDer2) / 2;

    //return Math.abs(m_rightLeader.getEncoder());
  }

  public double valorAbsoluto(double val){
    if (val < 0){
      return -val;
    }
    else{
      return val;
    }
  }


  
}



