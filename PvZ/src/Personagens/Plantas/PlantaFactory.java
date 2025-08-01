/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Plantas;

import Auxiliares.Posicao;

/**
 *
 * @author Felipe
 */
public class PlantaFactory {
    public static Planta criarPlanta(TipoPlanta tipo, Posicao posicao) {
        if (tipo == TipoPlanta.PEASHOOTER)
            return new PeaShooter(posicao);
        if (tipo == TipoPlanta.WALLNUT)
            return new WallNut(posicao);
        if (tipo == TipoPlanta.REPEATER)
            return  new Repeater(posicao);
        return null;
    }
}
