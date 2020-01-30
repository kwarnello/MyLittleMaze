package com.warluscampsite.mylittlemaze.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.TextAreaController;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.status.Vulnerability;
import com.warluscampsite.mylittlemaze.status.Weakness;

public class BattleAttackController {

	Data data;

	BattleNumbersController battleNumbers;

	Random random = new Random();

	ArrayList<Characterr> listThatCanAttack, listToAttack;
	Map<DamageTypes, Double> damageMap;

	boolean isAttackerPlayer, isItBuff;

	public BattleAttackController(Data data) {
		this.data = data;

		listThatCanAttack = new ArrayList<>();
		listToAttack = new ArrayList<>();

		battleNumbers = new BattleNumbersController();

		damageMap = new HashMap<>();
		for (DamageTypes damageTypes : DamageTypes.values())
			damageMap.put(damageTypes, 0.0);

	}

	public void thisAttack(Skill skill) {
		// do list with enemies that CAN be attack
		checkWhichEnemiesCanAttack(skill);

		if (listThatCanAttack.size() == 0)
			return;

		skill.attack();

		// do list with enemies that ACTUALLY will be attack
		listToAttack = checkWhichEnemiesShouldAttack(skill);

		doAttackStuff(skill);
	}

	private void checkWhichEnemiesCanAttack(Skill skill) {
		listThatCanAttack.clear();

		isAttackerPlayer = skill.getCharacter().getClass().equals(Player.class);
		isItBuff = skill.getSkillType().equals(SkillType.BUFF) || skill.getSkillType().equals(SkillType.HEAL);

		if (isAttackerPlayer) {
			if (isItBuff)
				for (Player player : data.getPlayerParty().getCharacterMap().values()) {
					if (player != null)
						if (!player.isDead())
							listThatCanAttack.add(player);
				}
			else
				for (Monster monster : data.getMaze().getMonstersParty().getPlaceMap().values()) {
					if (!monster.isDead())
						listThatCanAttack.add(monster);
				}
		} else {
			if (isItBuff)
				for (Monster monster : data.getMaze().getMonstersParty().getPlaceMap().values()) {
					if (!monster.isDead())
						listThatCanAttack.add(monster);
				}
			else
				for (Player player : data.getPlayerParty().getCharacterMap().values()) {
					if (player != null) {
						if (!player.isDead())
							listThatCanAttack.add(player);
					}
				}
		}

	}

	private ArrayList<Characterr> checkWhichEnemiesShouldAttack(Skill skill) {
		ArrayList<Characterr> dummyList = new ArrayList<>();

		switch (skill.getSkillRange()) {
		case ALL:
			dummyList = listThatCanAttack;
			break;

		case ALL_ONE_ROW:
			ArrayList<Characterr> dummyListForSecondRow = new ArrayList<>();
			for (Characterr character : listThatCanAttack) {
				if (character.getPlace() <= 3)
					dummyList.add(character);
				else
					dummyListForSecondRow.add(character);
			}
			if (dummyList.size() == 0)
				dummyList = dummyListForSecondRow;
			break;

		case ALL_TWO_ROW:
			int place = skill.getCharacter().getPlace();
			int placeDefender = 0;

			if (place <= 3) {
				placeDefender = listThatCanAttack.get(random.nextInt(listThatCanAttack.size())).getPlace();
			} else {
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() <= 3) {
						placeDefender = character.getPlace();
						break;
					}
				}
				if (placeDefender == 0)
					placeDefender = 4;
			}

			for (Characterr character : listThatCanAttack) {
				if (placeDefender >= 4 && character.getPlace() >= 4)
					dummyList.add(character);
				else
					dummyList.add(character);
			}
			break;

		case CROSS:
			int placeDefenderCross = listThatCanAttack.get(random.nextInt(listThatCanAttack.size())).getPlace();

			switch (placeDefenderCross) {
			case 1:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 1 || character.getPlace() == 2 || character.getPlace() == 4)
						dummyList.add(character);
				}
				break;
			case 2:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 2 || character.getPlace() == 3 || character.getPlace() == 1
							|| character.getPlace() == 5)
						dummyList.add(character);
				}
				break;
			case 3:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 3 || character.getPlace() == 2 || character.getPlace() == 6)
						dummyList.add(character);
				}
				break;
			case 4:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 4 || character.getPlace() == 1 || character.getPlace() == 5)
						dummyList.add(character);
				}
				break;
			case 5:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 5 || character.getPlace() == 4 || character.getPlace() == 6
							|| character.getPlace() == 2)
						dummyList.add(character);
				}
				break;
			case 6:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 6 || character.getPlace() == 5 || character.getPlace() == 3)
						dummyList.add(character);
				}
				break;
			}

			break;

		case LINE:
			int placeDefenderLine = listThatCanAttack.get(random.nextInt(listThatCanAttack.size())).getPlace();

			switch (placeDefenderLine) {
			case 1:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 1 || character.getPlace() == 4)
						dummyList.add(character);
				}
				break;
			case 2:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 2 || character.getPlace() == 5)
						dummyList.add(character);
				}
				break;
			case 3:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 3 || character.getPlace() == 6)
						dummyList.add(character);
				}
				break;
			case 4:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 4 || character.getPlace() == 1)
						dummyList.add(character);
				}
				break;
			case 5:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 5 || character.getPlace() == 2)
						dummyList.add(character);
				}
				break;
			case 6:
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() == 6 || character.getPlace() == 1)
						dummyList.add(character);
				}
				break;
			}
			break;

		case SINGLE_ANY:
			dummyList.add(listThatCanAttack.get(random.nextInt(listThatCanAttack.size())));
			break;

		case SINGLE_ONE_ROW:
			ArrayList<Characterr> first = new ArrayList<>();
			ArrayList<Characterr> two = new ArrayList<>();

			for (Characterr character : listThatCanAttack) {
				if (character.getPlace() <= 3)
					first.add(character);
				else
					two.add(character);
			}
			if (first.size() == 0)
				dummyList.add(two.get(random.nextInt(two.size())));
			else
				dummyList.add(first.get(random.nextInt(first.size())));
			break;

		case SINGLE_TWO_ROW:
			if (skill.getCharacter().getPlace() <= 3)
				dummyList.add(listThatCanAttack.get(random.nextInt(listThatCanAttack.size())));
			else {
				ArrayList<Characterr> justHere = new ArrayList<>();
				ArrayList<Characterr> justThere = new ArrayList<>();
				for (Characterr character : listThatCanAttack) {
					if (character.getPlace() <= 3)
						justHere.add(character);
					else
						justThere.add(character);
				}
				if (justHere.size() != 0)
					dummyList.add(justHere.get(random.nextInt(justHere.size())));
				else
					dummyList.add(justThere.get(random.nextInt(justThere.size())));
			}
			break;
		case SELF:
			dummyList.add(skill.getCharacter());
			break;
		default:
			break;
		}

		return dummyList;

	}

	private void doAttackStuff(Skill skill) {
		for (int n = 1; n <= skill.getNumberOfAttacks(); n++) {
			for (Characterr defender : listToAttack) {
				boolean isItHit = checkIfHit(skill, defender);

				if (isItHit) {
					///////// check critical
					boolean criticalStrike = FightController.trueOrFalse(skill.getCritChance());
					double criticalMultiplier = 1.0;
					if (criticalStrike)
						criticalMultiplier = skill.getCritMultiplier();

					/////////////// do skill stuff
					skill.getCharacter().doAttack(skill.getCharacter(), defender, skill, damageMap, criticalStrike);

					/////////////// calc all damage
					double damageOutput = 0.0;
					for (Entry<DamageTypes, Double> damage : damageMap.entrySet()) {
						double damageIn = damage.getValue();

						////////// check if character has weakness or
						////////// vulnerability
						if (damage.getKey().equals(DamageTypes.PHYSICAL)) {
							if (skill.getCharacter().getStatus().checkIfStatusExist("Weakness")) {
								damageIn = damageIn
										* (1 - ((Weakness) skill.getCharacter().getStatus().getStatus("Weakness"))
												.getPhysicalDamageReduction());
							}
							if (skill.getCharacter().getStatus().checkIfStatusExist("Vulnerability")) {
								damageIn = damageIn * (1
										+ ((Vulnerability) skill.getCharacter().getStatus().getStatus("Vulnerability"))
												.getPhysicalDamageIncrease());
							}
							//// add leech for physical damage
							if (damageMap.get(DamageTypes.LEECH) != 0.0) {
								double leechDamage = damageIn * criticalMultiplier
										* (1 - defender.getResist(damage.getKey()).getReduction())
										* damageMap.get(DamageTypes.LEECH);
								skill.getCharacter().getHealth().changeCurrentValue(leechDamage);
							}
						}

						damageOutput += (damageIn * criticalMultiplier
								* (1 - defender.getResist(damage.getKey()).getReduction()));
					}

					///////// do defender health stuffffffff
					defender.getHealth().changeCurrentValue(-damageOutput);

					//////// do numbers!!!!!!!!!!!
					battleNumbers.addNumber(defender, damageOutput, criticalStrike, isItBuff, isAttackerPlayer);

					TextAreaController.addInformationAboutAttack(skill.getCharacter().getName(), defender.getName(),
							skill, damageOutput, criticalStrike);

					resetDamageMap();

				}
			}
		}
	}

	private boolean checkIfHit(Skill skill, Characterr defender) {
		if (skill.getSkillType().equals(SkillType.BUFF) || skill.getSkillType().equals(SkillType.HEAL))
			return true;

		//////////////// check hit chance
		if (!FightController.trueOrFalse(FightController.calculateHitChance(skill.getCharacter(), defender))) {
			TextAreaController.addInformationIfMissed(skill.getCharacter().getName(), defender.getName(), skill);
			return false;
		}

		//////////////// check dodge
		if (FightController
				.trueOrFalse(defender.getStatistics().getDodge().getTotal() / Math.sqrt(data.getMaze().getLevel()))) {
			TextAreaController.addInformationIfDodge(skill.getName(), defender.getName(), skill);
			return false;
		}
		//////////////// check if block
		if (defender.getBlock().isCanBlock())
			if (skill.getSkillType().equals(SkillType.MELEE) || skill.getSkillType().equals(SkillType.RANGED)
					|| defender.getBlock().isCanBlockSpells())
				if (FightController.trueOrFalse(defender.getBlock().getBlockChance()))
					return false;

		return true;
	}

	private void resetDamageMap() {
		damageMap.replaceAll((k, v) -> 0.0);
	}

	public void addTicksForNumbers(int millis) {
		battleNumbers.addTick(millis);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public BattleNumbersController getBattleNumbers() {
		return battleNumbers;
	}

}
