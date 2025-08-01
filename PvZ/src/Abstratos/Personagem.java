/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstratos;

import Auxiliares.Posicao;
import Auxiliares.Sprite;
import Auxiliares.SpriteStore;
import Abstratos.GameObject;
import Main.GameLogic;
import java.awt.Graphics2D;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Felipe
 */
public abstract class Personagem implements GameObject{
    protected Posicao posicao;
    protected Sprite sprite;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int velocidadeAtaque;
    protected long tempoUltimoAtaque;
    
    public Personagem(Sprite sprite, Posicao posicao,int vidaMaxima, int velocidadeAtaque) {
        this.sprite = sprite;
        this.posicao = posicao;
        this.vidaMaxima = Math.max(1,vidaMaxima);
        this.vidaAtual = this.vidaMaxima;
        this.velocidadeAtaque = Math.max(1,velocidadeAtaque);
        this.tempoUltimoAtaque = System.currentTimeMillis();
    }
    
    public Personagem() {
        this(SpriteStore.get().getSprite("Sprites/peashooter.jpg"), new Posicao(true, 0, 0), 100, 5);
    }
    
    public Personagem(Personagem copia) {
        this.posicao = copia.posicao;
        this.sprite = copia.sprite;
        this.vidaMaxima = copia.vidaMaxima;
        this.vidaAtual = copia.vidaAtual;
        this.velocidadeAtaque = copia.velocidadeAtaque;
        this.tempoUltimoAtaque = copia.tempoUltimoAtaque;
    }
    
    @Override
    public void desenhar(Graphics2D graficos) {
        getSprite().desenhar(graficos, (int)getPosicao().getRealX(), (int)getPosicao().getRealY());
    };
    
    public boolean checarMorreu() {
        return getVidaAtual() < 0;
    }

    @Override
    public void update(long delta) {
        if (checarMorreu()) {
           GameLogic.getInstance().removeEntidade(this);
            return; 
        }
    }
    
    @Override
    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public int getVelocidadeAtaque() {
        return velocidadeAtaque;
    }

    public void setVelocidadeAtaque(int velocidadeAtaque) {
        this.velocidadeAtaque = velocidadeAtaque;
    }

    public long getTempoUltimoAtaque() {
        return tempoUltimoAtaque;
    }

    public void setTempoUltimoAtaque(long tempoUltimoAtaque) {
        this.tempoUltimoAtaque = tempoUltimoAtaque;
    }
    
    @Override
    public String toString() {
        return "Personagem";
    }
}
