package com.warluscampsite.mylittlemaze.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.status.Status;

public class CharacterStatuses {

	Characterr character;

	boolean bleedImmune = false;

	boolean statusChanged;

	protected Map<String, Status> statusesMap;

	public CharacterStatuses(Player player) {
		this();

		statusChanged = false;

		this.character = (Player) player;
	}

	public CharacterStatuses(Monster monster) {
		this();

		this.character = (Monster) monster;
	}

	public CharacterStatuses() {
		statusesMap = new HashMap<>();
	}

	public void addTimeFlow(int millis) {
		List<String> toRemove = new ArrayList<>();
		for (Entry<String, Status> status : statusesMap.entrySet()) {
			status.getValue().addTimeFlow(millis);

			if (status.getValue().checkIfShouldEnd())
				toRemove.add(status.getKey());
		}
		for (String statusName : toRemove) {
			removeStatus(statusName);
		}
	}

	public void addNewStatus(Status status) {
		String statusName = status.getName();

		if (checkIfImmune(statusName))
			return;

		if (statusesMap.containsKey(statusName))
			statusesMap.get(statusName).renew(status);
		else {
			statusesMap.put(statusName, status);
			character.refreshCharacter();
		}
		statusChanged = true;
	}

	private boolean checkIfImmune(String statusName) {
		if (statusName == "Bleed" && isBleedImmune())
			return true;
		else
			return false;
	}

	public void removeStatus(String statusName) {
		statusesMap.get(statusName).remove();
		statusesMap.remove(statusName);
		statusChanged = true;
		character.refreshCharacter();
	}

	public void removeAllStatusesAfterDeath() {
		statusesMap.clear();
		statusChanged = true;
	}

	public Status getStatus(String statusName) {
		return statusesMap.get(statusName);
	}

	public boolean checkIfStatusExist(String statusName) {
		if (statusesMap.containsKey(statusName))
			return true;
		return false;
	}

	public void resetOptionsBeforeRefresh() {
		bleedImmune = false;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public boolean isStatusChanged() {
		return statusChanged;
	}

	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}

	public Map<String, Status> getStatusesMap() {
		return statusesMap;
	}

	public boolean isBleedImmune() {
		return bleedImmune;
	}

	public void setBleedImmune(boolean bleedImmune) {
		this.bleedImmune = bleedImmune;
	}

}
