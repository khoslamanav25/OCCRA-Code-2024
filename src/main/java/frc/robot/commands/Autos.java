// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public static Command driveOutAuto(Drive driveSubsystem) {
    return new DriveCommand(driveSubsystem, () -> Constants.AutonConstants.driveOutSpeed, () -> 0, 2);
  }

  public static Command advancedAuto(Drive driveSubsystem, Shoot shootSubsystem, Intake intakeSubsystem){
    return new ParallelCommandGroup(
      new DriveCommand(driveSubsystem, () -> -Constants.AutonConstants.driveOutSpeed, () -> 0, 1.15),
      new ShootCommand(shootSubsystem, Constants.ShooterConstants.kShooterVoltage, 10),
      new SequentialCommandGroup(
        new WaitCommand(4),
        new IntakeCommand(intakeSubsystem, Constants.IntakeConstants.kIntakeVoltage, 10)
      )
    );
  }

  // public static Command advancedAuto(Drive driveSubsystem, Intake intakeSubsystem, Shoot shootSubsystem){
  //   if(Robot.timer.get() < 4){
  //     return new SequentialCommandGroup(
  //       new DriveCommand(driveSubsystem, () -> Constants.AutonConstants.driveOutSpeed, null)
  //     );
  //   }
  // }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
