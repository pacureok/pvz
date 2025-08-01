/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projetil;

import Abstratos.Colidivel;
import Auxiliares.Posicao;
import Auxiliares.SpriteStore;
import Main.GameLogic;
import java.awt.Rectangle;

/**
 *
 * @author Felipe
 */
public class Pea extends Projetil {
    
    public Pea(Posicao posicao) {
        super(0.13f,30, SpriteStore.get().getSprite("Sprites/pea.jpg"), posicao);
    }
    
    @Override
    public void update(long delta) {
       super.update(delta);
       if (posicao.getRealX() > Posicao.LARGURA) {
            GameLogic.getInstance().removeEntidade(this);
        }
        GameLogic.getInstance().checarColisacaoProjetil(this);
    }

    @Override
    public boolean estaColidindoCom(Colidivel outro) {
        Rectangle me = new Rectangle((int) posicao.getRealX(), (int) posicao.getRealX(),getSprite().getLargura(),getSprite().getAltura());
        Rectangle him = new Rectangle((int) outro.getPosicao().getRealX(), (int) outro.getPosicao().getRealX() , outro.getSprite().getLargura(), outro.getSprite().getAltura());

        return me.intersects(him);    }

    @Override
    public void colidiuCom(Colidivel outro) {
        GameLogic.getInstance().removeEntidade(this);
    }
}
