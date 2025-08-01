/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Plantas;

import Abstratos.Atacante;
import Auxiliares.Posicao;
import Auxiliares.Sprite;
import Auxiliares.SpriteStore;
import Main.GameLogic;
import Projetil.Pea;

/**
 *
 * @author Felipe
 */
public class PeaShooter extends Planta implements Atacante {

    private boolean deveAtacar;
    
    public  PeaShooter (Posicao posicao) {
        super(SpriteStore.get().getSprite("Sprites/peashooter.jpg"), posicao, 100, 1500);
        deveAtacar = false;
    }
    
    public  PeaShooter () {
        super(SpriteStore.get().getSprite("Sprites/peashooter.jpg"), new Posicao(true, 0, 0), 100, 1500);
        deveAtacar = false;
    }
    
    @Override
    public void notificarZumbiNasceu() {
        if (!deveAtacar) {
            deveAtacar = true;
        }
    }
    
    @Override
    public void update(long delta) {
        super.update(delta);
        if (deveAtacar) {
            atacar();
        }
    }

    @Override
    public void notificarMorreuZumbi() {
        if (GameLogic.getInstance().getQuantidadeZumbisFileira(posicao.getTileY()) == 0)
            deveAtacar = false;
    }

    @Override
    public void atacar() {
        if (System.currentTimeMillis() - tempoUltimoAtaque < velocidadeAtaque) {
            return;
        }
        tempoUltimoAtaque = System.currentTimeMillis();
        GameLogic.getInstance().addEntidade(new Pea(new Posicao(false, posicao.getRealX()+100, posicao.getRealY())));
    }

    @Override
    public boolean deveAtacar() {
        return deveAtacar;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof PeaShooter) {
            if (this.getPosicao().equals(((PeaShooter)o).getPosicao()))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Peashoter";
    }
}
