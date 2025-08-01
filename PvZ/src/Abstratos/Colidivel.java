/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstratos;

import Auxiliares.Posicao;
import Auxiliares.Sprite;

/**
 *
 * @author Felipe
 */
public interface Colidivel {
    public boolean estaColidindoCom(Colidivel outro);
    
    public void colidiuCom(Colidivel outro);
    
    public Sprite getSprite();
    
    public Posicao getPosicao();
}
