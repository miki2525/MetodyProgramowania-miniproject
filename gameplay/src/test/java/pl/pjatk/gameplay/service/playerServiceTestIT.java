package pl.pjatk.gameplay.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class playerServiceTestIT {

    @BeforeEach ///lub @AfterEach
    void cleanUp(){
        playerService.deleteAll();
    }

    @Autowired
    private PlayerService playerService;


    @Test
    void shouldNotFindAnyone(){
        List<Player> all = playerService.findAll();
            assertThat(all).isEmpty();

    }

    @Test
    void shouldFindAllPlayers(){
        playerService.save(new Player("nickname", 110, 10, List.of()));
        List<Player> all = playerService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldSavePlayer(){
        Player player = playerService.save(new Player("nickname", 100, 10, List.of()));
                assertThat(player.getId()).isPositive();
    }

    @Test
    void shouldFindById(){
        //
        Player player = playerService.save(new Player("nickname", 100, 20, List.of()));
        //Player player1 = player;
        //
        playerService.findById(player.getId());
        //
        assertThat(playerService.findById(player.getId())).isPresent();
    }

    @Test
    void shouldAttackPlayer(){
        Player attacker = playerService.save(new Player("nickname", 200, 100, List.of()));
        Player defender = playerService.save(new Player("nickname", 150, 40, List.of()));

        playerService.attackPlayer(attacker.getId(), defender.getId());

        assertThat(playerService.findById(defender.getId()).get().getHealth()).isEqualTo(50);
    }

    @Test
    void shouldThrowExceptionOnFindById(){
            assertThatExceptionOfType(RuntimeException.class).
                isThrownBy(() -> playerService.findById(10L));
    }




}
