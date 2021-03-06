/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import comandi.Comando;
import oggetti.Oggetto;
import comandi.TipoComando;
import utilita.Utilita;
import stanze.Porta;
import stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Parser {

    private final Set<String> paroleProibite;

    public Parser(Set<String> paroleProibite) {
        this.paroleProibite = paroleProibite;
    }

    public ParserOutput parse(String comandoUtente, List<Comando> azioni, List<Oggetto> oggetti, List<Oggetto> inventario, Stanza stanza) {
        ParserOutput comando = null;
        comandoUtente = comandoUtente.toLowerCase();
        comandoUtente = comandoUtente.replaceAll("[.!£$%&/]+", "");//Pulizia del comando di caratteri di punteggiatura
        comandoUtente = comandoUtente.replaceAll("'", " ");//Elimino l'apostrofo e lo sotituisco con uno spazio per individuare l'articolo
        List<String> paroleComando = pulisciStringa(comandoUtente);//Divido il comando in base agli spazi in modo da esaminare parola per parola
        if (!paroleComando.isEmpty()) {//controllo che dopo le varie pulizie la stringa non sia vuota
            int intAzione = Utilita.cercaAzioni(paroleComando.get(0), azioni);
            if (intAzione > -1) {//Se l'utente non ha scritto un azione l'intero comando non è valido
                if (paroleComando.size() >= 2) {
                    int intOggetto = Utilita.cercaOggetto(paroleComando.get(1), oggetti);
                    int intOggettoInv = -1;//Inizializzo una variabile per l'ggetto dell'inventario
                    if (intOggetto == -1) {//Se non è stato trovato l'oggetto nella seconda posizione, provo a cercarlo nella terza posizione e cerco un oggetto dell'inventario nella seconda
                        intOggettoInv = Utilita.cercaOggetto(paroleComando.get(1), inventario);
                    } else if (intOggetto > -1 && paroleComando.size() >= 3) {
                        intOggettoInv = Utilita.cercaOggetto(paroleComando.get(2), inventario);
                    }
                    if (intAzione > -1 && intOggetto > -1 && intOggettoInv > -1) {
                        comando = (new ParserOutput(azioni.get(intAzione), oggetti.get(intOggetto), inventario.get(intOggettoInv)));
                    } else if (intAzione > -1 && intOggetto > -1 && intOggettoInv == -1) {
                        comando = (new ParserOutput(azioni.get(intAzione), oggetti.get(intOggetto)));
                    } else if (intAzione > -1 && intOggetto == -1 && intOggettoInv > -1) {
                        comando = (new ParserOutput(azioni.get(intAzione), null, inventario.get(intOggettoInv)));
                    } else if (intAzione > -1 && intOggetto == -1 && intOggettoInv == -1) {
                        if (Utilita.cercaParola(paroleComando.get(1), Porta.getAlias()) && paroleComando.size() >= 3) {
                            if (Utilita.cercaParola(paroleComando.get(2),  "nord", "sopra", "su", "sù")) {
                                if (stanza.getPortaNord() != null) {
                                    comando = new ParserOutput(azioni.get(intAzione), stanza.getPortaNord());
                                }
                                else{
                                    comando = new ParserOutput(azioni.get(intAzione));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(2), "sud", "giu", "sotto", "giù")) {
                                if (stanza.getPortaSud() != null) {
                                    comando = new ParserOutput(azioni.get(intAzione), stanza.getPortaSud());
                                }
                                else{
                                    comando = new ParserOutput(azioni.get(intAzione));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(2), "destra", "est")) {
                                if (stanza.getPortaEst() != null) {
                                    comando = new ParserOutput(azioni.get(intAzione), stanza.getPortaEst());
                                }
                                else{
                                    comando = new ParserOutput(azioni.get(intAzione));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(2),  "sinistra", "ovest")) {
                                if (stanza.getPortaOvest() != null) {
                                    comando = new ParserOutput(azioni.get(intAzione), stanza.getPortaOvest());
                                }
                                else{
                                    comando = new ParserOutput(azioni.get(intAzione));
                                }
                            }

                        } else if (paroleComando.size() >= 2) {
                            if (stanza.getNpc() != null) {
                                if (Utilita.cercaParola(paroleComando.get(1), stanza.getNpc().getAliasNome())) {
                                    comando = new ParserOutput(azioni.get(intAzione), true);
                                }
                            } else
                                if (Utilita.cercaParola(paroleComando.get(1), "n", "nord", "sopra", "su", "sù")) {
                                if (azioni.get(intAzione).getTipo() == TipoComando.camminare_verso) {
                                    comando = new ParserOutput(azioni.get(Utilita.cercaAzioni("nord", azioni)));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(1), "sud", "s", "giu", "sotto", "giù")) {
                                if (azioni.get(intAzione).getTipo() == TipoComando.camminare_verso) {
                                    comando = new ParserOutput(azioni.get(Utilita.cercaAzioni("sud", azioni)));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(1), "e", "destrra", "est")) {
                                if (azioni.get(intAzione).getTipo() == TipoComando.camminare_verso) {
                                    comando = new ParserOutput(azioni.get(Utilita.cercaAzioni("est", azioni)));
                                }
                            } else if (Utilita.cercaParola(paroleComando.get(1), "o", "sinistra", "ovest")) {
                                if (azioni.get(intAzione).getTipo() == TipoComando.camminare_verso) {
                                    comando = new ParserOutput(azioni.get(Utilita.cercaAzioni("ovest", azioni)));
                                }
                            } else if(paroleComando.get(1).equals("indietro")){
                                if(azioni.get(intAzione).getTipo() == TipoComando.camminare_verso){
                                comando = new ParserOutput(azioni.get(Utilita.cercaAzioni("indietro", azioni)));
                                }
                            }
                            else if(azioni.get(intAzione).getTipo() == TipoComando.camminare_verso){
                                comando = null;
                            }
                            else {

                                comando = (new ParserOutput(azioni.get(intAzione)));
                            }
                        }
                    }

                } else {
                    comando = (new ParserOutput(azioni.get(intAzione)));
                }

            } else {
                comando = null;
            }
        }
        return comando;
    }

    public List<String> pulisciStringa(String comando) {
        String[] appoggio = comando.split("\\s+");
        List<String> paroleComando = new ArrayList();
        for (String appoggio1 : appoggio) {
            if (!paroleProibite.contains(appoggio1)) {
                paroleComando.add(appoggio1);
            }
        }
        return paroleComando;
    }

}
