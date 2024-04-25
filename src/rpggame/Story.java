package rpggame;

import dungeon.AngelDungeon;
import dungeon.DevilDungeon;
import dungeon.GoblinDungeon;
import dungeon.OgreDungeon;
import item.FireSword;
import item.PoisonSword;
import userjob.*;

import java.util.*;

public class Story {
    public static void main(String[] args) {

//        String filePath = "EmbracingMe.wav";
//
//        BackgroundMusicThread musicThread = new BackgroundMusicThread(filePath);
//        musicThread.start();

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

            // 마을로 이동
            village(hero, scanner);
        }

        else {
            System.out.println("‣소환을 거부했습니다. Summoned Hero를 종료합니다.");
        }
    }

    // 마을 메서드
    public static void village(Hero hero, Scanner scanner) {

        int choice = 0;

        do {
            // 기본 화면
            System.out.println("\n====== ▷ 마을 ◁ =====================================[LV."+ hero.getLevel() +" "+ hero.getJob()+ " " + hero.getNickname() +"]===");
            System.out.println("1. " + hero.getNickname() + " 의 정보 확인");
            System.out.println("2. 상점");
            System.out.println("3. 마물 던전");
            System.out.println("4. 모험 종료");
            System.out.println("===========================================================================");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                System.out.println("입력된 선택지: " + choice); // 디버깅용 출력
            } else {
                System.out.println("잘못된 입력이 감지되었습니다: " + scanner.next());
                System.out.println("올바른 정수를 입력해주세요."); // 디버깅용 출력
                scanner.nextLine(); // 잘못된 입력값 제거
            }


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
                    enterDungeon(scanner, hero); // 던전 입장 선택
                    break;
                case 4:
                    System.out.println("================================ 모험을 종료합니다. ================================");
                    System.exit(0);
                default:
                    System.out.println("✘ 행선지를 다시 확인해주세요! ✘");
            }
        } while (choice != 0);
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
            System.out.println("1. 듀얼블레이드 -> 양손검을 사용하는 클래스. 치명타 확률이 가장 높지만 적은 HP를 가지고 있다.");
            System.out.println("2. 소드마스터 -> 모든 검을 자유자재로 사용하는 클래스. 전 직업의 스킬을 사용할 수 있지만 타직업 스킬 사용시 MP와 HP를 모두 소모한다.");
            System.out.println("3. 버서커 -> 광폭화와 대검을 사용하는 클래스 -> 높은 체력과 치명타 확률을 보유한 클래스로 유려한 전투가 가능하다.");
            System.out.println("4. 워리어 -> 롱소드를 사용하는 기본에 충실한 클래스 -> 높은 체력과 공격력을 보유한 클래스로 안정적인 전투가 가능하다.");
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
            System.out.println("어서오세요 용사님~! 용사님의 모험을 위한 신비로운 물건들이 준비되어 있습니다! 편하게 말씀해주세요!!!!\n");
            System.out.println("\n\n================================ 상점 ================================");
            System.out.println("[1. HP 증가 영약: 3000원 -> 10 증가]  [2. MP 증가 영약: 2000원 -> 20 증가]  [3.공격력 증가 영약: 5000원 -> 10 증가]  [4.불의 검: 5000원 -> 지속데미지 10]  [3.독의 검: 5000원 -> 지속데미지 10]");
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
                        System.out.println("\n‣HP 증가 영약을 구매했습니다.\n");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("\n‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney() + "\n\n");
                    }
                    break;
                case 2:
                    if(hero.getMoney() >= 2000) {
                        hero.setMoney(hero.getMoney() - 2000);
                        hero.setMp(hero.getMp() + 20);
                        hero.setMaxMp(hero.getMaxMp() + 20);
                        hero.setInitialMp(hero.getInitialMp() + 20);
                        hero.setInitialMaxMp(hero.getInitialMaxMp());
                        System.out.println("\n‣MP 증가 영약을 구매했습니다.\n");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("\n‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney() + "\n\n");
                    }
                    break;
                case 3:
                    if(hero.getMoney() >= 5000) {
                        hero.setMoney(hero.getMoney() - 5000);
                        hero.setBasicAttackDamage(hero.getBasicAttackDamage() + 10);
                        System.out.println("\n‣공격력 증가 영약을 구매했습니다.\n");
                        System.out.println(hero);
                    }
                    else {
                        System.out.println("\n‣소지금이 부족합니다. 현재 보유 중인 소지금 : " + hero.getMoney() + "\n\n");
                    }
                    break;
                case 4: // 화염 검 구매
                    if (hero.getMoney() >= 5000) { // 가격 확인
                        hero.setMoney(hero.getMoney() - 5000); // 돈 차감
                        System.out.println("\n‣화염 검을 구매했습니다.\n");
                        hero.equipWeapon(new FireSword()); // 화염 검 장착
                    } else {
                        System.out.println("\n‣돈이 부족합니다. 현재 소지금: " + hero.getMoney() + "\n\n");
                    }
                    break;
                case 5: // 독 검 구매
                    if (hero.getMoney() >= 5000) { // 가격 확인
                        hero.setMoney(hero.getMoney() - 5000); // 돈 차감
                        System.out.println("\n‣독 검을 구매했습니다.\n");
                        hero.equipWeapon(new PoisonSword()); // 독 검 장착
                    } else {
                        System.out.println("\n‣돈이 부족합니다. 현재 소지금: " + hero.getMoney() + "\n\n");
                    }
                    break;
                case 0:
                    System.out.println("\n‣감사합니다 용사님~ 또 들러주세요!!!.");
                    return; // 마을로 이동
                default:
                    System.out.println("\n‣어떤 상품을 원하시나요? 다시 요청해주시면 가장 좋은 물건으로 드리겠습니다!!\n");
            }

        } while (true) ;

    }

    // 던전
    public static void enterDungeon(Scanner scanner, Hero hero) {

        int choice = -1;

        do {
            System.out.println("‣던전 문지기: 용사님!! 전투를 위한 준비는 잘 마치셨습니까? 건투를 빕니다!!!!!\n");
            System.out.println("=========================== 던전 ===========================");
            System.out.println("‣1. 고블린 마을 -> 난이도: 하");
            System.out.println("‣2. 오거 마을 -> 난이도 : 중");
            System.out.println("‣3. 악마의 은신처 -> 난이도 : 상");
            System.out.println("‣4. 타락한 천사들의 천궁 -> 난이도 : 최상");
            System.out.println("[‣0. 마을로 돌아가기]");
            System.out.println("===============================================================\n");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // 고블린 던전
                        System.out.println("‣고블린 마을에 입장합니다...\n");
                        GoblinDungeon.enterGoblinDungeon(scanner, hero);
                        break;
                    case 2:
                        // 오거 던전
                        System.out.println("‣오거 마을에 입장합니다...\n");
                        OgreDungeon.enterOgreDungeon(scanner, hero);
                        break;
                    case 3:
                        // 악마 던전
                        System.out.println("‣악마의 은신처로 입장합니다...\n");
                        DevilDungeon.enterDevilDungeon(scanner, hero);
                        break;
                    case 4:
                        // 천사 던전
                        System.out.println("‣타락한 천사들의 천궁으로 입장합니다...\n");
                        AngelDungeon.enterAngelDungeon(scanner, hero);
                        break;
                    case 0:
                        // 마을
                        return;
                    default:
                        System.out.println("‣올바른 던전을 선택해주세요.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("‣숫자를 입력해주세요.\n");
                scanner.next(); // 잘못된 입력을 처리하기 위해 입력 버퍼를 비움
            }
        } while (choice != 0);
    }


}