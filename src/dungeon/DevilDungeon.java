package dungeon;

import item.FireSword;
import item.PoisonSword;
import rpggame.Story;
import userjob.*;

import java.util.*;

import static userjob.Hero.startWeaponThread;

public class DevilDungeon {
    private static boolean battleOver = false; // 전투 종료여부 확인용

    public static void enterDevilDungeon(Scanner scanner, Hero hero) {

        try {
            int numDevils = new Random().nextInt(5) + 6;
            Map<String, Integer> devilCounts = new HashMap<>();
            List<Monster> monsters = new ArrayList<>();

            for (int i = 0; i < numDevils; i++) {
                DevilType devilType = DevilType.getRandomDevilType();
                Monster devil = devilType.createInstance();
                monsters.add(devil);
                devilCounts.put(devil.getName(), devilCounts.getOrDefault(devil.getName(), 0) + 1);
            }

            System.out.println("‣고블린 마을에 진입했습니다. 생성된 몬스터들:");
            for (Map.Entry<String, Integer> entry : devilCounts.entrySet()) {
                System.out.println("‣" + entry.getKey() + ": " + entry.getValue() + " 마리");
            }
            System.out.println();

            // 전투 스레드 실행
            DevilDungeonBattleThread battleThread = new DevilDungeonBattleThread(hero, monsters, devilCounts);
            battleThread.start();


            Scanner villageScanner = new Scanner(System.in); // 마을에서 사용할 스캐너 객체 생성

            while (!battleOver) {
                System.out.println("========================= 전투 메뉴 =========================");
                System.out.println("\n‣행동을 선택하세요:\n");
                System.out.println("‣1. 기본 공격");
                System.out.println("‣2. 스킬 사용\n");
                System.out.println("===========================================================");

                int actionChoice = scanner.nextInt();

                switch (actionChoice) {
                    case 1:
                        int totalMonsterHP = monsters.stream().mapToInt(Monster::getHp).sum();
                        for (Monster monster : monsters) {
                            int damage = hero.useBasicAttack();
                            System.out.println("‣적에게 " + damage + "의 피해를 입혔습니다.");
                            if (monster.getHp() - damage <= 0) {
                                System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!");
                                monsters.remove(monster);
                                devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
                                hero.gainExp(monster.dropExp());
                                hero.gainMoney(monster.dropMoney());
                                break;
                            }
                        }
                        break;
                    case 2:
                        handleSkillSelection(scanner, hero, monsters, devilCounts);
                        break;
                    default:
                        System.out.println("‣유효한 선택을 해주세요.");
                        continue;
                }

                if (monsters.isEmpty()) {
                    System.out.println("‣모든 고블린을 물리쳤습니다. 전투에서 승리했습니다!");

                    System.out.println("‣전투가 종료되었습니다. 마을로 돌아가시겠습니까? (돌아가려면 1을 입력하세요)");
                    int returnChoice = scanner.nextInt();
                    if (returnChoice == 1) {
                        System.out.println("‣마을로 돌아갑니다...");
                        Story.village(hero, scanner);
                    }
                    break;
                }
            }

            if (!hero.isAlive()) {
                System.out.println("‣전투에서 패배했습니다. 마을로 돌아갑니다.");
                Story.village(hero, villageScanner);

            }

        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 올바른 숫자를 입력해주세요.");
            scanner.nextLine(); // 버퍼 비우기
        }
    }

    public static boolean isBattleOver() {
        return battleOver;
    }

    public static void setBattleOver(boolean value) {
        battleOver = value;
    }


    private static void handleSkillSelection(Scanner scanner, Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts) {
        System.out.println("=================== 스킬 선택 ===================");
        System.out.println("‣스킬을 선택하세요:");

        String jobName = hero.getClass().getSimpleName();
        int numSkills = 0;

        if (jobName.equals("SwordMaster")) {
            System.out.println("‣1. 패스트 슬래시");
            System.out.println("‣2. 세비지 블로우");
            System.out.println("‣3. 블러드 스트라이크");
            System.out.println("‣4. 파워 스트라이크");
            System.out.println("‣5. 소드 마스터리");
            System.out.println("‣6. 검신의 의지");
            numSkills = 6;
        } else if (jobName.equals("DualBlade")) {
            System.out.println("‣1. 세비지 블로우");
            System.out.println("‣2. 인듀어런스");
            System.out.println("‣3. 어둠의 발자국");
            numSkills = 3;
        } else if (jobName.equals("Berserker")) {
            System.out.println("‣1. 블러드 스트라이크");
            System.out.println("‣2. 피의 욕망");
            System.out.println("‣3. 광폭화");
            numSkills = 3;
        } else if (jobName.equals("Warrior")) {
            System.out.println("‣1. 파워 스트라이크");
            System.out.println("‣2. 가드 마스터");
            System.out.println("‣3. 아머 마스터리");
            numSkills = 3;
        }

        System.out.println("====================================================");

        while (true) {
            System.out.print("‣선택: ");
            int skillChoice = scanner.nextInt();
            if (skillChoice >= 1 && skillChoice <= numSkills) {
                handleSkill(hero, monsters, devilCounts, skillChoice);
                break;
            } else {
                System.out.println("‣유효하지 않은 선택입니다.");
            }
        }
    }

    private static void handleSkill(Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts, int skillChoice) {

        String jobName = hero.getClass().getSimpleName();

        switch (jobName) {
            case "SwordMaster":
                switch (skillChoice) {
                    case 1:
                        useSkill(hero, monsters, devilCounts, "패스트 슬래시");
                        break;
                    case 2:
                        useSkill(hero, monsters, devilCounts, "세비지 블로우");
                        break;
                    case 3:
                        useSkill(hero, monsters, devilCounts, "블러드 스트라이크");
                        break;
                    case 4:
                        useSkill(hero, monsters, devilCounts, "파워 스트라이크");
                        break;
                    case 5:
                        ((SwordMaster) hero).swordMastery(); // 소드마스터리 사용
                        break;
                    case 6:
                        handlePassiveSkill(hero); // 검신의 의지 사용
                        break;
                    default:
                        System.out.println("‣유효하지 않은 선택입니다.");
                        break;
                }
                break;
            case "DualBlade":
                switch (skillChoice) {
                    case 1:
                        useSkill(hero, monsters, devilCounts, "세비지 블로우");
                        break;
                    case 2:
                        ((DualBlade) hero).endurance(); // 인듀어런스 사용
                        break;
                    case 3:
                        handlePassiveSkill(hero); // 어둠의 발자국 사용
                        break;
                    default:
                        System.out.println("‣유효하지 않은 선택입니다.");
                        break;
                }
                break;
            case "Berserker":
                switch (skillChoice) {
                    case 1:
                        useSkill(hero, monsters, devilCounts, "블러드 스트라이크");
                        break;
                    case 2:
                        ((Berserker) hero).bloodLust(); // 블러드 러스트 사용
                        break;
                    case 3:
                        handlePassiveSkill(hero); // 광폭화 사용
                        break;
                    default:
                        System.out.println("‣유효하지 않은 선택입니다.");
                        break;
                }
                break;
            case "Warrior":
                switch (skillChoice) {
                    case 1:
                        useSkill(hero, monsters, devilCounts, "파워 스트라이크");
                        break;
                    case 2:
                        ((Warrior) hero).guardMaster(); // 가드 마스터 사용
                        break;
                    case 3:
                        handlePassiveSkill(hero); // 아머 마스터리 사용
                        break;
                    default:
                        System.out.println("‣유효하지 않은 선택입니다.");
                        break;
                }
                break;
            default:
                System.out.println("‣유효하지 않은 선택입니다.");
                break;
        }
    }




    private static void useSkill(Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts, String skillName) {
        int damage = 0;
        for (Monster monster : monsters) {
            switch (skillName) {
                case "패스트 슬래시":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).fastSlash();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.");
                        return;
                    }
                    break;
                case "세비지 블로우":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).savageBlow();
                    } else if (hero instanceof DualBlade) {
                        damage = ((DualBlade) hero).savageBlow();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.");
                        return;
                    }
                    break;
                case "블러드 스트라이크":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).bloodStrike();
                    } else if (hero instanceof Berserker) {
                        damage = ((Berserker) hero).bloodStrike();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.");
                        return;
                    }
                    break;
                case "파워 스트라이크":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).powerStrike();
                    } else if (hero instanceof Warrior) {
                        damage = ((Warrior) hero).powerStrike();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.");
                        return;
                    }
                    break;
                default:
                    System.out.println("‣유효하지 않은 스킬입니다.");
                    return;
            }
            System.out.println("‣" + skillName + "를 사용하여 " + damage + "의 피해를 입혔습니다.");

            if (monster.getHp() - damage <= 0) {
                System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!");
                monsters.remove(monster);
                devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
                hero.gainExp(monster.dropExp());
                hero.gainMoney(monster.dropMoney());
                break;
            }
        }
    }

    private static void handlePassiveSkill(Hero hero) {
        System.out.println("=================== 패시브 스킬 사용 ===================");
        System.out.println("‣패시브 스킬을 사용합니다.");

        hero.usePassiveSkill();

        System.out.println("=========================================================");
    }


}