// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;
import com.revrobotics.spark.SparkFlex;

public class MotorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private int counter = 0;

  private final SparkFlex m_motor1;
  private final SparkFlex m_motor2;

  private double m_motor1SetPoint = 0;
  private boolean m_motor1Enabled = false;

  private double m_motor2SetPoint = 0;
  private boolean m_motor2Enabled = false;

  private Supplier<Double> m_leftStickYAxis;
  private Supplier<Double> m_rightStickYAxis;

  public MotorSubsystem(SparkFlex motor1, 
                        SparkFlex motor2,
                        Supplier<Double> leftStickYAxis, 
                        Supplier<Double> rightStickYAxis) {
    m_motor1 = motor1;
    m_motor2 = motor2;

    m_leftStickYAxis = leftStickYAxis;
    m_rightStickYAxis = rightStickYAxis;
  }

  public void toggleMotor1() {
    m_motor1Enabled = !m_motor1Enabled;
  }

  public void toggleMotor2() {
  m_motor2Enabled = !m_motor2Enabled;
  }

  public double getRPMMotor1() {
    return m_motor1.getEncoder().getVelocity();
  }

  public double getRPMMotor2() {
    return m_motor2.getEncoder().getVelocity();
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
        m_motor1SetPoint -= 0.05;
        MathUtil.clamp(m_motor1SetPoint, -1.0, 1.0);
      }

      if (rightStick > 0.5) {
        m_motor2SetPoint -= 0.05;
        MathUtil.clamp(m_motor2SetPoint, -1.0, 1.0);
      }

      if (leftStick < -0.5) {
        m_motor1SetPoint += 0.05;
        MathUtil.clamp(m_motor1SetPoint, -1.0, 1.0);
      }

      if (rightStick < -0.5) {
        m_motor2SetPoint += 0.05;
        MathUtil.clamp(m_motor2SetPoint, -1.0, 1.0);
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

      if(m_motor1Enabled){
        System.out.print("Motor1 ON " + (int) (m_motor1SetPoint*100+(Math.signum(m_motor1SetPoint)*0.5)) + "%");
      }
      else{
        System.out.print("Motor1 OFF " + (int) (m_motor1SetPoint*100+(Math.signum(m_motor1SetPoint)*0.5)) + "%");
      }

      System.out.print("  ");

      if(m_motor2Enabled){
        System.out.print("Motor2 ON " + (int) (m_motor2SetPoint*100+(Math.signum(m_motor2SetPoint)*0.5)) + "%");
      }
      else{
        System.out.print("Motor2 OFF " + (int) (m_motor2SetPoint*100+(Math.signum(m_motor2SetPoint)*0.5)) + "%");
      }
      System.out.println();
    }
    SmartDashboard.putNumber("RPM Motor 1", getRPMMotor1());
    SmartDashboard.putNumber("RPM Motor 2", getRPMMotor2());
  }

  @Override
  public void simulationPeriodic() {  
    // This method will be called once per scheduler run during simulation
  }
}
