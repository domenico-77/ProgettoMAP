/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import comandi.Comando;
import oggetti.Oggetto;
import stanze.Porta;


/**
 *
 * @author mtubi
 */
public class ParserOutput {
    private Comando comando;
    private Oggetto oggetto;
    private Oggetto oggettoInv;
    private Porta porta;
    private boolean npc;

    public ParserOutput(Comando comando, Oggetto oggetto) {
        this.comando = comando;
        this.oggetto = oggetto;
        this.oggettoInv = null;
        this.porta = null;
        this.npc = false;
    }

    public ParserOutput(Comando comando, Oggetto oggetto, Oggetto oggettoSec) {
        this.comando = comando;
        this.oggetto = oggetto;
        this.oggettoInv = oggettoSec;
        this.porta = null;
        this.npc = false;
    }

    public ParserOutput(Comando comando, Porta porta) {
        this.comando = comando;
        this.porta = porta;
        this.oggetto = null;
        this.oggettoInv = null;
        this.npc = false;
    }

    public ParserOutput(Comando comando, boolean npc) {
        this.comando = comando;
        this.oggetto = null;
        this.oggettoInv = null;
        this.porta = null;
        this.npc = npc;
    }

    
    
    
    
    public ParserOutput(Comando comando) {
        this.comando = comando;
    }

    public Comando getComando() {
        return comando;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public Oggetto getOggettoInv() {
        return oggettoInv;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public void setOggettoSec(Oggetto oggettoSec) {
        this.oggettoInv = oggettoSec;
    }

    public Porta getPorta() {
        return porta;
    }

    public boolean isNpc() {
        return npc;
    }
    
    
    
    }

