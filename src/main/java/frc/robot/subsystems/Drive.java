// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {

  // Left Motors
  private final WPI_TalonSRX m_leftFront = new WPI_TalonSRX(DriveConstants.kLeftMotor2Id);
  private final WPI_TalonSRX m_leftBack = new WPI_TalonSRX(DriveConstants.kLeftMotor1Id);

  // Right Motors
  private final WPI_TalonSRX m_rightFront = new WPI_TalonSRX(DriveConstants.kRightMotor1Id);
  private final WPI_TalonSRX m_rightBack = new WPI_TalonSRX(DriveConstants.kRightMotor2Id);

  // The drive
  private final DifferentialDrive m_drive = 
    new DifferentialDrive(m_leftFront, m_rightFront);

  /** Creates a new Drive. */
  public Drive() {
    m_leftFront.setInverted(true);
    m_leftBack.setInverted(true);
    m_rightFront.setInverted(false);
    m_rightBack.setInverted(false);

    m_leftFront.setNeutralMode(NeutralMode.Brake);
    m_leftBack.setNeutralMode(NeutralMode.Brake);
    m_rightFront.setNeutralMode(NeutralMode.Brake);
    m_rightBack.setNeutralMode(NeutralMode.Brake);

    m_leftBack.follow(m_leftFront);
    m_rightBack.follow(m_rightFront);

    // will or might change do it in test pleas
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, 0.75 * rot);
  }
}
