// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  // TalonSRX

  private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.IntakeConstants.kIntakeMotorId);

  public Intake() {
    intakeMotor.clearStickyFaults();
    intakeMotor.setInverted(false);

    intakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setVoltage(double voltage){
    intakeMotor.setVoltage(voltage);
  }
}
