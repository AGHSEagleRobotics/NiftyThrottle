// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import com.revrobotics.*;

public class MotorSubsystem extends SubsystemBase {

  private Supplier<Double> m_leftStickYAxis;
  private Supplier<Double> m_rightStickYAxis;
  /** Creates a new ExampleSubsystem. */
  public MotorSubsystem() {
  Supplier<Double> leftStickYAxis;
  Supplier<Double> rightStickYAxis;

  leftStickYAxis = m_leftStickYAxis;
  rightStickYAxis = m_rightStickYAxis;
  
  double leftStick = leftStickYAxis.get();
  double rightStick = rightStickYAxis.get();

  leftStick = MathUtil.applyDeadband(leftStick, 0.5); 
  rightStick = MathUtil.applyDeadband(rightStick, 0.5); 
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {  
    // This method will be called once per scheduler run during simulation
  }
}
