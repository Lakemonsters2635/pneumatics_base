// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawPneumaticSubsystem extends SubsystemBase {
  /** Creates a new CompressorSubsystem. */
  private DoubleSolenoid doubleSolenoid;
  // private boolean isClosed;

  public ClawPneumaticSubsystem() {
    // define the constants in the constants folder
    //doubleSolenoid = RobotContainer.m_pneumaticHub.makeDoubleSolenoid(Constants.CLOSE_CHANNEL, Constants.OPEN_CHANNEL);
    doubleSolenoid = new DoubleSolenoid(
      Constants.PNEUMATIC_HUB_CANID, 
      PneumaticsModuleType.CTREPCM, 
      Constants.CLOSE_CHANNEL, 
      Constants.OPEN_CHANNEL);
    // isClosed = true;
  }
  
  public boolean getIsClosed() {
    return doubleSolenoid.get() == Value.kForward;
  }

  public void grabberOpen() {
		doubleSolenoid.set(Value.kReverse);
    // isClosed = false;
	}

  public void togglePosition() {
    doubleSolenoid.set(getIsClosed() ? Value.kReverse : Value.kForward);
    // isClosed = !isClosed;
  }
  
	public void grabberClose() {
		doubleSolenoid.set(Value.kForward);
    // isClosed = true;
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}