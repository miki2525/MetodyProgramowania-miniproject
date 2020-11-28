package pl.pjatk.gameplay.service;

import org.hibernate.internal.util.collections.IdentityMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.pjatk.gameplay.controller.PlayerController;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private DamageService damageService ;

    public PlayerService(PlayerRepository playerRepository, DamageService damageService){
        this.playerRepository = playerRepository;
        this.damageService = damageService;

    }

        public List<Player> findAll() {
            return playerRepository.findAll();

            /*List<Player> playerList = new ArrayList<>();

            Player player = new Player();
            Player player2 = new Player();
            playerList.add(player);
            player.setAttack(100);
            playerList.add(player);
            playerList.add(player2);
            return playerList;*/
        }

        public Optional<Player> findById(Long playerID){
            return playerRepository.findById(playerID);
        }

        public Player save(Player player){
            return playerRepository.save(player);
        }

        public void delete(Player player){
        playerRepository.delete(player);
        }

        public void deleteById(Long id){
        playerRepository.deleteById(id);
        }

        public Player update(Player player) {
            Optional<Player> byId = playerRepository.findById(player.getId());
            if (!byId.isPresent()) {
                throw new RuntimeException();
            } else {
                return playerRepository.save(player);
            }

    }
    public Player attackPlayer(Long attackerID, Long defenderID){
        Player attacker = findById(attackerID).get();
        Player defender = findById(defenderID).get();

        defender = damageService.attackPlayer(attacker, defender);

        return update(defender);
    }

    //// utworzyc metode ktora przejmie atak z controllera  i przekaze nam  do DamageService.
    ///utowrzyc damageservice ktory przyjmie obiekty i zada obrazenia odpowiedniemu graczowi , nie dodawac zaleznosci  innych serwisow
    ///zapisac zmiany do bazy
    ////zwrocic gracza broniacego
    }
