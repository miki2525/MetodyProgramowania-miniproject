package pl.pjatk.gameplay.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private int health;
    private int attack;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "player")
    private List<Message> messageList = new ArrayList<>();

    public Player() {
    }

    public Player(String nickname, int health, int attack, List<Message> messageList) {
        this.nickname = nickname;
        this.health = health;
        this.attack = attack;
        this.messageList = messageList;
    }

    public Player(Long id, String nickname, int health, int attack) {
        this.id = id;
        this.nickname = nickname;
        this.health = health;
        this.attack = attack;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                '}';
    }
}

