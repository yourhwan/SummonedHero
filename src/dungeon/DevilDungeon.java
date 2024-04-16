package dungeon;

import dungeon.Monster;
import userjob.*;

import java.util.*;

public class DevilDungeon {

    public static void enterDevilDungeon(Scanner scanner, Hero hero) {

        int numDevils = new Random().nextInt(5) + 6;
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

        // 몬스터 공격 스레드
        Thread monsterAttackThread = new Thread(() -> {
            while (!monsters.isEmpty() && hero.isAlive()) {
                try {
                    Thread.sleep(1500); // 1.5초마다
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            }
        });
        monsterAttackThread.start();

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

        try {
            monsterAttackThread.join(); // Wait for monster attack thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    private static void handleSkillSelection(Scanner scanner, Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts) {
        int skillChoice = selectSkill(scanner, hero);
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
                if (hero instanceof SwordMaster) {
                    ((SwordMaster) hero).swordMastery();
                } else {
                    System.out.println("‣유효하지 않은 선택입니다.\n");
                }
                break;
            default:
                System.out.println("‣유효하지 않은 선택입니다.\n");
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


}
