package dungeon;

import userjob.*;

import java.util.*;

public class DevilDungeon {

    public static void enterDevilDungeon(Scanner scanner, Hero hero) {

        int numDevils = new Random().nextInt(5) + 6; // 랜덤으로 5에서 10까지의 악마 생성
        Map<String, Integer> devilCounts = new HashMap<>();
        List<Monster> monsters = new ArrayList<>();

        int numMedievalDevil = 0;
        int numModernDevil = 0;
        int numAncientDevil = 0;
        int numPrimordialDevil = 0;

        for (int i = 0; i < numDevils; i++) {
            int devilType = new Random().nextInt(4);
            Monster devil;
            switch (devilType) {
                case 0:
                    devil = new MedievalDevil();
                    numMedievalDevil++;
                    break;
                case 1:
                    devil = new ModernDevil();
                    numModernDevil++;
                    break;
                case 2:
                    devil = new AncientDevil();
                    numAncientDevil++;
                    break;
                case 3:
                    devil = new PrimordialDevil();
                    numPrimordialDevil++;
                    break;
                default:
                    devil = new MedievalDevil();
                    numMedievalDevil++;
                    break;
            }
            monsters.add(devil);
        }

        devilCounts.put("중세 악마", numMedievalDevil);
        devilCounts.put("현대 악마", numModernDevil);
        devilCounts.put("고대 악마", numAncientDevil);
        devilCounts.put("원시 악마", numPrimordialDevil);

        System.out.println("‣악마의 은신처에 진입했습니다. 생성된 몬스터들:");
        for (Map.Entry<String, Integer> entry : devilCounts.entrySet()) {
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
                            devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                                        devilCounts.put(monster.getName(), devilCounts.get(monster.getName()) - 1);
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
                System.out.println("‣모든 악마를 물리쳤습니다. 전투에서 승리했습니다!\n");
                break;
            }

            // 악마의 턴
            System.out.println("\n‣몬스의 턴:\n");
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
                System.out.println("‣악마의 공격이 빗나갔습니다!\n");
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

