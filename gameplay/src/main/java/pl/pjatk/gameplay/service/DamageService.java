package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

import java.util.Random;

@Service
public class DamageService {

    public Player attackPlayer(Player attacker, Player defender){
        defender.setHealth(defender.getHealth() - attacker.getAttack());
        return defender;
    }

    public Player increaseAttack(Player player){
        if ( player.getAttack() < 100){
            player.setAttack(player.getAttack()+200);
        }
        else{
            player.setAttack(player.getAttack()+100);
        }
        return player;
    }

    public Player reduceAttack(Player player){
        if ( player.getAttack() <= 100){
            player.setAttack(player.getAttack()-50);
        }
        else{
            player.setAttack(player.getAttack()-100);
        }
        return player;
    }

    public Player turnOnSpyMode(Player player){
        player.setNickname(player.getNickname()+"SPY");
        return player;
    }

    public Player fromHellToHeaven(Player player){
        if (player.getHealth() == 0) {
            player.setAttack(player.getHealth()+10000000);
        }
        return player;
    }

}
