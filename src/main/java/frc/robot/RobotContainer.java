// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drive m_drive = new Drive();
  private final Shoot m_shoot = new Shoot();
  private final Intake m_intake = new Intake();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_drive.setDefaultCommand(new DriveCommand(m_drive, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX(), 0));
    m_intake.setDefaultCommand(new IntakeCommand(m_intake, 0, 0));
    m_shoot.setDefaultCommand(new ShootCommand(m_shoot, 0, 0));

    m_operatorController.rightTrigger().whileTrue(
      new IntakeCommand(m_intake, Constants.IntakeConstants.kIntakeVoltage, 0)
    );

    m_operatorController.leftTrigger().whileTrue(
      new ParallelCommandGroup(
        new IntakeCommand(m_intake, Constants.IntakeConstants.kOutakeVoltage, 0),
        new ShootCommand(m_shoot, -Constants.ShooterConstants.kShooterVoltage, 0)
      )
    );

    m_driverController.rightBumper().whileTrue(
      new ShootCommand(m_shoot, Constants.ShooterConstants.kShooterVoltage, 0)
    );

    m_driverController.rightTrigger().whileTrue(
      new ParallelCommandGroup(   
        new IntakeCommand(m_intake, Constants.IntakeConstants.kIntakeVoltage, 0),
        new ShootCommand(m_shoot, Constants.ShooterConstants.kShooterVoltage, 0)
      )
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.advancedAuto(m_drive, m_shoot, m_intake);
  }
}
