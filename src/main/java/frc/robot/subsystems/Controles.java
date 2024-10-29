package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
public class Controles {

    private static PS4Controller ControlXbox;//
    private static PS4Controller ControlPiloto;//


    // _____ _ _ _ _ _
    // |_ _| (_) (_) | (_) (_)
    //  | | _ __ _ ___ _ __ _| |_ ______ _ ___ _ ___ _ __
    //  | | | '_ \| |/ __| |/ _` | | |_ / _` |/ __| |/ _ \| '_ \
    // _| |_| | | | | (__| | (_| | | |/ / (_| | (__| | (_) | | | |
    // |_____|_| |_|_|\___|_|\__,_|_|_/___\__,_|\___|_|\___/|_| |_|

    public Controles() {
        ControlXbox = new PS4Controller(0);//
        ControlPiloto = new PS4Controller(1); //
    }

    // -----------------------------------------------------
    // _ _ _ _
    // | | | | (_) | |
    // | | ___ _ _ ___| |_ _ ___| | __
    // _ | |/ _ \| | | / __| __| |/ __| |/ /
    // | |__| | (_) | |_| \__ \ |_| | (__| <
    // \____/ \___/ \__, |___/\__|_|\___|_|\_\
    // __/ |
    // |___/
    public boolean readJoystickButtons(int id) {
        return ControlPiloto.getRawButton(id);
    }

    public PS4Controller getControlPiloto() {
      return ControlPiloto;  // Add this getter method
  }

    public double readJoystickAxis(int axis) {
        return (ControlPiloto.getRawAxis(axis));
    }
    
    public int readJoystickDPad() { // obtiene el dpad, conocido tambien como pov
        return (ControlPiloto.getPOV());
    }
    
    // -----------------------------------------------------
    // __ __ ____
    // \ \ / / | _ \
    // \ V / ______ | |_) | _____ __
    // > < |______| | _ < / _ \ \/ /
    // / . \ | |_) | (_) > <
    // /_/ \_\ |____/ \___/_/\_\








    
    public boolean readPS4Buttons(int id) {
        return (ControlXbox.getRawButton(id));
    }

    public double readPS4Axis(int axis){
        return(ControlXbox.getRawAxis(axis));
    }

    public int readPS4DPad() { // obtiene el dpad, conocido tambien como pov
        return (ControlXbox.getPOV());
    }
    public PS4Controller getPS4() {
      return ControlXbox;  // Add this getter method
  }
}
