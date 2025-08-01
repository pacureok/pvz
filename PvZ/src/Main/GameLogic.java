/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Auxiliares.Posicao;
import Abstratos.GameObject;
import Personagens.Plantas.Planta;
import Personagens.Plantas.PlantaFactory;
import Personagens.Plantas.TipoPlanta;
import Personagens.Zumbis.TipoZumbi;
import Personagens.Zumbis.Zumbi;
import Personagens.Zumbis.ZumbiFactory;
import Projetil.Projetil;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class GameLogic extends Canvas{
    private static GameLogic instance;
    
    public static GameLogic getInstance() {
        if (instance == null)
            return instance = new GameLogic();
        return instance;
    }
    
    private BufferStrategy strategy;
    
    private final boolean gameRunning = true;
    private ArrayList<GameObject> entidades = new ArrayList();
    private ArrayList<GameObject> removeList = new ArrayList();
    private ArrayList<GameObject> addList = new ArrayList();
    
    private int[] quantidadeZumbisFileira = new int[Posicao.ALTURA_EM_TILES];
    
    private double tempoNascimentoUltimoZumbi = 0;
    private double tempoNascimentoZumbi = 4000;
    
    public GameLogic() {
        JFrame container = new JFrame("Plants vs Zombies");

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension((int)Posicao.LARGURA,(int)Posicao.ALTURA));
        panel.setLayout(null);

        setBounds(0,0,(int)Posicao.LARGURA,(int)Posicao.ALTURA);
        panel.add(this);

        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        container.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                        System.exit(0);
                }
        });

        requestFocus();

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        initEntitidades();
        

        tempoNascimentoUltimoZumbi = System.currentTimeMillis();

    }

    private void startGame() {
        entidades.clear();
        initEntitidades();
    }

    private void initEntitidades() {
        for (int i = 0; i < Posicao.ALTURA_EM_TILES;i++) {
            entidades.add(PlantaFactory.criarPlanta(TipoPlanta.REPEATER, new Posicao(true,0,i)));
            entidades.add(PlantaFactory.criarPlanta(TipoPlanta.PEASHOOTER, new Posicao(true,1,i)));
            entidades.add(PlantaFactory.criarPlanta(TipoPlanta.WALLNUT, new Posicao(true,2,i)));
        }
    }
    
    public void gameLoop() {
        long lastLoopTime = System.currentTimeMillis();

        // keep looping round til the game ends
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entidades should
            // move this loop
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            // Get hold of a graphics context for the accelerated 
            // surface and blank it out
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,800,600);

            update(delta);
            draw(g);

            entidades.removeAll(removeList);
            removeList.clear();

            entidades.addAll(addList);
            addList.clear();

            g.dispose();
            strategy.show();

            // finally pause for a bit. Note: this should run us at about
            // 100 fps but on windows this might vary each loop due to
            // a bad implementation of timer
            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }
    
    
    public void update(long delta) {
        for (GameObject entidade : entidades) {
            entidade.update(delta);
        }
        criarZumbis();
    }
    
    public void criarZumbis() {
        if (System.currentTimeMillis() - tempoNascimentoUltimoZumbi < tempoNascimentoZumbi) {
            return;
        }
        Random gerador = new Random();
        int numeroAleatorio = gerador.nextInt(Posicao.ALTURA_EM_TILES);
        addEntidade(ZumbiFactory.criarZumbi(TipoZumbi.Zumbi,new Posicao(true,8,numeroAleatorio)));
        quantidadeZumbisFileira[numeroAleatorio]++;
        tempoNascimentoZumbi = Math.max(tempoNascimentoZumbi-100, 2000);
        tempoNascimentoUltimoZumbi = System.currentTimeMillis();
    }
    
    public void draw(Graphics2D graphics) {
        entidades.stream().forEach((entidade) -> {
            entidade.desenhar(graphics);
        });
    }
    
    public void perder() {
        System.exit(0);
    }
    
    public void checarColisacaoProjetil(Projetil projetil) {
        for (GameObject gameObject : entidades) {
            if (gameObject instanceof Zumbi) {
                if(projetil.getPosicao().getRealY() == gameObject.getPosicao().getRealY()) {
                    if (projetil.estaColidindoCom((Zumbi)gameObject)) {
                        projetil.colidiuCom((Zumbi)gameObject);
                        ((Zumbi)gameObject).colidiuCom(projetil);
                    }
                }
            }
        }
    }
    
    public void checarTileOcupadoPorPlanta(Zumbi zumbi) {
        for (GameObject gameObject : entidades) {
            if (gameObject instanceof Planta) {
                if (zumbi.getPosicao().getTileX() == gameObject.getPosicao().getTileX() && 
                        zumbi.getPosicao().getTileY() == gameObject.getPosicao().getTileY()) {
                    zumbi.notificaAtacarPlanta((Planta)gameObject);
                }
            }
        }
    }
   
    public void addEntidade(GameObject entidade) {
        addList.add(entidade);
        if (entidade instanceof Zumbi) {
            for (GameObject p : entidades) {
                if (p instanceof Planta) {    
                    if (p.getPosicao().getTileY() == entidade.getPosicao().getTileY()) {
                        ((Planta)p).notificarZumbiNasceu();
                    }
                }
            }
        }
    }
    
    public void removeEntidade(GameObject entidade) {
        removeList.add(entidade);
        if (entidade instanceof Zumbi) {
           quantidadeZumbisFileira[entidade.getPosicao().getTileY()]--;
            for (GameObject p : entidades) {
               
                if (p instanceof Planta) {
                    if (p.getPosicao().getTileY() == entidade.getPosicao().getTileY()) {
                        ((Planta)p).notificarMorreuZumbi();
                        
                    }
                }
            }
        }
    }
    
    public int getQuantidadeZumbisFileira(int fileira) {
        return quantidadeZumbisFileira[fileira];
    }
}
