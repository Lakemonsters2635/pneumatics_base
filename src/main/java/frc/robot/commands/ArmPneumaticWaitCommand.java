// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticsSubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
  * Moves lower arm between retracted and extended configurations
 */
public class ArmPneumaticWaitCommand extends CommandBase {
  PneumaticsSubsystem m_PneumaticsSubsystem;
  public boolean bExtend;

  Timer m_ticktock = new Timer(); 
  double m_startTime;
  double m_endTime;
  double m_delay_retract = 1.0; // seconds // TODO: THIS IS HORRIBLE IT SUCKS COPYING
  double m_delay_extend = 1.5; // TODO
  boolean is_extending; 

  /** Creates a new IntakePneumaticCommand. */
  public ArmPneumaticWaitCommand(PneumaticsSubsystem PneumaticsSubsystem, boolean bExtend) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_PneumaticsSubsystem = PneumaticsSubsystem;
    this.bExtend = bExtend;
    
    addRequirements(m_PneumaticsSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("APC: " + bExtend);
    if(bExtend) { // if extended then retract
      is_extending = false;
      m_PneumaticsSubsystem.armExtend();
    } else { // if retracted then extend
      is_extending = true; 
      m_PneumaticsSubsystem.armRetract();
    }

    m_ticktock.start(); 
    m_startTime = m_ticktock.get(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_endTime = m_ticktock.get(); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (is_extending) {
      return m_endTime - m_startTime > m_delay_extend;
    } else {
      return m_endTime - m_startTime > m_delay_retract;
    }
  }
}
