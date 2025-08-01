/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Zumbis;

import Auxiliares.Posicao;

/**
 *
 * @author Felipe
 */
public class ZumbiFactory {
    public static int zumbisCriados = 0;
    
    public static Zumbi criarZumbi(TipoZumbi tipo, Posicao posicao) {
        if (tipo == TipoZumbi.Zumbi) {
            zumbisCriados++;
            return new Zumbi(tipo, posicao);
        }
        return null;
    }
}
