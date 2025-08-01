/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Plantas;

import Abstratos.Atacante;
import Auxiliares.Posicao;
import Auxiliares.SpriteStore;
import Main.GameLogic;
import Projetil.Pea;

/**
 *
 * @author Felipe
 */
public class Repeater extends Planta implements Atacante{

    private boolean deveAtacar;
    private boolean eEntreAtaque;
    private float velocidadeEntreAtaque;
    
    public  Repeater (Posicao posicao) {
        super(SpriteStore.get().getSprite("Sprites/repeater.png"), posicao, 100, 1500);
        deveAtacar = false;
        eEntreAtaque = false;
        velocidadeEntreAtaque = 300;
    }
    
    public  Repeater () {
        super(SpriteStore.get().getSprite("Sprites/repeater.png"), new Posicao(true, 0, 0), 100, 1500);
        deveAtacar = false;
        eEntreAtaque = false;
        velocidadeEntreAtaque = 300;
    }
    
    @Override
    public void update(long delta) {
        super.update(delta);
        if (deveAtacar) {
            atacar();
        }
    }
    
    @Override
    public void notificarZumbiNasceu() {
        if (!deveAtacar)
            deveAtacar = true;
    }

    @Override
    public void notificarMorreuZumbi() {
        if (GameLogic.getInstance().getQuantidadeZumbisFileira(posicao.getTileY()) == 0)
            deveAtacar = false;
    }

    @Override
    public void atacar() {
        float tempoEsperar = eEntreAtaque ? velocidadeEntreAtaque : velocidadeAtaque;
        if (System.currentTimeMillis() - tempoUltimoAtaque < tempoEsperar) {
            return;
        }
        tempoUltimoAtaque = System.currentTimeMillis();
        GameLogic.getInstance().addEntidade(new Pea(new Posicao(false, posicao.getRealX()+100, posicao.getRealY())));
        eEntreAtaque = !eEntreAtaque;

    }

    @Override
    public boolean deveAtacar() {
        return deveAtacar;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Repeater) {
            if (this.getPosicao().equals(((Repeater)o).getPosicao()))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Repeater";
    }
}
