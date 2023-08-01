// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

  import edu.wpi.first.wpilibj.DoubleSolenoid;
  import edu.wpi.first.wpilibj.PneumaticsModuleType;
  import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
  import edu.wpi.first.wpilibj2.command.SubsystemBase;
  import frc.robot.Constants;

  public class PneumaticsSubsystem extends SubsystemBase {
    /** Creates a new CompressorSubsystem. */
    private DoubleSolenoid doubleSolenoid;
    private  boolean isExtended;

    public PneumaticsSubsystem() {
      // define the constants in the constants folder
      // doubleSolenoid = RobotContainer.m_pneumaticHub.makeDoubleSolenoid(Constants.EXTEND_CHANNEL, Constants.RETRACT_CHANNEL);
      doubleSolenoid = new DoubleSolenoid(
        Constants.PNEUMATIC_HUB_CANID, 
        PneumaticsModuleType.CTREPCM, 
        Constants.EXTEND_CHANNEL, 
        Constants.RETRACT_CHANNEL);
        isExtended = doubleSolenoid.get()==Value.kForward;
    }
  
    public boolean getIsExtended() {
      return isExtended;
    }

    public void armExtend() {
	  	doubleSolenoid.set(Value.kForward);
      isExtended = true;
	  }

	  public void armRetract() {
	  	doubleSolenoid.set(Value.kReverse);
      isExtended = false;
	  }

    public void togglePosition() {
      doubleSolenoid.set(isExtended ? Value.kForward : Value.kReverse);
      isExtended = !isExtended;
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}