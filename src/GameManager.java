import character.*;
import character.Character;
import subject.*;

// 실제 게임이 실행되고 돌아가기 위한 데이터들을 관리하는 역할
// 플레이어, 적의 데이터를 관리
// 전투를 실행하여 결과값을 반환 - 실제로 전투가 돌아가는 부분
// 스테이지 설정 등, 게임의 기본 데이터 관리
public class GameManager {

    // 필드 선언부
    private Player player; // 플레이어 정보
    private Enemy enemy; // 적 정보
    private int stage; // 현재 스테이지
    private boolean gameOverFlag; // 게임 오버 여부
    private boolean battleEndFlag; // 전투 종료 여부
    private ClearUser[] ranking; // 유저 랭킹

    // 생성자 선언부
    public GameManager() {
        ranking = new ClearUser[5];
        ranking[0] = new ClearUser(5, "cyh");
        ranking[1] = new ClearUser(2, "cyh1");
        ranking[2] = new ClearUser(4, "cyh3");
        ranking[3] = new ClearUser(6, "cyh4");
        ranking[4] = new ClearUser(5, "cyh5");
        setGame();
    }

    // 직업 정보 표시
    public String subInfo() {
        return new Warrior().info() + "\n" + new Archer().info() + "\n" + new Mage().info();
    }

    // 랭킹 표시
    // 공동 순위 표시, ex 1,1 -> 3
    public void showRanking() {

        int rankNum = 1;
        System.out.printf("%d위 | 닉네임 : %s | 최종 스테이지 : %d\n", rankNum, ranking[0].getName(), ranking[0].getStage());

        for (int i = 1; i < ranking.length; i++) {
            if (ranking[i].getStage() != ranking[i - 1].getStage()) {
                rankNum = i + 1;
            }
            System.out.printf("%d위 | 닉네임 : %s | 최종 스테이지 : %d\n", rankNum, ranking[i].getName(), ranking[i].getStage());
        }
    }

    // 랭킹 정렬
    // ranking 변수에 들어있는 값들을 클리어 스테이지가 높은 순으로 정렬
    void sortRanking() {

        for (int i = 0; i < ranking.length; i++) {
            for (int j = i + 1; j < ranking.length; j++) {
                if (ranking[i].getStage() < ranking[j].getStage()) {
                    ClearUser temp = ranking[i];
                    ranking[i] = ranking[j];
                    ranking[j] = temp;
                }
            }
        }//for i end
    }//sortRanking end

    // 게임 기본 설정
    public void setGame() {
        sortRanking();
        stage = 0;
        gameOverFlag = false;
    }

    // 클래스 선택시 플레이어에게 직업 부여
    public void setPlayerSubject(int i, String name) {
        switch (i) {
            case 1: // 전사
                player = new Player(new Warrior(), name);
                break;
            case 2: // 마법사
                player = new Player(new Mage(), name);
                break;
            case 3: // 궁수
                player = new Player(new Archer(), name);
        }
    }

    // 스테이지 시작시 기본 스테이지 설정
    public void setStage() {
        stage++; // 스테이지 상승
        battleEndFlag = false; // 전투 시작
        makeEnemy(); // 적 생상
    }

    // 적 생성 (랜덤으로 직업 부여)
    private void makeEnemy() {
        int enemySubSelectRN = (int) (Math.random() * 3);
        switch (enemySubSelectRN) {
            case 0:
                enemy = new Enemy(new Mage()); // 마법사
                break;
            case 1:
                enemy = new Enemy(new Archer()); // 궁수
                break;
            case 2:
                enemy = new Enemy(new Warrior()); // 전사
                break;
            default:
        }
    }

    // 스테이지 종료시 하는 것
    public void endStage() {
        player.endStage(); // 플레이어 회복 및 마나 게이지 초기화
    }

    // 전투가 끝날때 마다 해야 하는 것
    private void endBattle() {
        // 방어 태세 해제
        player.resetDefenseMode();
        enemy.resetDefenseMode();

        checkPlayerDie(); // 플레이어 사망(게임 오버) 확인
        checkEnemyDie(); // 전투 종료 확인
    }

    // 플레이어의 체력이 0이 되면 전투 및 게임 종료
    private void checkPlayerDie() {
        if (player.isDieFlag()) {
            ClearUser[] tempRanking = new ClearUser[ranking.length + 1];

            for (int i = 0; i < ranking.length; i++) {
                tempRanking[i] = ranking[i];
            }

            tempRanking[tempRanking.length - 1] = new ClearUser(stage, player.getName());

            ranking = tempRanking;
            sortRanking();

            battleEndFlag = true; // 전투 종료
            gameOverFlag = true; // 게임 종료
        }
    }

    // 적의 체력이 0이 되면 전투 종료
    private void checkEnemyDie() {
        if (enemy.isDieFlag()) {
            battleEndFlag = true; // 전투 종료
        }
    }

    // 적 행위 결정
    private String decideEnemyPlay() {
        int enemyPlay;
        while(true) {
            enemyPlay = (int) (Math.random() * 4) + 1;
            if(enemyPlay == 4 && checkPossibleUseSkill(enemy) != 0) { // 스킬공격이지만 스킬 게이지가 다 차지 않았으면 재선택
                continue;
            }
            break;
        }

        switch (enemyPlay) {
            case 1: // 공격
                return enemy.attack(player);
            case 2: // 방어
                return enemy.defense();
            case 3: // 마나 회복
                return enemy.addMana();
            case 4: // 스킬
                return enemy.skill(player);
            default:
                return null;
        }
    }

    // 플레이어 공격 시
    public String playerAttack() {
        // 적 행위 상황 - 방어 상태 일 수도 있기에 먼저 행함
        String enemyAction = decideEnemyPlay();
        // 플레이어 행위 상황
        String playerAction = player.attack(enemy);

        endBattle();
        return playerAction + "\n" + enemyAction;
    }

    // 플레이어 방어 시
    public String playerDefense() {
        
        // 플레이어 행위 상황 - 방어는 적보다 먼저 행해야 함
        String playerAction = player.defense();
        // 적 행위 상황
        String enemyAction = decideEnemyPlay();

        endBattle();
        return playerAction + "\n" + enemyAction;
    }

    // 플레이어 마나 회복 시
    public String playerAddMana() {
        // 플레이어 행위 상황
        String playerAction = player.addMana();
        // 적 행위 상황
        String enemyAction = decideEnemyPlay();

        endBattle();
        return playerAction + "\n" + enemyAction;
    }

    // 플레이어 스킬 사용시
    public String playerUseSkill() {
        // 플레이어 행위 상황
        String playerAction = player.skill(enemy);
        // 적 행위 상황
        String enemyAction = decideEnemyPlay();

        endBattle();
        return playerAction + "\n" + enemyAction;
    }

    // 캐릭터의 스킬 사용 가능 확인
    // 사용 가능시 0반환 또는 스킬 사용 가능 까지 남은 마나 회복 횟수 반환
    private int checkPossibleUseSkill(Character character) {
        return character.checkPossibleUseSkill();
    }

    // 플레이어의 남은 마나 게이지를 String 으로 반환
    public int playerLackMana() {
        return checkPossibleUseSkill(player);
    }

    // 플레이어 정보 반환
    public String playerInfo() {
        return player.info();
    }

    // 적 정보 반환
    public String enemyInfo() {
        return enemy.info();
    }

    // getter, setter
    public int getStage() {
        return stage;
    }

    public boolean isBattleEndFlag() {
        return battleEndFlag;
    }

    public boolean isGameOverFlag() {
        return gameOverFlag;
    }
}
