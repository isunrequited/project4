package character;

import subject.Subject;

public class Player extends Character {

    public Player(Subject sub, String name) {
        // 플레이어 직업 설정 및 플레이어 설정
        super(sub);
        setPlayer(name);
    }

    // 메서드
    // 플레이어 회복
    public void endStage() {
        currentHp += 10;
        manaCount = 0;
    }
}
