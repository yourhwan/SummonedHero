package rpggame;

import userjob.*;

import java.util.*;

public class Story {
    public static void main(String[] args) {

        System.out.println("‣Summoned Hero에 접속하셨습니다. 모든 선택 및 답변은 주어지는 선택지의 숫자를 입력해주시면 됩니다." +
                "\n\n‣당신은 마물들의 공격으로 멸망 위기에 처한 세계로 소환이 될 예정입니다." +
                "\n‣만약 소환을 원하지 않는다면 거부할 수도 있습니다." +
                "\n\n‣소환에 응하시겠습니까?" +
                "\n‣1. 수락    2. 거절");

        Scanner scanner = new Scanner(System.in);
        int startChoice = scanner.nextInt();

        boolean gameRunning = (startChoice == 1);

        // 게임 시작 후 메인 스토리 진행
        if (gameRunning) {

            // 캐릭터 생성
            Hero hero = createHero(scanner);

            System.out.println("\n‣소환자들: 이제 이세계에서의 용사님의 모험을 위한 준비는 모두 마무리 되었습니다! " + hero.getJob() + " " + hero.getNickname() + "용사님!" +
                    "\n‣용사님의 정보 확인은 언제든 가능합니다. 이제 모험을 통해 더욱 강해지시고, 절망에 물든 이 세상을 구원해주세요!\n\n\n");

            int choice;
            do {
                // 기본 화면
                System.out.println("\n================================== 마을 ==================================");
                System.out.println("1. " + hero.getNickname() + " 의 정보 확인");
                System.out.println("2. 상점");
                System.out.println("3. 마물 던전");
                System.out.println("4. 모험 종료");
                System.out.println("==========================================================================");

                choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        // 마을로 돌아가기
                        break;
                    case 1:
                        System.out.println(hero); // 사용자 정보
                        break;
                    case 2:
                        goToStore(scanner, hero); // 상점
                        break;
                    case 3:
                        // enterDungeon(scanner, hero);
                        break;
                    case 4:
                        System.out.println("================================ 모험을 종료합니다. ================================");
                        System.exit(0);
                    default:
                        System.out.println("✘ 행선지를 다시 확인해주세요! ✘");
                }
            } while (choice != 0);
        }

        /////////////////////////// 게임 종료 ///////////////////////////////
        else {
            System.out.println("‣소환을 거부했습니다. Summoned Hero를 종료합니다.");
        }
    }

    // 캐릭터 생성 메서드
    private static Hero createHero(Scanner scanner) {

        Hero hero = null;
        boolean validChoice = false;

        while (!validChoice) {

            System.out.println("‣이세계의 간절함이 당신의 마음을 움직였습니다. 당신은 이세계에 소환되었습니다." +
                    "\n\n‣소환자들: 저희의 간절함이 용사님께 닿았군요... 용사님...! 마물들의 침략으로 몰락해가는 저희 세계를 구원해주세요!!");

            // 직업 선택 인터페이스
            System.out.println("\n‣소환자들: 용사님은 이세계에서 원하시는 클래스를 마음대로 선택하실 수 있습니다. 저희가 용사님을 위해 준비해두었습니다." +
                    "\n‣마음에 드는 클래스를 선택해주세요. 클래스 선택의 기회는 단 한번 뿐이니 신중히 결정해주세요.\n");
            System.out.println("1. 듀얼블레이드");
            System.out.println("2. 소드마스터");
            System.out.println("3. 버서커");
            System.out.println("4. 워리어");
            int jobChoice = scanner.nextInt();

            // 닉네임 설정 인터페이스
            System.out.println("\n‣소환자들: 탁월하신 선택입니다! 이런 저희가 용사님의 성함을 먼저 여쭤봤어야 했는데... 저희가 용사님을 어떻게 부르면 될까요?");
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
                    System.out.println("‣올바르지 않은 선택입니다. 보기의 숫자 중에서 다시 선택해주세요.");
            }

            if (validChoice) {
                // Set the nickname for the character
                hero.setNickname(nickname);
            }
        }

        return hero;
    }

    // 상점 메서드
    private static void goToStore(Scanner scanner, Hero hero) {

        int buyingChoice;

        do {
            System.out.println("\n\n================================ 상점 ================================");
            System.out.println("[1. HP 증가 영약: 3000원 -> 10 증가]  [2. MP 증가 영약: 2000원 -> 20 증가]  [3.공격력 증가 영약: 5000원 -> 10 증가]");
            System.out.println("[0. 마을로 돌아가기]");
            System.out.println("===============================================================");
            System.out.println("‣" + hero.getNickname() + " 의 소지금: " + hero.getMoney()+"\n\n");

            buyingChoice = scanner.nextInt();

            switch (buyingChoice) {
                case 1:
                    if(hero.getMoney() >= 3000) {
                        hero.setMoney(hero.getMoney() - 3000);
                        hero.setHp(hero.getHp() + 10);
                        hero.setMaxHp(hero.getMaxHp() + 10);
                        hero.setInitialHp(hero.getInitialHp() + 10);
                        hero.setInitialMaxHp(hero.getInitialMaxHp());
                        System.out.println("‣HP 증가 영약을 구매했습니다.");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney());
                    }
                    break;
                case 2:
                    if(hero.getMoney() >= 2000) {
                        hero.setMoney(hero.getMoney() - 2000);
                        hero.setMp(hero.getMp() + 20);
                        hero.setMaxMp(hero.getMaxMp() + 20);
                        hero.setInitialMp(hero.getInitialMp() + 20);
                        hero.setInitialMaxMp(hero.getInitialMaxMp());
                        System.out.println("‣MP 증가 영약을 구매했습니다.");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney());
                    }
                    break;
                case 3:
                    if(hero.getMoney() >= 5000) {
                        hero.setMoney(hero.getMoney() - 5000);
                        hero.setBasicAttackDamage(hero.getBasicAttackDamage() + 10);
                        System.out.println("‣공격력 증가 영약을 구매했습니다.");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney());
                    }
                    break;
                default:
                    System.out.println("‣어떤 상품을 원하시나요? 다시 요청해주시면 가장 좋은 물건으로 드리겠습니다!!");

            }

        } while (buyingChoice != 0) ;

    }
}





