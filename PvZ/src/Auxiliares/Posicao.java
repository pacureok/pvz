/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

/**
 *
 * @author Felipe
 */
public class Posicao implements Comparable<Posicao>{
    public static final float ALTURA = 600;
    public static final int ALTURA_EM_TILES = 6;
    public static final int LARGURA_EM_TILES = 8;
    public static final float LARGURA = 800;
    private int tileX;
    private int tileY;
    
    private float realX;
    private float realY;

    public Posicao(boolean isTile, float x, float y) {
        if (isTile) {
            tileX = Math.max(0, Math.min((int)x, LARGURA_EM_TILES));
            tileY = Math.max(0, Math.min((int)y, ALTURA_EM_TILES));
            tileToReal();
        }
        else {
            realX = Math.max(0, Math.min(x, LARGURA));
            realY = Math.max(0, Math.min(y, ALTURA));
        }
    }

    public void tileToReal() {
        realX = tileX*LARGURA/LARGURA_EM_TILES;
        realY = tileY*ALTURA/ALTURA_EM_TILES;
    }
    
    public void realToTile() {
        tileX = (int) (realX*LARGURA_EM_TILES/LARGURA);
        tileY = (int) (realY*ALTURA_EM_TILES/ALTURA);
    }

    public int getTileX() {
        return tileX;
    }


    public void setTileX(int tileX) {
        this.tileX = tileX;
    }


    public int getTileY() {
        return tileY;
    }


    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

 
    public float getRealX() {
        return realX;
    }

    /**
     * @param realX the realX to set
     */
    public void setRealX(float realX) {
        this.realX = realX;
    }

    /**
     * @return the realY
     */
    public float getRealY() {
        return realY;
    }

    /**
     * @param realY the realY to set
     */
    public void setRealY(float realY) {
        this.realY = realY;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Posicao) {
            if (this.realX == ((Posicao)o).realX && this.realY == ((Posicao)o).realY)
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Tile(x,y) = (" + tileX + "," + tileY + ") Real(x,y) = (" + realX + "," + realY + ")";
    }

    @Override
    public int compareTo(Posicao o) {
        if (o.getRealY() > this.realX)
            return -1;
        if (o.getRealY() == this.realX)
            return 0;
        return  -1;
    }
}