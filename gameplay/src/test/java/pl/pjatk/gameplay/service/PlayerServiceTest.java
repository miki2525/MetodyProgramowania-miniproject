package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.List.*;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //mowimy serwisowy ze rozrzezamy klasy o Mockito
class PlayerServiceTest {

    @Mock
    private DamageService damageServiceTest;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerServiceTest;


    @Test
    void shouldfindAll() {
        //given
        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player())); // w Java 9+ jest List.of
        //when
        List<Player> listTest = playerServiceTest.findAll();
        //then
        assertThat(listTest).hasSize(1);
    }
    /*     @Test
           void shouldfindAll(){
            //given
            //when
            List<Player> listTest = playerServiceTest.findAll();
            //then
            assertThat(listTest).isNotEmpty();
    */
    @Test
    void shouldDeleteById() { ////sprawdzamy czy metoda zostala wywolana
        //given
        //when
        playerServiceTest.deleteById(1L);
        //then
        verify(playerRepository, times(1)).deleteById(1L);
    }

     @Test
     void shouldSave(){
         //given
         Player playerTest = new Player("Ktos", 100, 200);
         when(playerRepository.save(playerTest)).thenReturn(new Player(25L,"Ktos", 100, 200));
         //when
         Player save = playerServiceTest.save(playerTest);
         //then
         assertThat(save.getId()).isEqualTo(25L);
        }

        @Test
    void shouldAttackPlayer(){
            //given
            Player attackerTest = new Player(50L, "Ktos", 100, 30);
            Player defenderTest = new Player(25L, "Inny", 100, 50);
            when(damageServiceTest.attackPlayer(attackerTest, defenderTest)).thenReturn(defenderTest);
            when(playerRepository.findById(attackerTest.getId())).thenReturn(Optional.of(attackerTest));
            when(playerRepository.findById(defenderTest.getId())).thenReturn(Optional.of(defenderTest));
            when(playerRepository.save(defenderTest)).thenReturn(defenderTest);
            //when
            Player test = playerServiceTest.attackPlayer(attackerTest.getId(), defenderTest.getId());
            //then
            assertThat(test.getHealth()).isEqualTo(defenderTest.getHealth());

        }


    }





