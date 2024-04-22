package dungeon;

import rpggame.Story;
import userjob.*;

import java.util.*;

public class DevilDungeon {

    public static void enterDevilDungeon(Scanner scanner, Hero hero) {

        int numDevils = new Random().nextInt(5) + 6;
        Map<String, Integer> devilCounts = new HashMap<>();
        List<Monster> monsters = new ArrayList<>();

        for (int i = 0; i < numDevils; i++) {
            DevilType devilType = DevilType.getRandomDevilType();
            Monster devil = devilType.createInstance();
            monsters.add(devil);
            devilCounts.put(devil.getName(), devilCounts.getOrDefault(devil.getName(), 0) + 1);
        }

        System.out.println("‣악마의 은신처에 진입했습니다. 생성된 몬스터들:");
        for (Map.Entry<String, Integer> entry : devilCounts.entrySet()) {
            System.out.println("‣" + entry.getKey() + ": " + entry.getValue() + " 마리");
        }
        System.out.println();

        // 몬스터 공격 스레드
        DevilDungeonBattleThread battleThread = new DevilDungeonBattleThread(hero, monsters, devilCounts, scanner);
        battleThread.start();

        while (!monsters.isEmpty() && hero.isAlive()) {
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
                        System.out.println("‣적에게 " + damage + "의 피해를 입혔습니다.\n");
                        if (monster.getHp() - damage <= 0) {
                            System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
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
                    System.out.println("‣유효한 선택을 해주세요.\n");
                    continue;
            }

            if (monsters.isEmpty()) {
                System.out.println("‣모든 악마를 물리쳤습니다. 전투에서 승리했습니다!\n");
                break;
            }
        }

        // 전투 종료 후, 마을로 이동 여부 확인
        System.out.println("‣전투가 종료되었습니다. 마을로 돌아가시겠습니까? (돌아가려면 1을 입력하세요)");
        int returnChoice = scanner.nextInt();
        if (returnChoice == 1) {
            System.out.println("‣마을로 돌아갑니다...");
            Story.village(hero, scanner); // 마을로 이동
        }
    }


    private static int selectSkill(Scanner scanner, Hero hero) {
        System.out.println("=================== 스킬 선택 ===================");
        System.out.println("‣스킬을 선택하세요:\n");

        if (hero instanceof SwordMaster) {
            System.out.println("‣1. 패스트 슬래시");
            System.out.println("‣2. 세비지 블로우");
            System.out.println("‣3. 블러드 스트라이크");
            System.out.println("‣4. 파워 스트라이크");
            System.out.println("‣5. 소드 마스터리");
            System.out.println("‣6. 검신의 의지\n");
        } else if (hero instanceof DualBlade) {
            System.out.println("‣1. 세비지 블로우");
            System.out.println("‣2. 인듀어런스");
            System.out.println("‣3. 어둠의 발자국\n");
        } else if (hero instanceof Berserker) {
            System.out.println("‣1. 블러드 스트라이크");
            System.out.println("‣2. 피의 욕망");
            System.out.println("‣3. 광폭화\n");
        } else if (hero instanceof Warrior) {
            System.out.println("‣1. 파워 스트라이크");
            System.out.println("‣2. 가드 마스터");
            System.out.println("‣3. 아머 마스터리\n");
        }
        System.out.println("====================================================");
        return scanner.nextInt();
    }

    private static void handleSkillSelection(Scanner scanner, Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts) {
        while (true) {
            int skillChoice = selectSkill(scanner, hero);
            switch (skillChoice) {
                case 1:
                    if (hero instanceof SwordMaster || hero instanceof Berserker) {
                        useSkill(hero, monsters, devilCounts, "블러드 스트라이크");
                    } else {
                        useSkill(hero, monsters, devilCounts, "패스트 슬래시");
                    }
                    return;
                case 2:
                    if (hero instanceof SwordMaster) {
                        useSkill(hero, monsters, devilCounts, "세비지 블로우");
                    } else if (hero instanceof Berserker) {
                        useSkill(hero, monsters, devilCounts, "피의 욕망");
                    } else if (hero instanceof DualBlade) {
                        useSkill(hero, monsters, devilCounts, "인듀어런스");
                    } else {
                        System.out.println("‣유효하지 않은 선택입니다.\n");
                    }
                    return;
                case 3:
                    if (hero instanceof SwordMaster) {
                        useSkill(hero, monsters, devilCounts, "블러드 스트라이크");
                    } else if (hero instanceof Berserker) {
                        useSkill(hero, monsters, devilCounts, "광폭화");
                    } else if (hero instanceof DualBlade) {
                        useSkill(hero, monsters, devilCounts, "어둠의 발자국");
                    } else {
                        System.out.println("‣유효하지 않은 선택입니다.\n");
                    }
                    return;
                case 4:
                    if (hero instanceof SwordMaster) {
                        useSkill(hero, monsters, devilCounts, "파워 스트라이크");
                    } else if (hero instanceof Warrior) {
                        useSkill(hero, monsters, devilCounts, "가드 마스터");
                    } else {
                        System.out.println("‣유효하지 않은 선택입니다.\n");
                    }
                    return;
                case 5:
                    if (hero instanceof SwordMaster) {
                        ((SwordMaster) hero).swordMastery();
                    } else {
                        System.out.println("‣유효하지 않은 선택입니다.\n");
                    }
                    break;
                case 6:
                    handlePassiveSkill(hero);
                    return;
                default:
                    System.out.println("‣유효하지 않은 선택입니다.\n");
            }
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
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.\n");
                        return;
                    }
                    break;
                case "세비지 블로우":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).savageBlow();
                    } else if (hero instanceof DualBlade) {
                        damage = ((DualBlade) hero).savageBlow();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.\n");
                        return;
                    }
                    break;
                case "블러드 스트라이크":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).bloodStrike();
                    } else if (hero instanceof Berserker) {
                        damage = ((Berserker) hero).bloodStrike();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.\n");
                        return;
                    }
                    break;
                case "파워 스트라이크":
                    if (hero instanceof SwordMaster) {
                        damage = ((SwordMaster) hero).powerStrike();
                    } else if (hero instanceof Warrior) {
                        damage = ((Warrior) hero).powerStrike();
                    } else {
                        System.out.println("‣해당 스킬을 사용할 수 없는 직업입니다.\n");
                        return;
                    }
                    break;
                default:
                    System.out.println("‣유효하지 않은 스킬입니다.\n");
                    return;
            }
            System.out.println("‣" + skillName + "를 사용하여 " + damage + "의 피해를 입혔습니다.\n");

            if (monster.getHp() - damage <= 0) {
                System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
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
        System.out.println("‣패시브 스킬을 사용합니다.\n");

        if (hero instanceof SwordMaster) {
            SwordMaster swordMaster = (SwordMaster) hero;
            swordMaster.usePassiveSkill();
        } else if (hero instanceof DualBlade) {
            DualBlade dualBlade = (DualBlade) hero;
            dualBlade.usePassiveSkill();
        } else if (hero instanceof Berserker) {
            Berserker berserker = (Berserker) hero;
            berserker.usePassiveSkill();
        } else if (hero instanceof Warrior) {
            Warrior warrior = (Warrior) hero;
            warrior.usePassiveSkill();
        } else {
            System.out.println("‣사용할 수 없는 직업 입니다.");
        }

        System.out.println("=========================================================");
    }


}