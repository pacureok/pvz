/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Plantas;
/**
 *
 * @author Felipe
 */
public enum TipoPlanta {
    
    PEASHOOTER("Pea Shooter"),
    WALLNUT("Wall Nut"),
    REPEATER("Repeater");
    
    private final String nome;

    
    TipoPlanta(String tipoNome) {
        nome = tipoNome;
    }

    public String getNome() {
        return nome;
    }
}
