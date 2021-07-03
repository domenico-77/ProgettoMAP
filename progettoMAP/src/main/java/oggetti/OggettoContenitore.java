/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.io.Serializable;
import oggetti.Oggetto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.Inventario;
import tipi.Utilita;
import tipi.stanze.TipoPorta;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class OggettoContenitore extends Oggetto implements Serializable {

    private final static boolean PRENDIBILE = false;
    private final static int DURABILITA = -1;
    private boolean aperto = false;
    private List<Oggetto> listaOggetti = new ArrayList();
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.oggettoContenitore;

    public OggettoContenitore(String nome, Set<String> alias, List<Oggetto> listaOggetti) {
        super(nome, alias, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
        this.listaOggetti = listaOggetti;
    }

    public List<Oggetto> getListaOggetti() {
        return listaOggetti;
    }

    public void setListaOggetti(List<Oggetto> listaOggetti) {
        this.listaOggetti = listaOggetti;
    }

    public void aggiungiOggetto(Oggetto o) {
        this.listaOggetti.add(o);
    }

    public void rimuoviOggetto(Oggetto o) {
        if (contieneOggetto(o)) {
            this.listaOggetti.remove(o);
        }
    }

    public boolean contenitoreVuoto() {
        boolean vuoto = false;
        if (this.aperto) {
            vuoto = this.listaOggetti.isEmpty();
        }
        return vuoto;
    }

    public boolean contieneOggetto(Oggetto o) {
        return this.listaOggetti.contains(o);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        Inventario inventario = giocatore.getInventario();
        if (this.aperto) {
            if (!this.contenitoreVuoto()) {
                this.listaOggetti.forEach(o -> {
                    if(o.prendibile){
                        System.out.println("Rin: 'Hai raccolto "+o.nome+"'");
                        inventario.aggiungiOggetto(o);
                    }
                    else{
                        o.usa(giocatore, stanza);
                    }
                });
                giocatore.setInventario(inventario);
            } else {
                System.out.println("Rin: 'Non c'è nessun oggetto all'interno'");
            }
        } else {
            System.out.println("Rin: 'Lo scrigno è chiuso, forse dovremmo aprirlo con una chiave'");
        }
    }

    @Override
    public void descrizioneOggetto() {
            if (this.aperto) {
                if(this.listaOggetti.isEmpty()){
                    System.out.println("Rin: 'E' uno scrigno vuoto, abbiamo preso tutto'");
                }
                else{
                System.out.println("Rin: 'Lo scrigno è aperto, prendiamo quello che c'è dentro'");
                }
            } else {
                System.out.println("Rin: 'E' uno scrigno, potrebbe contenere oggetti interessanti, se abbiamo una chiave potremmo aprirlo'");
            }
    }

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static int getDURABILITA() {
        return DURABILITA;
    }

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public boolean isAperto() {
        return aperto;
    }

}
