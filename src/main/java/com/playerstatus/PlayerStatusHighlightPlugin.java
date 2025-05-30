package com.playerstatus;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.text.Highlighter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.VarPlayer;
import net.runelite.api.Varbits;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "Player Status Highlight",
	description = "A plugin that displays the player's immunity or protection status by highlighting the player with an appropriate color",
	tags = "status,immunity,poison,dragonfire,venom,highlight"
)
public class PlayerStatusHighlightPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private PlayerStatusHighlightConfig config;
	@Inject
	private PlayerStatusHighlightOverlay playerStatusHighlightOverlay;
	@Inject
	private OverlayManager overlayManager;

	private MarkHandler markHandler;

	private boolean displayPoison;
	private boolean displayVenom;
	private boolean displayDragonfire;
	private boolean displaySuperDragonfire;
	private boolean displayMark;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Player Status Highlight started!");
		overlayManager.add(playerStatusHighlightOverlay);
		markHandler = new MarkHandler(client);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Player Status Highlight stopped!");
		overlayManager.remove(playerStatusHighlightOverlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			displayVenom = false;
			displayPoison = false;
			displayDragonfire = false;
			displaySuperDragonfire = false;
			displayMark = false;
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event) {
		if (event.getVarpId() == VarPlayer.POISON) {
			final int poisonVarp = event.getValue();
			if (poisonVarp < -38) {
				if (config.showVenom()) {
					displayVenom = true;
					displayPoison = false;
				} else if (config.showPoison()){
					// since venom immunity also counts as poison immunity
					displayPoison = true;
				}
			} else if (poisonVarp < 0) {
				displayVenom = false;
				if (config.showPoison()) {
					displayPoison = true;
				}
			} else {
				//player is neither poison nor venom immune
				displayPoison = false;
				displayVenom = false;
			}
		}
		if (event.getVarbitId() == Varbits.ANTIFIRE && config.showDragonfire()) {
			final int antifireVarbit = event.getValue();
            displayDragonfire = antifireVarbit > 0;
		}
		if (event.getVarbitId() == Varbits.SUPER_ANTIFIRE && config.showSuperDragonfire()) {
			final int superantifireVarbit = event.getValue();
			displaySuperDragonfire = superantifireVarbit > 0;
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {
		if (event.getType().equals(ChatMessageType.GAMEMESSAGE)) {
			String message = event.getMessage().replaceAll("<col=[a-z0-9]+>", "").replaceAll("</col>", "");
			if (message.equals(MarkHandler.MARK_BEGIN_MESSAGE)) {
				markHandler.activate();
				log.debug("Mark of Darkness activated with duration {} ticks", markHandler.getTicksRemaining());
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		updateMarkHandler();
	}

	private void updateMarkHandler() {
		displayMark = markHandler.decreaseTime() && config.showMark();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (!event.getGroup().equals("playerstatushighlight")) {
			return;
		}
		if (event.getKey().equals("showPoison") || event.getKey().equals("showVenom")) {
			int poisonVarp = client.getVarpValue(VarPlayer.POISON);
			if (!config.showPoison()) {
				displayPoison = false;
			} else {
                if (poisonVarp < -38) {
					if (!config.showVenom()) {
						displayPoison = true; //because player is still immune to poison
					} else {
						displayVenom = true;
					}
				} else displayPoison = poisonVarp < 0;
			}
		}
		if (event.getKey().equals("showDragonfire")) {
			if (!config.showDragonfire()) {
				displayDragonfire = false;
			} else if (client.getVarbitValue(Varbits.ANTIFIRE) > 0) {
				displayDragonfire = true;
			}
		}
		if (event.getKey().equals("showVenom")) {
			if (!config.showVenom()) {
				displayVenom = false;
			} else if (client.getVarpValue(VarPlayer.POISON) < -38) {
				displayVenom = true;
			}
		}
		if (event.getKey().equals("showSuperDragonfire")) {
			if (!config.showSuperDragonfire()) {
				displaySuperDragonfire = false;
			} else if (client.getVarbitValue(Varbits.SUPER_ANTIFIRE) > 0) {
				displaySuperDragonfire = true;
			}
		}
		if (event.getKey().equals("showMark")) {
			if (!config.showMark()) {
				displayMark	= false;
			}
			log.debug("Mark config changed");
		}
	}

	public HighlightProperties getHighlightProperties() {
		if (displaySuperDragonfire) {
			if (displayVenom) {
				return new HighlightProperties(config.superDragonfireAndVenomImmunityColor(), config.venomThickness(), config.venomFeather());
			} else if (displayPoison) {
				return new HighlightProperties(config.superDragonfireAndPoisonImmunityColor(), config.poisonThickness(), config.poisonFeather());
			} else {
				return new HighlightProperties(config.superDragonfireImmunityColor(), config.superDragonfireThickness(), config.superDragonfireFeather());
			}
		} else if (displayDragonfire) {
			if (displayVenom) {
				return new HighlightProperties(config.dragonfireAndVenomImmunityColor(), config.venomThickness(), config.venomFeather());
			} else if (displayPoison) {
				return new HighlightProperties(config.dragonfireAndPoisonImmunityColor(), config.poisonThickness(), config.poisonFeather());
			} else {
				return new HighlightProperties(config.dragonfireImmunityColor(), config.dragonfireThickness(), config.dragonfireFeather());
			}
		} else if (displayVenom) {
			return new HighlightProperties(config.venomImmunityColor(), config.venomThickness(), config.venomFeather());
		} else if (displayPoison) {
			return new HighlightProperties(config.poisonImmunityColor(), config.poisonThickness(), config.poisonFeather());
		} else {
			//default option in case nothing is to be displayed
			return new HighlightProperties(new Color(0,0,0,0), 0, 0);
		}
	}

	public boolean getMarkHighlightStatus() {
		return displayMark;
	}
	public HighlightProperties getMarkHighlightProperties() {
		return new HighlightProperties(config.markColor(), config.markThickness(), config.markFeather());
	}

	@Provides
	PlayerStatusHighlightConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PlayerStatusHighlightConfig.class);
	}

	@Getter
	public static class HighlightProperties {
		private final Color color;
		private final int outlineWidth;
		private final int feather;
		private HighlightProperties(Color color, int outlineWidth, int feather) {
			this.color = color;
			this.outlineWidth = outlineWidth;
			this.feather = feather;
		}


	}
}
