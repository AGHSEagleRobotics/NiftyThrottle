// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;
import com.revrobotics.CANSparkMax;

public class MotorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private int counter = 0;

  private final CANSparkMax m_motor1;
  private final CANSparkMax m_motor2;

  private double m_motor1SetPoint = 0;
  private boolean m_motor1Enabled = false;

  private double m_motor2SetPoint = 0;
  private boolean m_motor2Enabled = false;

  private Supplier<Double> m_leftStickYAxis;
  private Supplier<Double> m_rightStickYAxis;

  public MotorSubsystem(CANSparkMax motor1, 
                        CANSparkMax motor2,
                        Supplier<Double> leftStickYAxis, 
                        Supplier<Double> rightStickYAxis) {
    m_motor1 = motor1;
    m_motor2 = motor2;

    m_leftStickYAxis = leftStickYAxis;
    m_rightStickYAxis = rightStickYAxis;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    counter++;

    // setting motor

    if (counter % 10 == 0) {
      double leftStick = m_leftStickYAxis.get();
      double rightStick = m_rightStickYAxis.get();

      if (leftStick > 0.5) {
        m_motor1SetPoint += 0.05;
      }

      if (rightStick > 0.5) {
        m_motor2SetPoint += 0.05;
      }

      // turning on motor

      if (!m_motor1Enabled) {
        m_motor1.set(0);
      } else {
        m_motor1.set(m_motor1SetPoint);
      }

      if (!m_motor2Enabled) {
        m_motor2.set(0);
      } else {
        m_motor2.set(m_motor2SetPoint);
      }

    }

    // printing motor
    
    if (counter % 50 == 0) {
      System.out.println(m_motor1Enabled);
      System.out.println(m_motor2Enabled);
      System.out.println(m_motor1SetPoint);
      System.out.println(m_motor2SetPoint);
    }
  }

  @Override
  public void simulationPeriodic() {  
    // This method will be called once per scheduler run during simulation
  }
}
