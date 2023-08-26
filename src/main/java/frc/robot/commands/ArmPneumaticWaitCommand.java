// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticsSubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
  * Moves lower arm between retracted and extended configurations
  * 
  * The delays for pneumatic commands are for timing purposes.  When pneumatic commands
  * fire, the command ends immediately since the command is sent to the controller which
  * then commands the valves to open and the pneumatic cylinders to go to the commanded position.
  * However, as the pneumatics are physical systems, it takes some time for the pneumantics
  * to actuate.  If there are other commands which are scripted to run sequentially after 
  * the pneumatics transition and those commands require the pneumatics to be in that other
  * state in order to work properly, there must be some mechanism to delay this command from 
  * finishing until you know the state of the robot is appropriate for the next command to complete.
  * In this command, we do that with a wait command which was determined experimentally.
  * Other methods could include adding a sensor to detect when the pneumatic transition has completed.
  * If this is done, it would likely be good to add a sensor to the pneumatic subsystem command 
  * so that the subsystem could be queried to see if it is in the proper state.  Note that 
  * care should be taken when doing this and possibly a backup timeout should be implemented
  * otherwise if the sensor is faulty and does not detect the transition, then the robot doesn't
  * get stuck waiting for a sensor to trigger which will never trigger since it is faulted.
 */
public class ArmPneumaticWaitCommand extends CommandBase {
  PneumaticsSubsystem m_PneumaticsSubsystem;
  public boolean bExtend;

  Timer m_ticktock = new Timer(); 
  double m_startTime;
  double m_endTime;
  double m_delay_retract = 1.0; // seconds, retract vs extend can require different times to execute.
  double m_delay_extend = 1.5;  // seconds, retract vs extend can require different times to execute.
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
