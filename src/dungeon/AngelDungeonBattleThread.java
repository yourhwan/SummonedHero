package dungeon;

import userjob.Hero;

import java.util.*;

public class AngelDungeonBattleThread extends Thread {
    private final Hero hero;
    private final List<Monster> monsters;
    private final Map<String, Integer> angelCounts;
    private volatile boolean battleOver = false; // 전투 종료여부 확인용

    public AngelDungeonBattleThread(Hero hero, List<Monster> monsters, Map<String, Integer> angelCounts) {
        this.hero = hero;
        this.monsters = monsters;
        this.angelCounts = angelCounts;
    }

    @Override
    public void run() {
        try {
            while (!monsters.isEmpty() && hero.isAlive() && !AngelDungeon.isBattleOver()) {
                Thread.sleep(3000); // 몬스터 공격 사이에 3초 대기
                for (Monster monster : monsters) {
                    int damage = monster.randomAttack(hero);
                    System.out.println("‣" + monster.getName() + "이(가) 당신에게 " + damage + "의 피해를 입혔습니다.\n");
                    if (hero.getHp() <= 0) {

                        AngelDungeon.setBattleOver(true);// 전투 종료
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
        }
    }

    public boolean isBattleOver() {
        return battleOver;
    }

}
