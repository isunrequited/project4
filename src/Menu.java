import java.util.Scanner;

// 사용자에게 보여주는 모든 화면, 텍스트
// 사용자에게 입력을 받아 입력 값을 gm 에게 전달해주는 역할
// gm 으로부터 받은 데이터를 사용자에게 보여주는 역할
// 직접 데이터를 조작하지는 않는다.
public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final GameManager gm = new GameManager();

    //기본 생성자
    public Menu() {
    }

    // 숫자 입력(메뉴, 전투)
    public int inputNum() {
        while(true) {
            int menuNum = 0;
            try {
                System.out.print(">> ");
                menuNum = sc.nextInt();
                return menuNum;
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요!");
                sc.nextLine();
            }
        }
    }

    // 메인 메뉴
    public void mainMenu() {

        //메뉴 시작 메서드
        while (true) {

            System.out.println("\n1. 게임 시작");
            System.out.println("2. 캐릭터 조회");
            System.out.println("3. 랭킹 조회");
            System.out.println("0. 프로그램 종료");

            int menuNum = inputNum();
            System.out.println();

            switch (menuNum){
                case 1:
                    System.out.println("게임을 시작하시겠습니까? [y/n]");
                    System.out.print(">> ");
                    String answer;
                    answer = sc.next();

                    if (answer.equalsIgnoreCase("y")) {
                        System.out.println("게임을 시작합니다.");
                        gameStart();
                    } else if(answer.equalsIgnoreCase("n")) {
                        System.out.println("메뉴로 돌아갑니다.");
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                    break;
                case 2:
                    viewCharacter();
                    break;
                case 3:
                    System.out.println("랭킹을 조회합니다.");
                    gm.showRanking();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    System.exit(0);//프로그램 종료
                default:
                    System.out.println("메뉴를 잘못 입력했습니다.");
            }//switch end


        }//while(true) end
    }//Menu end

    //===================== 1. 게임시작 메서드 ========================
    private void gameStart() {
        while (true) {
            System.out.println("\n# 게임을 시작합니다.");
            int classSelect;   //선택한 캐릭터 입력 변수

            System.out.print("닉네임 입력 >> ");
            String name = sc.next();

            System.out.println();
            System.out.println("직업을 선택하여 주세요.");
            System.out.println("1. 전사");
            System.out.println("2. 마법사");
            System.out.println("3. 궁수");
            System.out.print("직업 선택 ");
            classSelect = inputNum();

            switch (classSelect) {
                case 1:
                    System.out.println("전사를 선택하였습니다.");
                    gm.setPlayerSubject(1, name);
                    //# 캐릭터 별 함수 넣는 곳. 추후 수정 예정=======================
                    break;
                case 2:
                    System.out.println("마법사를 선택하였습니다.");
                    gm.setPlayerSubject(2, name);
                    //# 캐릭터 별 함수 넣는 곳. 추후 수정 예정=======================
                    break;
                case 3:
                    System.out.println("궁사를 선택하였습니다.");
                    gm.setPlayerSubject(3, name);
                    //# 캐릭터 별 함수 넣는 곳. 추후 수정 예정=======================
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    continue;
            }//switch end
            // 게임오버할 때 까지 스테이지 반복
            while(true) {
                stage();
                if(gm.isGameOverFlag()) {
                    gameOver();
                    restart();
                    break;
                }
            }
            break;
        }//while true end
    }//gameStart end

    //======================== 2. 캐릭터 조회 메서드(캐릭터별 스탯을 미리 확인할 수 있음) ========================
    private void viewCharacter() {
        System.out.println(gm.subInfo());
    }

    // 스테이지 끝날 때 메세지
    private void endStage() {
        System.out.println("적이 사망했습니다.");
        System.out.printf("%d 스테이지 클리어!!\n", gm.getStage());
        System.out.println("다음 스테이지로 넘어갑니다.");
        System.out.println("[Enter]");
        sc.nextLine();
    }

    // 재시작 여부 확인, 재시작시 true, 종료시 false 반환
    private void restart() {
        while(true) {
            System.out.println("다시 도전 하시겠습니까? [y/n]");
            System.out.print(">> ");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("y")) { // 재시작
                System.out.println("게임을 다시 시작합니다.");
                gm.setGame();
                return;
            } else if(answer.equalsIgnoreCase("n")) { // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                System.exit(0);//프로그램 종료
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 스테이지 시작시
    private void stage() {
        gm.setStage(); // 스테이지 설정
        battle(); // 전투
        if(!gm.isGameOverFlag()) { // 게임오버가 아닐시 다음 스테이지 준비
            gm.endStage(); // 스테이지 종료
            endStage(); // 스테이지 종료 메세지
        }
    }

    // 전투 돌입시
    // 전투가 끝날 때 까지 반복
    private void battle() {
        while (!gm.isBattleEndFlag()) {
            System.out.println("\n\n=========================");
            System.out.printf("%s 스테이지\n", gm.getStage());
            System.out.println("=========================");
            System.out.println(gm.playerInfo());
            System.out.println(gm.enemyInfo());
            System.out.println("=========================");
            System.out.println("1. 공격");
            System.out.println("2. 방어");
            checkPossibleUseSkill();
            int selectPlay = inputNum();
            sc.nextLine();

            System.out.println();
            switch (selectPlay) {
                case 1:
                    System.out.println(gm.playerAttack());
                    break;
                case 2:
                    System.out.println(gm.playerDefense());
                    break;
                case 3:
                    System.out.println(gm.playerAddMana());
                    break;
                case 4:
                    if(gm.playerLackMana() != 0) { // 스킬 게이지가 다 차지 않아 스킬 사용이 불가능한 경우
                        System.out.println("스킬을 사용 할 수 없습니다.");
                    } else {
                        System.out.println(gm.playerUseSkill());
                    }
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }// end switch

            System.out.println("[Enter]");
            sc.nextLine();
        }
    }

    private void checkPossibleUseSkill() {
        int lackMana = gm.playerLackMana();
        if(lackMana == 0) {
            System.out.println("3. 마나 회복");
            System.out.println("4. 스킬 사용 - 스킬 사용 가능!!");
        } else {
            System.out.printf("3. 마나 회복 - %d회 남음\n", lackMana);
        }
    }

    // 게임 오버시
    private void gameOver() {
        System.out.println("게임 오버");
        System.out.println("최종 스테이지 : " + gm.getStage());
    }
}