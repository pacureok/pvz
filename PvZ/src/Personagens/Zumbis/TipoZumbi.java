/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Zumbis;

import Auxiliares.Sprite;
import Auxiliares.SpriteStore;

/**
 *
 * @author Felipe
 */
public enum TipoZumbi {
    
    Zumbi("Padrao",1000,300, SpriteStore.get().getSprite("Sprites/zumbi.jpg"), 0.001f);
    
    private final String nome;
    private final int velocidadeAtaque;
    private final int resistenciaDano;
    private final float velocidadeMovimento;
    private final Sprite sprite; 
    
    TipoZumbi(String tipoNome, int  velocidade, int resistencia, Sprite sprite, float movimento) {
        nome = tipoNome;
        velocidadeAtaque = Math.max(velocidade, 1);
        resistenciaDano = Math.max(resistencia, 100);
        velocidadeMovimento = movimento;
        this.sprite = sprite;
    }
    
    public String getNome() {
        return nome;
    }

    public int getVelocidadeAtaque() {
        return velocidadeAtaque;
    }

    public int getResistenciaDano() {
        return resistenciaDano;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getVelocidadeMovimento() {
        return velocidadeMovimento;
    }
}