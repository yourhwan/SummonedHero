package dungeon;

import rpggame.Story;
import userjob.Hero;

import java.util.*;

public class DevilDungeonBattleThread extends Thread {
    private final Hero hero;
    private final List<Monster> monsters;
    private final Map<String, Integer> devilCounts;

    private final Scanner scanner;

    public DevilDungeonBattleThread(Hero hero, List<Monster> monsters, Map<String, Integer> devilCounts, Scanner scanner) {
        this.hero = hero;
        this.monsters = monsters;
        this.devilCounts = devilCounts;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            while (!monsters.isEmpty() && hero.isAlive()) {
                Thread.sleep(3000); // 몬스터 공격 사이에 2초 대기
                for (Monster monster : monsters) {
                    int damage = monster.randomAttack(hero);
                    System.out.println("‣" + monster.getName() + "이(가) 당신에게 " + damage + "의 피해를 입혔습니다.\n");
                    if (hero.getHp() <= 0) {
                        System.out.println("========================= 전투 결과 =========================");
                        System.out.println("‣전투에서 패배했습니다.\n");
                        System.out.println("‣내 정보");
                        System.out.println(hero); // 사용자 정보 출력
                        System.out.println("==========================================================");
                        System.out.println("‣마을로 돌아갑니다.");
                        Story.village(hero, scanner);
                        hero.revert(); // 영웅의 상태 초기화
                        return;
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("전투가 중단 되었습니다.");
            Thread.currentThread().interrupt(); // 중단된 상태 복원
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace(); // 디버깅을 위한 에러메시지 출력
        } finally {
            if (scanner != null) {
                scanner.close(); // 스캐너 닫기
            }
        }
    }

}
