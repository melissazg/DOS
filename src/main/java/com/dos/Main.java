package com.dos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.reset();

        final List<String> playerId = new ArrayList<String>();

        int nbJoueursEnJeu = 3;

        for(int i = 1; i <= nbJoueursEnJeu; i++) {
            playerId.add("Joueur" + i);
        }

        Game game = new Game(playerId);
        game.start(game);

        final Queue<String> players = new LinkedList<>();
        

        players.addAll(game.getPlayers());

        int j = 1;

        while (players.size() > nbJoueursEnJeu - 1) {
            System.out.println("Round " + j++);
            System.out.println("C'est au  " + game.getCurrentPlayer() + " de jouer \n");
            System.out.println("Main du J1 : " + game.getPlayerHand(playerId.get(0)).toString());
            System.out.println("Main du J2 : " + game.getPlayerHand(playerId.get(1)).toString());
            System.out.println("Main du J3 : " + game.getPlayerHand(playerId.get(2)).toString() + "\n");
            System.out.println("StockPile : " + game.getStockPile() + "\n");
            

            String firstPlayerInRound = players.poll();
            players.offer(firstPlayerInRound);

            game.submitPlayerCard(firstPlayerInRound);

            if (game.hasEmptyHand(firstPlayerInRound)) {
                System.out.println("Fin de la partie");
                System.out.println("Main du J1 : " + game.getPlayerHand(playerId.get(0)).toString());
                System.out.println("Main du J2 : " + game.getPlayerHand(playerId.get(1)).toString());
                System.out.println("Main du J3 : " + game.getPlayerHand(playerId.get(2)).toString());
                System.out.println("StockPile : " + game.getStockPile());
                System.out.println("\n" + firstPlayerInRound + " a gagné.\n");
                firstPlayerInRound = players.poll();
            }
        }        
    }
       /* initialise les players (chacun un id)
       on distribue les cartes 
       afficher les cartes des players 
       on tire deux cartes de la pioche pour stockpile
       start game 
       
       [joueur doit poser validcolor ou validValue
       préférence plusieurs cartes identiques de validcolor
       sinon somme de valid
       si pas de carte valide, piocher ou jouer carte spé
       passer au joueur suivant] while aucun joueur n'a de main vide ??
        * 
        */
}
