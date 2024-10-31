// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class DriveConstants {
    public static final int kLeftMotor1Id = 3; // Back Left
    public static final int kLeftMotor2Id = 2; // Front Left
    public static final int kRightMotor1Id = 0; // Front Right
    public static final int kRightMotor2Id = 1;  // Back Right
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }
  
  public static class IntakeConstants{
    public static final int kIntakeMotorId = 4;
    public static final int kIntakeVoltage = 6;
    public static final int kOutakeVoltage = -8;
  }

  public static class ShooterConstants{
    public static final int kShooterMotorId = 5;
    public static final int kShooterVoltage = 12;

    
  }

  public static class AutonConstants{
    public static final double driveOutSpeed = 0.75;
  }
}
