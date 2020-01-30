package com.warluscampsite.mylittlemaze.playerteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.warluscampsite.mylittlemaze.camp.Camp;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.maze.Coordinates;
import com.warluscampsite.mylittlemaze.maze.Direction;
import com.warluscampsite.mylittlemaze.playerteam.classes.ClassBase;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;

public class PlayerParty {

	Random random = new Random();

	Data data;

	Coordinates coord;

	private Map<Integer, Player> characterMap;

	private Items items;
	private Camp camp;

	public PlayerParty(Data data) {
		this.data = data;

		items = new Items(this);
		camp = new Camp();
		coord = new Coordinates();

		characterMap = new HashMap<Integer, Player>();
		for (int n = 1; n <= 6; n++)
			characterMap.put(n, null);
	}

	public void newPlayer(int place, String string, ClassBase proffesion) {
		characterMap.put(place, new Player(this, string, proffesion));
		characterMap.get(place).setPlace(place);

		data.getGUI().addNewCharacterElements(characterMap.get(place));

		for (int n = 0; n < 10; n++)
			data.getMaze().getLoot().checkLootAfterBattle();
	}

	public boolean checkIfAllDeath() {
		for (Entry<Integer, Player> character : characterMap.entrySet()) {
			if (character.getValue() != null) {
				System.out.println(character.toString());
				if (!character.getValue().isDead())
					return false;
			}
		}
		return true;
	}

	public void moveParty() {
		int currentX = coord.getX();
		int currentY = coord.getY();

		Direction direction = Direction.randomDirection();

		boolean shouldHunt = true, shouldGenerateNewMaze = true;

		if (data.getMaze().getMazeGrid().canWalk(currentX, currentY, direction)
				&& data.getMaze().getMazeGrid().thereIsNoWall(currentX, currentY, direction)) {
			shouldHunt = false;
			coord.setX(currentX += direction.getDx());
			coord.setY(currentY += direction.getDy());

		} else if (data.getMaze().getMazeGrid().canWalk(currentX, currentY, direction.rotate90())
				&& data.getMaze().getMazeGrid().thereIsNoWall(currentX, currentY, direction.rotate90())) {
			shouldHunt = false;
			coord.setX(currentX += direction.rotate90().getDx());
			coord.setY(currentY += direction.rotate90().getDy());

		} else if (data.getMaze().getMazeGrid().canWalk(currentX, currentY, direction.rotate180())
				&& data.getMaze().getMazeGrid().thereIsNoWall(currentX, currentY, direction.rotate180())) {
			shouldHunt = false;
			coord.setX(currentX += direction.rotate180().getDx());
			coord.setY(currentY += direction.rotate180().getDy());

		} else if (data.getMaze().getMazeGrid().canWalk(currentX, currentY, direction.rotate270())
				&& data.getMaze().getMazeGrid().thereIsNoWall(currentX, currentY, direction.rotate270())) {
			shouldHunt = false;
			coord.setX(currentX += direction.rotate270().getDx());
			coord.setY(currentY += direction.rotate270().getDy());
		}

		// If cannot move should find new place
		if (shouldHunt) {
			for (int i = 1; i <= data.getMaze().getMazeGrid().getMazeMaxX(); i++) {
				for (int j = 1; j <= data.getMaze().getMazeGrid().getMazeMaxY(); j++) {
					if (data.getMaze().getMazeGrid().getMazeCells().get(i).get(j).isWasVisited())
						if (data.getMaze().getMazeGrid().hasUnvisitedNeighbour(i, j)
								&& data.getMaze().getMazeGrid().partyCanMoveSomeWhere(i, j)) {
							coord.setX(i);
							coord.setY(j);
							shouldGenerateNewMaze = false;
							data.getMaze().getMazeGrid().getMazeCells().get(coord.getX()).get(coord.getY())
									.setWasVisited(true);
							data.getMaze().getMazeGrid().setVisibleAround(coord.getX(), coord.getY());
							moveParty();
							return;
						}
				}
			}

			// If no good place to move should generate new maze
			if (shouldGenerateNewMaze) {
				if (data.getGUI().getMazePanel().getChckbxAutoDescend().isSelected())
					data.getGUI().getMazePanel().getjDescendButton().doClick();
				return;
			}

		}
		data.getMaze().getMazeGrid().getMazeCells().get(coord.getX()).get(coord.getY()).setWasVisited(true);
		data.getMaze().getMazeGrid().setVisibleAround(coord.getX(), coord.getY());
		data.getGUI().getMazePanel().repaint();
	}

	public int getRandomFreePlace() {
		for (int i = 1; i <= 6; i++) {
			if (!characterMap.containsKey(i) || characterMap.get(i) == null)
				return i;
		}
		return 6;
	}

	public void replecePlayers(int replaceFrom, int replaceTo) {
		Player playerFrom = getCharacterMap().get(replaceFrom);
		getCharacterMap().replace(replaceFrom, null);

		Player playerTo = null;
		if (getCharacterMap().containsKey(replaceTo)) {
			playerTo = getCharacterMap().get(replaceTo);
			getCharacterMap().replace(replaceTo, null);
		}

		getCharacterMap().put(replaceTo, playerFrom);
		getCharacterMap().get(replaceTo).setPlace(replaceTo);

		if (playerTo != null) {
			getCharacterMap().put(replaceFrom, playerTo);
			getCharacterMap().get(replaceFrom).setPlace(replaceFrom);
		}
		
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Map<Integer, Player> getCharacterMap() {
		return characterMap;
	}

	public Player getPlayerByName(String name) {
		for (Entry<Integer, Player> player : characterMap.entrySet()) {
			if(player.getValue() != null)
				if (player.getValue().getName() == name)
					return player.getValue();
		}
		return null;
	}

	public int getHighestLuck() {
		int highestLuck = 0;
		for (Entry<Integer, Player> player : getCharacterMap().entrySet()) {
			if (player.getValue() != null) {
				if (player.getValue().getAttributes().getAttribute(AttributesEnum.LUCK).getSum() > highestLuck)
					highestLuck = player.getValue().getAttributes().getAttribute(AttributesEnum.LUCK).getSum();
			}
		}

		return highestLuck;
	}

	public Items getItems() {
		return items;
	}

	public Coordinates getCoord() {
		return coord;
	}

	public Player getRandomAlive() {
		ArrayList<Integer> listOfAlive = new ArrayList<>();

		for (Entry<Integer, Player> player : characterMap.entrySet()) {
			if (!player.getValue().isDead())
				listOfAlive.add(player.getKey());
		}

		return characterMap.get(listOfAlive.get(random.nextInt(listOfAlive.size())));
	}

	public Data getData() {
		return data;
	}

	public Camp getCamp() {
		return camp;
	}

}
