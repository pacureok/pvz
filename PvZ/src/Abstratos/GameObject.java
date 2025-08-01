/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstratos;

import Auxiliares.Posicao;
import Auxiliares.Sprite;
import java.awt.Graphics2D;

/**
 *
 * @author Felipe
 */
public interface GameObject {
    public void update(long delta);
    
    public void desenhar(Graphics2D g);
    
    public Sprite getSprite();
    
    public Posicao getPosicao();
}
