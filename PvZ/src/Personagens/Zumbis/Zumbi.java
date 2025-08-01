/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens.Zumbis;

import Auxiliares.Posicao;
import Abstratos.Atacante;
import Abstratos.Colidivel;
import Main.GameLogic;
import Abstratos.Personagem;
import Personagens.Plantas.Planta;
import Projetil.Projetil;
import java.awt.Rectangle;

/**
 *
 * @author Felipe
 */
public class Zumbi extends Personagem implements Colidivel, Atacante{
    private Planta atacando;
    private float velocidadeMovimento;
    private boolean deveAtacar;
    
   public Zumbi(Zumbi copia) {
       super(copia);
       this.atacando = copia.atacando;
       this.velocidadeMovimento = copia.velocidadeMovimento;
       this.deveAtacar = copia.deveAtacar;
       
   }
    
    public Zumbi(TipoZumbi tipo, Posicao posicao) {
        super(tipo.getSprite(),posicao, tipo.getResistenciaDano(), tipo.getVelocidadeAtaque());
        velocidadeMovimento = tipo.getVelocidadeMovimento();
        deveAtacar = false;
    }
    
    @Override
    public void update(long delta) {
        super.update(delta);
        
        if(deveAtacar == false) {
            posicao.setRealX(posicao.getRealX() - velocidadeMovimento*100*delta);
            
            posicao.realToTile();

            if(posicao.getRealX() < 0) {
                GameLogic.getInstance().perder();
            }
            
            GameLogic.getInstance().checarTileOcupadoPorPlanta(this);
        }
        else {
            atacar();
        }
    }

     @Override
    public void atacar() {
        if (deveAtacar == false) return;
        if (System.currentTimeMillis() - tempoUltimoAtaque < velocidadeAtaque) {
            return;
        }
        tempoUltimoAtaque = System.currentTimeMillis();
        atacando.notificarLevouDano(this, 50);
    }
    
    public void notificaAcertadoPorProjetil(int dano) {
        this.vidaAtual -= dano;
    }

    /**
     * @return the velocidadeMovimento
     */
    public float getVelocidadeMovimento() {
        return velocidadeMovimento;
    }

    /**
     * @param velocidadeMovimento the velocidadeMovimento to set
     */
    public void setVelocidadeMovimento(float velocidadeMovimento) {
        this.velocidadeMovimento = velocidadeMovimento;
    }

    @Override
    public boolean estaColidindoCom(Colidivel outra) {
        Rectangle me = new Rectangle((int) posicao.getRealX(), (int) posicao.getRealX(),getSprite().getLargura(),getSprite().getAltura());
        Rectangle him = new Rectangle((int) outra.getPosicao().getRealX(), (int) outra.getPosicao().getRealX() , outra.getSprite().getLargura(), outra.getSprite().getAltura());

        return me.intersects(him);

    }

    @Override
    public void colidiuCom(Colidivel outra) {
        if (outra instanceof Projetil) {
            vidaAtual -= ((Projetil)outra).getDano();
         //   System.out.println("Vida" + vidaAtual);
        }
    } 
    
    public void notificaAtacarPlanta(Planta p) {
        atacando = p;
        deveAtacar = true;
    }

    //@Override
    public void notificarAtacadoMorreu() {
        deveAtacar = false;
        atacando = null;
    }

    @Override
    public boolean deveAtacar() {
        return deveAtacar;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Zumbi) {
            if (this.getPosicao().equals(((Zumbi)o).getPosicao()))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Zumbi";
    }
}