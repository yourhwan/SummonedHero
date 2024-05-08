package dungeon;

import userjob.Hero;

import java.util.*;
public class OgreDungeonBattleThread extends Thread{
    private final Hero hero;
    private final List<Monster> monsters;
    private final Map<String, Integer> ogreCounts;
    private volatile boolean battleOver = false; // 전투 종료여부 확인용

    public OgreDungeonBattleThread(Hero hero, List<Monster> monsters, Map<String, Integer> ogreCounts) {
        this.hero = hero;
        this.monsters = monsters;
        this.ogreCounts = ogreCounts;
    }

    @Override
    public void run() {
        try {
            while (!monsters.isEmpty() && hero.isAlive() && !OgreDungeon.isBattleOver()) {
                Thread.sleep(4000); // 몬스터 공격 사이에 3초 대기
                for (Monster monster : monsters) {
                    monster.randomAttack(hero);
                    if (hero.getHp() <= 0) {
                        battleOver = true; // 전투 종료 상태로 변경
                        break;
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

