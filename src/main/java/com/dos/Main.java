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
        //ArrayList<Card> stockPile = new ArrayList<Card>();

        int nbJoueursEnJeu = 3;

        for(int i = 1; i <= nbJoueursEnJeu; i++) {
            playerId.add("Joueur" + i);
        }

        Game game = new Game(playerId);
        game.start(game);

        final Queue<String> players = new LinkedList<>();

        players.addAll(game.getPlayers());

        int j = 0;

        while (players.size() > nbJoueursEnJeu - 1) {
            System.out.println("Round " + j++);
            System.out.println("Main du J1 : " + game.getPlayerHand(playerId.get(0)).toString());
            System.out.println("Main du J2 : " + game.getPlayerHand(playerId.get(1)).toString());
            System.out.println("Main du J3 : " + game.getPlayerHand(playerId.get(2)).toString() + "\n");
            //System.out.println("StockPile : " + stockPile + "\n");

            //take the first player form the queue
            String firstPlayerInRound = players.poll();
            //and put it immediately at the end
            players.offer(firstPlayerInRound);

            game.submitPlayerCard(firstPlayerInRound);

            if (game.hasEmptyHand(firstPlayerInRound)) {
                System.out.println("Round " + j);
                System.out.println("Main du J1 : " + game.getPlayerHand(playerId.get(0)).toString());
                System.out.println("Main du J2 : " + game.getPlayerHand(playerId.get(1)).toString());
                System.out.println("Main du J3 : " + game.getPlayerHand(playerId.get(2)).toString());
                System.out.println("\n" + firstPlayerInRound + " a gagné.");
                firstPlayerInRound = players.poll();
            }
            //game.submitDraw(firstPlayerInRound);

        }
        //since we've left the loop, we have only 1 player left: the winner
        
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
