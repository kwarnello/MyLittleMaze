package com.warluscampsite.mylittlemaze.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ArmorBase;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ArmorTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public class Equipment {

	Player player;
	Map<EquipmentSlots, Item> equipment;

	boolean maxIsMed, maxIsLight, maxIsCloth, onlyMed, onlyLight, onlyHeavy, onlyCloth;
	int numberOfCloth, numberOfLight, numberOfMed, numberOfHeavy;

	public Equipment(Player player) {
		this.player = player;

		initializeEquipmentMap();
	}

	private void initializeEquipmentMap() {
		equipment = new HashMap<>();

		for (EquipmentSlots itemType : EquipmentSlots.values()) {
			equipment.put(itemType, null);
		}
	}

	public void refresh() {
		player.getBlock().setCanBlock(false);

		for (Item item : equipment.values()) {
			if (item != null)
				item.doDuringEquip(player);
		}
	}

	public void equipItem(Item item) {
		EquipmentSlots slot = null;

		refreshArmorTypes();

		switch (item.getItemBase().getItemType()) {
		case AMULET:
			if (equipment.get(EquipmentSlots.AMULET) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.AMULET));
			equipment.replace(EquipmentSlots.AMULET, item);
			slot = EquipmentSlots.AMULET;
			break;

		case BELT:
			if (equipment.get(EquipmentSlots.BELT) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.BELT));
			equipment.replace(EquipmentSlots.BELT, item);
			slot = EquipmentSlots.BELT;
			break;

		case BODY_ARMOR:
			if (equipment.get(EquipmentSlots.BODY_ARMOR) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.BODY_ARMOR));
			equipment.replace(EquipmentSlots.BODY_ARMOR, item);
			slot = EquipmentSlots.BODY_ARMOR;
			break;

		case BOOTS:
			if (equipment.get(EquipmentSlots.BOOTS) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.BOOTS));
			equipment.replace(EquipmentSlots.BOOTS, item);
			slot = EquipmentSlots.BOOTS;
			break;

		case GLOVES:
			if (equipment.get(EquipmentSlots.GLOVES) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.GLOVES));
			equipment.replace(EquipmentSlots.GLOVES, item);
			slot = EquipmentSlots.GLOVES;
			break;

		case HELMET:
			if (equipment.get(EquipmentSlots.HELMET) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.HELMET));
			equipment.replace(EquipmentSlots.HELMET, item);
			slot = EquipmentSlots.HELMET;
			break;

		case LEGS:
			if (equipment.get(EquipmentSlots.LEGS) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.LEGS));
			equipment.replace(EquipmentSlots.LEGS, item);
			slot = EquipmentSlots.LEGS;
			break;

		case ONE_HANDED_MAGIC:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		case ONE_HANDED_MELEE:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		case ONE_HANDED_RANGED:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		case RING:
			if (equipment.get(EquipmentSlots.RING) == null)
				equipment.replace(EquipmentSlots.RING, item);
			else if (equipment.get(EquipmentSlots.RING_SECOND) == null)
				equipment.replace(EquipmentSlots.RING_SECOND, item);
			else {
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.RING));
				equipment.replace(EquipmentSlots.RING, equipment.get(EquipmentSlots.RING_SECOND));
				equipment.replace(EquipmentSlots.RING_SECOND, item);
			}
			slot = EquipmentSlots.RING;
			break;

		case SHIELD:
			if (equipment.get(EquipmentSlots.SECOND_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.SECOND_HAND));
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null) {
				ItemTypes firstHand = equipment.get(EquipmentSlots.FIRST_HAND).getItemBase().getItemType();
				if (firstHand.equals(ItemTypes.TWO_HANDED_MAGIC) || firstHand.equals(ItemTypes.TWO_HANDED_RANGED)
						|| firstHand.equals(ItemTypes.TWO_HANDED_MELEE)) {
					player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
					equipment.put(EquipmentSlots.FIRST_HAND, null);
				}
			}
			equipment.replace(EquipmentSlots.SECOND_HAND, item);
			slot = EquipmentSlots.SECOND_HAND;
			break;

		case SHOULDERS:
			if (equipment.get(EquipmentSlots.SHOULDERS) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.SHOULDERS));
			equipment.replace(EquipmentSlots.SHOULDERS, item);
			slot = EquipmentSlots.SHOULDERS;
			break;

		case TWO_HANDED_MAGIC:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			if (equipment.get(EquipmentSlots.SECOND_HAND) != null) {
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.SECOND_HAND));
				equipment.put(EquipmentSlots.SECOND_HAND, null);
			}
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		case TWO_HANDED_MELEE:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			if (equipment.get(EquipmentSlots.SECOND_HAND) != null) {
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.SECOND_HAND));
				equipment.put(EquipmentSlots.SECOND_HAND, null);
			}
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		case TWO_HANDED_RANGED:
			if (equipment.get(EquipmentSlots.FIRST_HAND) != null)
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.FIRST_HAND));
			if (equipment.get(EquipmentSlots.SECOND_HAND) != null) {
				player.getPlayerParty().getItems().addItemAfterBattle(equipment.get(EquipmentSlots.SECOND_HAND));
				equipment.put(EquipmentSlots.SECOND_HAND, null);
			}
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			slot = EquipmentSlots.FIRST_HAND;
			break;

		default:
			break;
		}

		checkEquipedArmorTypes();

		player.getPlayerParty().getData().getGUI().getCharacterPanel(player).getjEquipmentPanel().addEquipedIcon(slot);
		player.refreshCharacter();
		player.getPlayerParty().getItems().getItemHandler().remove(item);
	}

	public void equipItemAfterCrafting(Item item, Item oldItem) {
		EquipmentSlots slot = null;

		for (Entry<EquipmentSlots, Item> eqElement : equipment.entrySet()) {
			if (eqElement.getValue() == oldItem) {
				slot = eqElement.getKey();
				break;
			}
		}

		refreshArmorTypes();

		switch (slot) {
		case AMULET:
			equipment.replace(EquipmentSlots.AMULET, item);
			break;

		case BELT:
			equipment.replace(EquipmentSlots.BELT, item);
			break;

		case BODY_ARMOR:
			equipment.replace(EquipmentSlots.BODY_ARMOR, item);
			break;

		case BOOTS:
			equipment.replace(EquipmentSlots.BOOTS, item);
			break;

		case GLOVES:
			equipment.replace(EquipmentSlots.GLOVES, item);
			break;

		case HELMET:
			equipment.replace(EquipmentSlots.HELMET, item);
			break;

		case LEGS:
			equipment.replace(EquipmentSlots.LEGS, item);
			break;

		case FIRST_HAND:
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			break;

		case RING:
			equipment.replace(EquipmentSlots.RING, item);
			break;

		case RING_SECOND:
			equipment.replace(EquipmentSlots.RING_SECOND, item);

		case SECOND_HAND:
			equipment.replace(EquipmentSlots.SECOND_HAND, item);
			break;

		case SHOULDERS:
			equipment.replace(EquipmentSlots.SHOULDERS, item);
			break;

		default:
			break;
		}

		checkEquipedArmorTypes();

		player.getPlayerParty().getData().getGUI().getCharacterPanel(player).getjEquipmentPanel().addEquipedIcon(slot);
		player.refreshCharacter();
	}

	public void destroyEquipedItem(Item item) {
		EquipmentSlots slot = null;

		for (Entry<EquipmentSlots, Item> eqElement : equipment.entrySet()) {
			if (eqElement.getValue() == item) {
				slot = eqElement.getKey();
				break;
			}
		}

		refreshArmorTypes();

		item = null;

		switch (slot) {
		case AMULET:
			equipment.replace(EquipmentSlots.AMULET, item);
			break;

		case BELT:
			equipment.replace(EquipmentSlots.BELT, item);
			break;

		case BODY_ARMOR:
			equipment.replace(EquipmentSlots.BODY_ARMOR, item);
			break;

		case BOOTS:
			equipment.replace(EquipmentSlots.BOOTS, item);
			break;

		case GLOVES:
			equipment.replace(EquipmentSlots.GLOVES, item);
			break;

		case HELMET:
			equipment.replace(EquipmentSlots.HELMET, item);
			break;

		case LEGS:
			equipment.replace(EquipmentSlots.LEGS, item);
			break;

		case FIRST_HAND:
			equipment.replace(EquipmentSlots.FIRST_HAND, item);
			break;

		case RING:
			equipment.replace(EquipmentSlots.RING, item);
			break;

		case RING_SECOND:
			equipment.replace(EquipmentSlots.RING_SECOND, item);

		case SECOND_HAND:
			equipment.replace(EquipmentSlots.SECOND_HAND, item);
			break;

		case SHOULDERS:
			equipment.replace(EquipmentSlots.SHOULDERS, item);
			break;

		default:
			break;
		}

		checkEquipedArmorTypes();

		player.getPlayerParty().getData().getGUI().getCharacterPanel(player).getjEquipmentPanel().addEquipedIcon(slot);
		player.refreshCharacter();
	}

	private void refreshArmorTypes() {
		maxIsMed = false;
		maxIsLight = false;
		maxIsCloth = false;
	}

	void checkEquipedArmorTypes() {
		refreshBooleansForArmorTypes();
		List<Boolean> cloth = new ArrayList<>(), light = new ArrayList<>(), med = new ArrayList<>(),
				heavy = new ArrayList<>();

		for (Item item : equipment.values())
			if (item != null)
				if (item.getItemBase().getItemType().getTypeOfItem() == 0) {
					ArmorTypes type = ((ArmorBase) item.getItemBase()).getArmorTypes();
					switch (type) {
					case CLOTH:
						cloth.add(true);
						break;
					case HEAVY:
						heavy.add(true);
						break;
					case LIGHT:
						light.add(true);
						break;
					case MEDIUM:
						med.add(true);
						break;
					}
				}

		numberOfCloth = cloth.size();
		numberOfLight = light.size();
		numberOfMed = med.size();
		numberOfHeavy = heavy.size();

		if (heavy.size() == 0)
			maxIsMed = true;
		else if (heavy.size() == 7) {
			onlyHeavy = true;
			return;
		}

		if (med.size() == 0)
			maxIsLight = true;
		else if (med.size() == 7) {
			onlyMed = true;
			return;
		}
		if (light.size() == 0)
			maxIsCloth = true;
		else if (light.size() == 7) {
			onlyLight = true;
			return;
		}

		if (cloth.size() == 7) {
			onlyCloth = true;
			return;
		}
	}

	private void refreshBooleansForArmorTypes() {
		maxIsMed = false;
		maxIsLight = false;
		maxIsCloth = false;
		onlyMed = false;
		onlyLight = false;
		onlyHeavy = false;
		onlyCloth = false;
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Map<EquipmentSlots, Item> getEquipmentMap() {
		return equipment;
	}

	public Item getEquipmentElement(EquipmentSlots slot) {
		return equipment.get(slot);
	}

	public boolean isMaxIsMed() {
		return maxIsMed;
	}

	public boolean isMaxIsLight() {
		return maxIsLight;
	}

	public boolean isMaxIsCloth() {
		return maxIsCloth;
	}

	public boolean isOnlyMed() {
		return onlyMed;
	}

	public boolean isOnlyLight() {
		return onlyLight;
	}

	public boolean isOnlyHeavy() {
		return onlyHeavy;
	}

	public boolean isOnlyCloth() {
		return onlyCloth;
	}

	public int getNumberOfCloth() {
		return numberOfCloth;
	}

	public int getNumberOfLight() {
		return numberOfLight;
	}

	public int getNumberOfMed() {
		return numberOfMed;
	}

	public int getNumberOfHeavy() {
		return numberOfHeavy;
	}

}
