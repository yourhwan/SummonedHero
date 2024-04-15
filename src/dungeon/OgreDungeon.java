package dungeon;

import userjob.*;

import java.util.*;

public class OgreDungeon {

    public static void enterOgreDungeon(Scanner scanner, Hero hero) {

        int numOgres = new Random().nextInt(3) + 3; // 랜덤으로 3에서 5까지의 오거 생성
        Map<String, Integer> ogreCounts = new HashMap<>();
        List<Monster> monsters = new ArrayList<>();

        int numOni = 0;
        int numKijin = 0;
        int numKishin = 0;

        for (int i = 0; i < numOgres; i++) {
            int ogreType = new Random().nextInt(3);
            Monster ogre;
            switch (ogreType) {
                case 0:
                    ogre = new Oni();
                    numOni++;
                    break;
                case 1:
                    ogre = new Kijin();
                    numKijin++;
                    break;
                case 2:
                    ogre = new Kishin();
                    numKishin++;
                    break;
                default:
                    ogre = new Oni();
                    numOni++;
                    break;
            }
            monsters.add(ogre);
        }

        ogreCounts.put("Oni", numOni);
        ogreCounts.put("Kijin", numKijin);
        ogreCounts.put("Kishin", numKishin);

        System.out.println("‣Entered the Ogre Dungeon. Monsters created:");
        for (Map.Entry<String, Integer> entry : ogreCounts.entrySet()) {
            System.out.println("‣" + entry.getKey() + ": " + entry.getValue() + " monsters");
        }
        System.out.println();

        while (!monsters.isEmpty() && hero.isAlive()) {
            System.out.println("\n‣Select an action:\n");
            System.out.println("‣1. Basic Attack");
            System.out.println("‣2. Use Skill\n");

            int actionChoice = scanner.nextInt();

            switch (actionChoice) {
                case 1:
                    int totalMonsterHP = monsters.stream().mapToInt(Monster::getHp).sum();
                    for (Monster monster : monsters) {
                        int damage = hero.useBasicAttack();
                        System.out.println("‣Dealt " + damage + " damage to the enemy.\n");
                        if (monster.getHp() - damage <= 0) {
                            System.out.println("‣Defeated " + monster.getName() + "!\n");
                            monsters.remove(monster);
                            ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                                        System.out.println("‣" + monster.getName() + " 을(를) 처치했습니다!\n");
                                        monsters.remove(monster);
                                        ogreCounts.put(monster.getName(), ogreCounts.get(monster.getName()) - 1);
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
                    System.out.println("‣Please make a valid selection.\n");
                    continue;
            }

            if (monsters.isEmpty()) {
                System.out.println("‣Defeated all monsters. Won the battle!\n");
                break;
            }

            // Monster's turn
            System.out.println("\n‣Monster's Turn:\n");
            int totalHeroHP = hero.getHp();
            for (Monster monster : monsters) {
                int damage = monster.randomAttack(hero);
                System.out.println("‣" + monster.getName() + " dealt " + damage + " damage to you.\n");
                if (hero.getHp() <= 0) {
                    System.out.println("‣Lost the battle.\n");
                    hero.revert();
                    return;
                }
            }
            if (totalHeroHP == hero.getHp()) {
                System.out.println("‣Monster's attack missed!\n");
            }
        }
    }

    private static int selectSkill(Scanner scanner, Hero hero) {
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
        return scanner.nextInt();
    }
}
