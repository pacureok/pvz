/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author Felipe
 */
public class Shovel extends Item {

    public Shovel() {
        super(200);
    }

    @Override
    public void usar() {
        //Codigo para excluir uma planta
    }
    
    @Override
    public String toString() {
        return "Shovel " + super.toString();
    }
}
