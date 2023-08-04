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
  private DoubleSolenoid doubleSolenoid;
  private Value OPEN = Value.kReverse;
  private Value CLOSED = Value.kForward;

  public ClawPneumaticSubsystem() {
    // define the constants in the constants folder
    doubleSolenoid = new DoubleSolenoid(
      Constants.PNEUMATIC_HUB_CANID, 
      PneumaticsModuleType.CTREPCM, 
      Constants.CLOSE_CHANNEL, 
      Constants.OPEN_CHANNEL
    );
    // doubleSolenoid is initialized with Value.kOff
    // toggle doesn't work unless the state is kForward or kReverse
    // so we need to initialize it
    doubleSolenoid.set(CLOSED);
  }
  
  public boolean getIsClosed() {
    return doubleSolenoid.get() == CLOSED;
  }

  public void grabberOpen() {
		doubleSolenoid.set(OPEN);
	}

  public void togglePosition() {
    doubleSolenoid.toggle();
  }
  
	public void grabberClose() {
		doubleSolenoid.set(CLOSED);
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}