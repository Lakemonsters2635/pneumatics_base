// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmPneumaticCommand;
import frc.robot.commands.ToggleClawPneumaticsCommand;
import frc.robot.subsystems.ClawPneumaticSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // Subsystems
  private final ClawPneumaticSubsystem m_clawPneumaticSubsystem = new ClawPneumaticSubsystem();
  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();
  // Commands
  private final ToggleClawPneumaticsCommand m_toggleClawPneumaticsCommand = new ToggleClawPneumaticsCommand(m_clawPneumaticSubsystem);
  private final ArmPneumaticCommand m_armPneumaticRetractCommand = new ArmPneumaticCommand(m_pneumaticsSubsystem, false);
  private final ArmPneumaticCommand m_armPneumaticExtendCommand = new ArmPneumaticCommand(m_pneumaticsSubsystem, true);
  // Joysticks
  public static final Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_CHANNEL);
  public static final Joystick leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_CHANNEL);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Trigger clawPneumaticButton = new JoystickButton(leftJoystick, Constants.CLAW_PNEUMATIC_BUTTON);
    Trigger armPneumaticRetractButton = new JoystickButton(rightJoystick, Constants.ARM_PNEUMATIC_RETRACT_BUTTON);
    Trigger armPneumaticExtendButton = new JoystickButton(rightJoystick, Constants.ARM_PNEUMATIC_EXTEND_BUTTON);

    clawPneumaticButton.onTrue(m_toggleClawPneumaticsCommand);
    armPneumaticRetractButton.onTrue(m_armPneumaticRetractCommand);
    armPneumaticExtendButton.onTrue(m_armPneumaticExtendCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
