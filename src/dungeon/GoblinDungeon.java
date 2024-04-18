package dungeon;

import rpggame.Story;
import userjob.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GoblinDungeon {

    public static void enterGoblinDungeon(Scanner scanner, Hero hero) { // 던전 진입 메소드

        // 고블린 생성 및 몬스터 리스트 초기화
        List<Monster> monsters = generateGoblins();

        // 생성된 고블린 정보 출력
        printGeneratedGoblins(monsters);

        // 몬스터 공격 스레드 생성 및 시작
        AtomicBoolean battleEnded = new AtomicBoolean(false);
        Thread monsterAttackThread = createMonsterAttackThread(hero, monsters, battleEnded);
        monsterAttackThread.start();

        // 전투 진행
        try {
            while (!monsters.isEmpty() && hero.isAlive()) {
                // 전투 메뉴 출력
                printBattleMenu();

                // 사용자 선택 처리
                int actionChoice = scanner.nextInt();
                switch (actionChoice) {
                    case 1:
                        performBasicAttack(hero, monsters);
                        break;
                    case 2:
                        handleSkillSelection(scanner, hero, monsters);
                        break;
                    default:
                        System.out.println("‣유효한 선택을 해주세요.\n");
                        break;
                }

                // 영웅이 죽었을 경우 전투 중단
                if (!hero.isAlive()) {
                    System.out.println("‣전투에서 패배했습니다. 이동할 장소를 선택해주세요.");
                    showMenuAfterBattle(scanner);
                    break;
                }

                // 모든 몬스터를 쓰러뜨렸을 경우 전투 승리
                if (monsters.isEmpty()) {
                    System.out.println("‣모든 고블린을 물리쳤습니다. 전투에서 승리했습니다!\n");
                    showMenuAfterBattle(scanner);
                    break;
                }
            }

            // 몬스터 공격 스레드 종료 대기
            monsterAttackThread.join();
        } catch (InputMismatchException e) {
            System.out.println("‣숫자를 입력해주세요.\n");
            scanner.next(); // 잘못된 입력을 처리하기 위해 입력 버퍼를 비움
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showMenuAfterBattle(Scanner scanner) {
        System.out.println("‣다음 동작을 선택하세요:");
        System.out.println("‣1. 마을로 돌아가기");
        System.out.println("‣2. 다른 던전으로 이동하기");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // 마을로 돌아가는 동작 수행
                break;
            case 2:
                // 다른 던전으로 이동하는 동작 수행
                break;
            default:
                System.out.println("‣잘못된 선택입니다. 안식처인 마을로 돌아갑니다.");
                // 마을로 돌아가는 동작 수행
                break;
        }
    }

    private static List<Monster> generateGoblins() {
        int numGoblins = new Random().nextInt(3) + 3; // 랜덤으로 3에서 5까지의 고블린 생성
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < numGoblins; i++) {
            int goblinType = new Random().nextInt(2); // 0부터 1까지의 랜덤한 숫자 생성
            Monster goblin;
            switch (goblinType) {
                case 0:
                    goblin = new Goblina();
                    break;
                case 1:
                    goblin = new Hobgoblin();
                    break;
                default:
                    goblin = new Goblina();
                    break;
            }
            monsters.add(goblin); // 생성된 고블린을 몬스터 리스트에 추가
        }
        return monsters;
    }

    private static void printGeneratedGoblins(List<Monster> monsters) {
        System.out.println("‣고블린 던전에 진입했습니다. 생성된 몬스터들:");
        Map<String, Integer> goblinCounts = new HashMap<>();
        for (Monster monster : monsters) {
            String name = monster.getName();
            goblinCounts.put(name, goblinCounts.getOrDefault(name, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : goblinCounts.entrySet()) {
            System.out.println("‣" + entry.getKey() + ": " + entry.getValue() + "마리");
        }
        System.out.println();
    }

    private static Thread createMonsterAttackThread(Hero hero, List<Monster> monsters, AtomicBoolean battleEnded) {
        return new Thread(() -> {
            while (!monsters.isEmpty() && !Thread.currentThread().isInterrupted()) {
                if (hero.getHp() <= 0) { // 영웅의 체력을 확인하여 스레드 종료
                    Thread.currentThread().interrupt(); // 영웅이 패배했을 경우 스레드 종료
                    battleEnded.set(true);
                    return;
                }

                try {
                    Thread.sleep(1500); // 1.5초마다 스레드가 정지
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                // 전투가 계속되는 경우 몬스터가 공격
                for (Monster monster : monsters) {
                    int damage = monster.randomAttack(hero);
                    System.out.println("‣" + monster.getName() + "이(가) 당신을 공격합니다.\n");
                }
            }
        });
    }

    private static void printBattleMenu() {
        System.out.println("========================= 전투 메뉴 =========================");
        System.out.println("\n‣행동을 선택하세요:\n");
        System.out.println("‣1. 기본 공격");
        System.out.println("‣2. 스킬 사용\n");
        System.out.println("===========================================================");
    }

    private static void performBasicAttack(Hero hero, List<Monster> monsters) {
        int totalMonsterHP = monsters.stream().mapToInt(Monster::getHp).sum();
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            int damage = hero.useBasicAttack();
            System.out.println("‣적에게 " + damage + "의 피해를 입혔습니다.\n");
            if (monster.getHp() - damage <= 0) {
                System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                iterator.remove();
                hero.gainExp(monster.dropExp());
                hero.gainMoney(monster.dropMoney());
                break;
            }
        }
    }

    private static void handleSkillSelection(Scanner scanner, Hero hero, List<Monster> monsters) {
        int skillChoice = selectSkill(scanner, hero);
        switch (skillChoice) {
            case 1:
                useSkill(hero, monsters, "패스트 슬래시");
                break;
            case 2:
                useSkill(hero, monsters, "세비지 블로우");
                break;
            case 3:
                useSkill(hero, monsters, "블러드 스트라이크");
                break;
            case 4:
                useSkill(hero, monsters, "파워 스트라이크");
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

    private static void useSkill(Hero hero, List<Monster> monsters, String skillName) {
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

            if (monster.getHp() - damage <= 0) {
                System.out.println("‣" + monster.getName() + "을(를) 처치했습니다!\n");
                monsters.remove(monster);
                hero.gainExp(monster.dropExp());
                hero.gainMoney(monster.dropMoney());
                break;
            }
        }
    }
}
