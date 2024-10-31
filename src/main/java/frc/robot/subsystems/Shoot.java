// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;


public class Shoot extends SubsystemBase {
  private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(Constants.ShooterConstants.kShooterMotorId);
  
  public Shoot() {
    shooterMotor.clearStickyFaults();
    shooterMotor.setInverted(true);

    shooterMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setVoltage(double voltage){
    shooterMotor.setVoltage(voltage);
  }
}
