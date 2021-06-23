/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progettomap.parser;

import tipi.Comando;
import oggetti.Oggetto;
import tipi.stanze.Porta;


/**
 *
 * @author mtubi
 */
public class ParserOutput {
    private Comando comando;
    private Oggetto oggetto;
    private Oggetto oggettoInv;
    private Porta porta;

    public ParserOutput(Comando comando, Oggetto oggetto) {
        this.comando = comando;
        this.oggetto = oggetto;
    }

    public ParserOutput(Comando comando, Oggetto oggetto, Oggetto oggettoSec) {
        this.comando = comando;
        this.oggetto = oggetto;
        this.oggettoInv = oggettoSec;
    }

    public ParserOutput(Comando comando, Porta porta) {
        this.comando = comando;
        this.porta = porta;
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

    public Oggetto getOggettoSec() {
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
    
    
    
    }

