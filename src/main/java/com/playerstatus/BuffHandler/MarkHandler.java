package com.playerstatus.BuffHandler;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.Skill;

public class MarkHandler extends BuffHandler {
    private final Client client;
    private static final int PURGING_STAFF_MULTIPLIER = 5;
    private static final int PURGING_STAFF_ID = 29594;
    public static final String MARK_BEGIN_MESSAGE = "You have placed a Mark of Darkness upon yourself.";

    public MarkHandler(Client client) {
        this.client = client;
    }

    public void activate() {
        // duration is player's (base) magic level, in ticks
        ticksRemaining = client.getRealSkillLevel(Skill.MAGIC);
        // multiplied by 5 if the player has a purging staff equipped
        ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
        if (equipment != null) {
            for (Item item : equipment.getItems()) {
                if (item != null) {
                    if (item.getId() == PURGING_STAFF_ID) {
                        ticksRemaining *= PURGING_STAFF_MULTIPLIER;
                    }
                }
            }
        }
        isActive = true;
    }

}
