package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DamageServiceTest {

    private DamageService damageService = new DamageService();

    @Mock
    DamageService damageServiceMock;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;


    @Test
    void shouldIncreaseAttack(){
        //given
        Player player = new Player ("Gondor", 200, 100);
        //when
        damageService.increaseAttack(player);
        //then
        assertThat(player.getAttack()).isEqualTo(200);
        assertThat(player.getAttack()).isNotEqualTo(201);
    }


    @Test
    void shouldFromHellToHeaven() {
        //given
        Player player = new Player("Majek", 300, 250);
        //when
        damageService.fromHellToHeaven(player);
        //then
        assertThat(player.getHealth()).isNotEqualTo(10000000);
        assertThat(player.getHealth()).isEqualTo(300);
    }


    @Test
    void shouldReduceAttack() {
        //given
        Player player = new Player("Donatello", 45, 60);
        //when
        damageService.reduceAttack(player);
        //then
        assertThat(player.getAttack()).isEqualTo(10);
        assertThat(player.getAttack()).isNotEqualTo(-160);
    }

    @Test
    void turnOnSpyMode() {
        //given
        Player player = new Player("Szpieg", 0, 0);
        //when
        damageService.turnOnSpyMode(player);
        //then
        assertThat(player.getNickname().endsWith("SPY")).isEqualTo(true);
        assertThat(player.getNickname().endsWith("spy")).isNotEqualTo(true);
    }

    @Test
    void shouldAttackPlayer() {
        Player player1 = new Player(1L, "test 1", 1000, 50);
        Player player2 = new Player(2L, "test 2", 1000, 50);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(player2));
        when(playerRepository.save(player2)).thenReturn(player2);
        when(damageServiceMock.attackPlayer(any(), any())).thenCallRealMethod();
        ////thenCallrealmethod -
        Player attack1 = playerService.attackPlayer(1L, 2L);
        assertThat(attack1.getHealth()).isEqualTo(950);
    }


}
