package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int counter = 0;
    public static String bossDefenceType = " ";
    public static int[] heroesHealth = {270, 260, 250, 280};
    public static int[] heroesDamages = {20, 25, 15};
    public static String[] heroesAttackType = {" Physical ", " Magical ", " Kinetic ", "Medic"};


    public static int roundCounter = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void medicHealing() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && counter < 1 && heroesHealth[i] != heroesHealth[3] && heroesHealth[3] > 0) {
                if (heroesHealth[i] >= 1 && heroesHealth[i] < 100) {
                    Random r = new Random();
                    int randomHealing = r.nextInt(51) + 50;
                    heroesHealth[i] = heroesHealth[i] + randomHealing;
                    counter++;
                }
            }


        }
    }


    public static void bossAngryState() {
        if (bossHealth <= 200) {
            Random r = new Random();
            int healthRand = r.nextInt(31) + 28;
            int damageRand = r.nextInt(11) + 10;
            bossHealth = bossDamage + healthRand;
            bossDamage = bossDamage + damageRand;
            System.out.println(" Boss became angry :" + "increased health by " + healthRand
                    + " and damage " + damageRand);
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        roundCounter++;
        changeBossDefence();
        bossAngryState();
        bossHits();
        heroesHit();
        printStatistics();
        medicHealing();


    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesDamages[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2;
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i] + "Critical damage " + heroesDamages[i] * coeff);
                } else {
                }
                if (bossHealth - heroesDamages[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamages[i];
                }

            }

        }
    }


    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }

            }

        }
    }


    public static void printStatistics() {
        System.out.println("ROUND ---- " + roundCounter);
        System.out.println("______________");
        System.out.println("boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health : " + heroesHealth[i]);

        }
        System.out.println("-----------------------");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("hero won !!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println("Boss won !!!!");
        }
        return allHeroesDead;
    }
}