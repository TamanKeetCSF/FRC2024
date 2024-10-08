// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  CANSparkMax leftMotor = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax rightMotor = new CANSparkMax(2, MotorType.kBrushless);

  /** Creates a new Dri(veTrain. */
  public DriveTrain() {}

  public void setMotors(double left, double right){
    left = scaleLeft(left);
    right = scaleRight(right);

    setMotorsRaw(left, right);
  }

  public void setMotorsRaw(double left, double right){
    left = safetyTest(left);
    right = safetyTest(right);

    leftMotor.set(left);
    rightMotor.set(right);
  }

  private double safetyTest(double motorValue){
    motorValue = (motorValue < -1) ? -1 : motorValue;
    motorValue = (motorValue > 1) ? 1 : motorValue;

    return motorValue;
  }

  private double scaleLeft(double left){
    return 1.0 * left;
  }

  private double scaleRight(double right){
    return 1.0 * right;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
