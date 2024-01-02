// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;;
public class Wrist extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public enum WristState{
    OFF,
    JOG,
    POSITION,
    ZERO
  }

  public Wrist() {
    configMotors();
  }

  CANSparkMax m_master = new CANSparkMax(MotorConstants.kWRIST_MASTER, MotorType.kBrushless);
  CANSparkMax m_slave = new CANSparkMax(MotorConstants.kWRIST_SLAVE, MotorType.kBrushless);

  AbsoluteEncoder m_Encoder = m_master.getAbsoluteEncoder(Type.kDutyCycle);

  WristState m_state = WristState.OFF;

  double m_jogValue = 0;
  Rotation2d m_setpoint = new Rotation2d();

  private static Wrist m_instance = new Wrist();
  
  public static Wrist getInstance(){
    return m_instance;
  }


  public void configMotors(){
    m_slave.follow(m_master);

    m_master.restoreFactoryDefaults();
    m_slave.restoreFactoryDefaults();

    m_master.setInverted(true);
    m_slave.setInverted(m_master.getInverted());

    m_master.setIdleMode(IdleMode.kBrake);
    m_slave.setIdleMode(m_master.getIdleMode());

    m_master.setSmartCurrentLimit(40, 40);
    m_slave.setSmartCurrentLimit(40, 40);
  }

  public void set(double value){
    m_master.set(value);
  }

  @Override
  public void periodic() {
    switch(m_state){
      case OFF:
        // set(0);
        break;
      case JOG:
        // set(m_jogValue);
        break;
      case POSITION:
        // goToSetpoint();
        break;
      case ZERO:
        break;
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
