// 게임을 실행하는 역할
public class GamePlay {

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.mainMenu();
    }
}

/*
*

스테이지 1
전투를 시작합니다.

set stage (스테이지 설정)

{
플레이어 정보
적 정보

1. 공격
2. 방어
3. 스킬
선택 :

결과값을 게임메니저에 보내줌 (battle)

게임오버 됬는지 확인
스테이지가 끝났느지 확인(적이 죽으면 끝)
}

안끝났으면 반복

끝났으면
end stage

스테이지 2
.....




게임오버시

게임오버!
5스테이지까지 도달하였습니다.
*
*/