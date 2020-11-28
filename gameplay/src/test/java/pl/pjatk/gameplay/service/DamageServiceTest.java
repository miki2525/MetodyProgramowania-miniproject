package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageServiceTest {

    private DamageService damageService = new DamageService();

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
        assertThat(player.getHealth()).isNotEqualTo(0);
        assertThat(player.getHealth()).isEqualTo(10000000);
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
}
