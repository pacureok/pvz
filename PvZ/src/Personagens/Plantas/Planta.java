/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Plantas;

import Auxiliares.Posicao;
import Auxiliares.Sprite;
import Abstratos.Atacante;
import Main.GameLogic;
import Abstratos.Personagem;
import Personagens.Zumbis.Zumbi;

/**
 *
 * @author Felipe
 */
public abstract class Planta extends Personagem {
    
    public Planta(Sprite sprite, Posicao posicao,int vidaMaxima, int velocidadeAtaque)  {
        super(sprite,posicao,vidaMaxima,velocidadeAtaque);
    }
    
    public Planta() {
        super();
    }
    
    public abstract void notificarZumbiNasceu();
    
    public abstract void notificarMorreuZumbi();
    
    public void notificarLevouDano(Atacante a, int dano) {
        this.vidaAtual -= dano;
        if (checarMorreu()) {
            GameLogic.getInstance().removeEntidade(this);
            if (a instanceof Zumbi) {
                ((Zumbi)a).notificarAtacadoMorreu();
            }
        }
    }
    
    @Override
    public String toString() {
        return "Planta";
    }
}
