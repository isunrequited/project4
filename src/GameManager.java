import charactor.*;
import subject.*;

public class GameManager {

    private boolean isGameOver; // 게임 오버 여부
    private Player player;
    private Enemy enemy;
    private int stage;
    private boolean gameOverFlag;

    // 클래스 선택시 플레이어에게 직업 부여
    public void setPlayerSubject(Subject playerSubject) {
        player = new Player(playerSubject);
    }

    // 스테이지 시작시 기본 스테이지 설정
    public void setStage() {
        // 적 생성
        makeEnemy();
    }

    // 적 생성
    private void makeEnemy() {
        enemy = new Enemy(new Mage());
    }

    // 전투시 플레이어와 에너미의 선택을 비교 그에 맞는 결과 실행 결과 반납
    // 적의 체력이 0이 되면 true 반납
    public boolean battle(String playerplay) {
        // 적의 행동 결정(랜덤)
        decideEnemyPlay();

        // 각자의 행위에 따른 행동 수행
        // 플레이어 : 공격, 적 : 공격
        player.attack(enemy);
        enemy.attack(player);

        // 플레이어 : 방어, 적 : 공격
        // -----

        isGameOver(); // 플레이어 사망(게임 오버) 확인
        if(enemy.getDieFlag()) return true; // 적이 사망하면 true 반환

        return false; // 적을 죽이지 못했다면 false반환
    }

    // 적의 행동 결정(랜덤)
    private String decideEnemyPlay() {
        return null;
    }

    public void endStage() {
        // 스테이지 1상승
        stage++;
        // 플레이어 회복
    }

    // 게임 오버 판단 여부
    private void isGameOver() {
        if (player.getDieFlag()) {
             gameOverFlag = true;
        }
    }

    // 플레이어 정보 반환
    public String playerInfo() {
        return player.info();
    }

    // 적 정보 반환
    public String enemyInfo() {
        return enemy.info();
    }

}