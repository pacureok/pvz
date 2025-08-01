/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author Felipe
 */
public abstract class Item {
    private int cooldown;
    private int tempoUltimoCooldown;
    private boolean podeUsar;
    
    public Item(int cooldown) {
        this.cooldown = Math.min(300, Math.max(cooldown, 0));
        this.tempoUltimoCooldown = 0;
        podeUsar = true;
    }

    public abstract void usar();
    
    public boolean checarPodeUsar() {
        if(podeUsar) return true;
        int tempoAtual = 0;
        if (tempoAtual - tempoUltimoCooldown > cooldown)
            podeUsar = true;
        return podeUsar;
    }
    
    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getTempoUltimoCooldown() {
        return tempoUltimoCooldown;
    }

    public void setTempoUltimoCooldown(int tempoUltimoCooldown) {
        this.tempoUltimoCooldown = tempoUltimoCooldown;
    }

    public boolean isPodeUsar() {
        return podeUsar;
    }

    public void setPodeUsar(boolean podeUsar) {
        this.podeUsar = podeUsar;
    }
    
    @Override
    public String toString() {
        return "Item CD" + cooldown + " Pronto para usar?" + podeUsar;
    }
}
