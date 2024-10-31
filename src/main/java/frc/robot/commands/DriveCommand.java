// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */
  public final Drive m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;
  double stopTime;

  public DriveCommand(Drive subsystem, DoubleSupplier forward, DoubleSupplier rotation, double stopTime) {
    this.m_drive = subsystem;
    this.m_forward = forward;
    this.m_rotation = rotation;
    this.stopTime = stopTime;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(stopTime == 0){
      m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
    }
    else if(Robot.timer.get() <= stopTime){
      m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
