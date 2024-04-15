package dungeon;

import userjob.*;

import java.util.*;

public class AngelDungeon {

    public static void enterAngelDungeon(Scanner scanner, Hero hero) {
        // 천사의 수를 5에서 10 사이의 랜덤한 값으로 생성합니다.
        int numAngels = new Random().nextInt(5) + 16;
        // 각 천사 종류의 수를 저장하는 맵입니다.
        Map<String, Integer> angelCounts = new HashMap<>();
        // 모든 천사 객체를 저장하는 리스트입니다.
        List<Monster> monsters = new ArrayList<>();

        // 각 천사 종류별 수를 초기화합니다.
        int numCourageAngel = 0;
        int numHopeAngel = 0;
        int numFateAngel = 0;
        int numWisdomAngel = 0;
        int numJusticeAngel = 0;

        // 랜덤하게 생성된 천사 수만큼 반복합니다.
        for (int i = 0; i < numAngels; i++) {
            // 랜덤한 값을 통해 천사 종류를 결정합니다.
            int angelType = new Random().nextInt(5);
            Monster angel;
            // 천사 종류에 따라 객체를 생성하고 수를 증가시킵니다.
            switch (angelType) {
                case 0:
                    angel = new CourageAngel();
                    numCourageAngel++;
                    break;
                case 1:
                    angel = new HopeAngel();
                    numHopeAngel++;
                    break;
                case 2:
                    angel = new FateAngel();
                    numFateAngel++;
                    break;
                case 3:
                    angel = new WisdomAngel();
                    numWisdomAngel++;
                    break;
                case 4:
                    angel = new JusticeAngel();
                    numJusticeAngel++;
                    break;
                default:
                    angel = new CourageAngel();
                    numCourageAngel++;
                    break;
            }
            monsters.add(angel);
        }

        // 각 천사 종류별 수를 맵에 저장합니다.
        angelCounts.put("용기의 대천사", numCourageAngel);
        angelCounts.put("희망의 대천사", numHopeAngel);
        angelCounts.put("운명의 대천사", numFateAngel);
        angelCounts.put("지혜의 대천사", numWisdomAngel);
        angelCounts.put("정의의 대천사", numJusticeAngel);

        // 천사 던전 진입 메시지를 출력합니다.
        System.out.println("‣천사들의 궁전에 진입했습니다. 마주에 천사들:");
        for (Map.Entry<String, Integer> entry : angelCounts.entrySet()) {
            // 각 천사 종류별 수를 출력합니다.
            System.out.println("‣" + entry.getKey() + ": " + entry.getValue() + " 마리");
        }
        System.out.println();

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
                            angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                            hero.gainExp(monster.dropExp());
                            hero.gainMoney(monster.dropMoney());
                            break;
                        }
                    }
                    break;
                case 2:
                    if (hero instanceof SwordMaster) {
                        SwordMaster swordMaster = (SwordMaster) hero;
                        int skillChoice = selectSkill(scanner, swordMaster);
                        switch (skillChoice) {
                            case 1:
                                int damage = swordMaster.fastSlash();
                                for (Monster monster : monsters) {
                                    System.out.println("‣패스트 슬래시를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                damage = swordMaster.savageBlow();
                                for (Monster monster : monsters) {
                                    System.out.println("‣세비지 블로우를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                damage = swordMaster.bloodStrike();
                                for (Monster monster : monsters) {
                                    System.out.println("‣블러드 스트라이크를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 4:
                                damage = swordMaster.powerStrike();
                                for (Monster monster : monsters) {
                                    System.out.println("‣파워 스트라이크를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 5:
                                swordMaster.swordMastery();
                                break;
                            default:
                                System.out.println("‣유효하지 않은 선택입니다.\n");
                        }
                    } else if (hero instanceof DualBlade) {
                        DualBlade dualBlade = (DualBlade) hero;
                        int skillChoice = selectSkill(scanner, dualBlade);
                        switch (skillChoice) {
                            case 1:
                                int damage = dualBlade.savageBlow();
                                for (Monster monster : monsters) {
                                    System.out.println("‣세비지 블로우를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                dualBlade.indurance();
                                break;
                            default:
                                System.out.println("‣유효하지 않은 선택입니다.\n");
                        }
                    } else if (hero instanceof Berserker) {
                        Berserker berserker = (Berserker) hero;
                        int skillChoice = selectSkill(scanner, berserker);
                        switch (skillChoice) {
                            case 1:
                                int damage = berserker.bloodStrike();
                                for (Monster monster : monsters) {
                                    System.out.println("‣블러드 스트라이크를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                berserker.bloodLust();
                                break;
                            default:
                                System.out.println("‣유효하지 않은 선택입니다.\n");
                        }
                    } else if (hero instanceof Warrior) {
                        Warrior warrior = (Warrior) hero;
                        int skillChoice = selectSkill(scanner, warrior);
                        switch (skillChoice) {
                            case 1:
                                int damage = warrior.powerStrike();
                                for (Monster monster : monsters) {
                                    System.out.println("‣파워 스트라이크를 사용하여 " + damage + "의 피해를 입혔습니다.\n");
                                    if (monster.getHp() - damage <= 0) {
                                        System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        angelCounts.put(monster.getName(), angelCounts.get(monster.getName()) - 1);
                                        hero.gainExp(monster.dropExp());
                                        hero.gainMoney(monster.dropMoney());
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                warrior.guardMaster();
                                break;
                            default:
                                System.out.println("‣유효하지 않은 선택입니다.\n");
                        }
                    } else {
                        System.out.println("‣이 직업에게 유효하지 않은 선택입니다.\n");
                    }
                    break;
                default:
                    System.out.println("‣유효한 선택을 해주세요.\n");
                    continue;
            }

            if (monsters.isEmpty()) {
                System.out.println("‣모든 천사족을 물리쳤습니다. 전투에서 승리했습니다!\n");
                break;
            }

            // 악마의 턴
            System.out.println("\n‣몬스터의 턴:\n");
            int totalHeroHP = hero.getHp();
            for (Monster monster : monsters) {
                int damage = monster.randomAttack(hero);
                System.out.println("‣" + monster.getName() + "이(가) 당신에게 " + damage + "의 피해를 입혔습니다.\n");
                if (hero.getHp() <= 0) {
                    System.out.println("========================= 전투 결과 =========================");
                    System.out.println("‣전투에서 패배했습니다.\n");
                    System.out.println("‣내 정보");
                    System.out.println(hero); // 사용자 정보
                    System.out.println("==========================================================");
                    hero.revert();
                    return;
                }
            }
            if (totalHeroHP == hero.getHp()) {
                System.out.println("‣천사의 공격이 빗나갔습니다!\n");
            }
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
            System.out.println("‣5. 소드 마스터리\n");
        } else if (hero instanceof DualBlade) {
            System.out.println("‣1. 세비지 블로우");
            System.out.println("‣2. 인듀어런스\n");
        } else if (hero instanceof Berserker) {
            System.out.println("‣1. 블러드 스트라이크");
            System.out.println("‣2. 피의 욕망\n");
        } else if (hero instanceof Warrior) {
            System.out.println("‣1. 파워 스트라이크");
            System.out.println("‣2. 가드 마스터\n");
        }
        System.out.println("====================================================");
        return scanner.nextInt();
    }
}