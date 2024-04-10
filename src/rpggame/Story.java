package rpggame;

import userjob.*;

import java.util.*;

public class Story {
    public static void main(String[] args) {

        System.out.println("Summoned Hero에 접속하셨습니다." +
                "\n당신은 마물들의 공격으로 멸망 위기에 처한 세계로 소환이 될 예정입니다." +
                "\n만약 소환을 원하지 않는다면 거부할 수 있습니다. 소환에 응하시겠습니까?");

        Scanner scanner = new Scanner(System.in);

        // 캐릭터 생성
        Hero hero = createHero(scanner);

        // 캐릭터 생성 후 정보 확인
        System.out.println(hero);
    }

    private static Hero createHero(Scanner scanner) {

        Hero hero = null;
        boolean validChoice = false;

        while (!validChoice) {

            // 직업 선택 인터페이스
            System.out.println("용사님의 직업은 무엇인가요?:");
            System.out.println("1. 듀얼블레이드");
            System.out.println("2. 소드마스터");
            System.out.println("3. 버서커");
            System.out.println("4. 워리어");
            int jobChoice = scanner.nextInt();

            // 닉네임 설정 인터페이스
            System.out.println("용사님의 성함은 무엇인가요? : ");
            scanner.nextLine();
            String nickname = scanner.nextLine();

            // 사용자 입력에 따른 직업설정
            switch (jobChoice) {
                case 1:
                    hero = new DualBlade();
                    validChoice = true;
                    break;
                case 2:
                    hero = new SwordMaster();
                    validChoice = true;
                    break;
                case 3:
                    hero = new Berserker();
                    validChoice = true;
                    break;
                case 4:
                    hero = new Warrior();
                    validChoice = true;
                    break;
                default:
                    System.out.println("올바르지 않은 선택입니다. 보기의 숫자 중에서 다시 선택해주세요.");
            }

            if (validChoice) {
                // Set the nickname for the character
                hero.setNickname(nickname);
            }
        }

        return hero;
    }
}





