package charactor;

import subject.Subject;

public class Player extends Charactor {

    public Player(Subject sub) {
        // 플레이어 직업 설정 및 플레이어 설정
        super(sub);
        setPlayer();
    }

    // 메서드
    // 플레이어 회복
    public void heal() {
        currentHp += 10;
    }
}
